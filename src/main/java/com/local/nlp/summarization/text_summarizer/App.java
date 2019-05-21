package com.local.nlp.summarization.text_summarizer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.clustering.LDA;
import org.apache.spark.ml.clustering.LDAModel;
import org.apache.spark.ml.feature.CountVectorizer;
import org.apache.spark.ml.feature.CountVectorizerModel;
import org.apache.spark.ml.feature.StopWordsRemover;
import org.apache.spark.ml.feature.Word2Vec;
import org.apache.spark.ml.feature.Word2VecModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.col;

import com.local.nlp.summarization.text_summarizer.abstractions.AbstractionBuilder;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.MetaAbstraction;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static List<String> getprocessableFilePath(String path) {
		
		ArrayList<String> files = new ArrayList<String>();
		File dir = new File(path);		
		File[] directoryListing = dir.listFiles();
		
		if (directoryListing != null) {
			
			for (File child : directoryListing) {	
				
				String childPath = child.toPath().toString();
				if(childPath.contains(".txt")){	
					
					files.add(childPath);					
				}
				else {
					files.addAll(
							getprocessableFilePath(childPath)
							);
				}
			}
		}
		
		return files;
	}
	
    public static void main( String[] args ) throws IOException
    {
    	org.apache.log4j.Logger.getLogger("org").setLevel(Level.ERROR);
		Locale.setDefault(new Locale("es","ES"));
		
		//Load the files
		List<String> files = getprocessableFilePath("src/main/resources/newsarticles");
		for(String file : files) {
			
			processData(file);
			//getOutputPath(file);
			//break;
		}
    }
    
    private static void processData(String path) {
    	
		try{
			System.out.println(path);
			
			//Sets utput paths
			String outPutPath = getOutputPath(path);
			BufferedWriter systemWriter = new BufferedWriter(
					new FileWriter(outPutPath.replace(".txt", "_system2.txt"), false)
					);
			BufferedWriter extractWriter = new BufferedWriter(
						new FileWriter(outPutPath.replace(".txt", "_system1.txt"), false)
					);
			
			String text = "";
	    	text = new String(Files.readAllBytes(Paths.get(path)));
	    	
			InputStream tokenModelStream = new FileInputStream("src/main/resources/models/en-token.bin");
			InputStream posModelStream = new FileInputStream("src/main/resources/models/en-pos-maxent.bin");
			InputStream sentenceModelStream = new FileInputStream("src/main/resources/models/en-sent.bin");
			
			SentenceModel sentenceModel = new SentenceModel(sentenceModelStream);
			SentenceDetectorME sentenceDect = new SentenceDetectorME(sentenceModel);
			String[] sentences = sentenceDect.sentDetect(text);
						
			TokenizerModel tokenModel = new TokenizerModel(tokenModelStream);
			Tokenizer tokenizer = new TokenizerME(tokenModel);		
			
			POSModel posModel = new POSModel(posModelStream);
			POSTaggerME posTagger = new POSTaggerME(posModel);
			
			String[] fullDataWords = tokenizer.tokenize(text.replaceAll("\\p{Punct}", "").toLowerCase());
			Map<String,Integer> dict = new HashMap<String,Integer>();
			int totalWords = 0;
			
			/*for(String w : fullDataWords) {
				
				if(dict.containsKey(w)) {
					dict.put(w, Integer.sum(dict.get(w), 1));
				}
				else {
					dict.put(w, new Integer(1));
					totalWords++;
				}
			}/**/
			
			//Spark  CountVectorizer 
			SparkSession spark = SparkSession.builder().appName("text-summarizer").master("local[*]").getOrCreate();
			
			List<Row> data = new ArrayList<Row>();
			//System.out.println("lines:"+sentences.length);
			for(String sent : sentences) {
			
				String[] tokens = tokenizer.tokenize(sent.replaceAll("\\p{Punct}+(?![sS])", ""));				
				String[] postags = posTagger.tag(tokens);
				
				data.add(
						RowFactory.create(Arrays.asList(tokens),Arrays.asList(postags)) 
						);
			
			}/**/
			
			StructType schema = new StructType(new StructField [] {
					  new StructField(
							  "rawsentences", 
							  new ArrayType(DataTypes.StringType, true),
							  false, 
							  Metadata.empty()),
					  new StructField(
							  "postag", 
							  new ArrayType(DataTypes.StringType, true),
							  false, 
							  Metadata.empty())
					});
			
			Dataset<Row> df = spark.createDataFrame(data, schema);
		
			//StopWordRemoval
			StopWordsRemover remover = new StopWordsRemover()
					.setInputCol("rawsentences")
					.setOutputCol("nonStop");
			Dataset<Row> cleanDF = remover.transform(df);
			
			Word2Vec word2Vec = new Word2Vec()
					  .setInputCol("nonStop")
					  .setOutputCol("worVec")
					  //.setVectorSize(3)
					  .setMinCount(1);
			
			Word2VecModel W2Vmodel = word2Vec.fit(cleanDF);
			Dataset<Row> vectorizedDataset = W2Vmodel.transform(cleanDF); 
			//vectorizedDataset.show();
			
			
			
			LDA lda = new LDA()
					.setK(10)
					.setMaxIter(10).setSeed(12345)
					.setFeaturesCol("worVec");
			LDAModel ldaModel = lda.fit(vectorizedDataset);

			//Dataset<Row> topics = 
			//		ldaModel.describeTopics(3).show();
			Dataset<Row> transformed = ldaModel.transform(vectorizedDataset);
			//transformed.show();
			
			// Trains a k-means model.
			KMeans kmeans = new KMeans()
					.setK(10)
					.setSeed(1L)
					.setFeaturesCol("worVec")
					.setPredictionCol("kmeans");
			KMeansModel kmodel = kmeans.fit(transformed);
			Dataset<Row> meansk =
					kmodel.transform(vectorizedDataset);
			
			Dataset<Row> summary = meansk.filter("kmeans = 3");
			
			List<Row> sentRows =summary.select("rawsentences","postag").collectAsList();
			List<MetaAbstraction> summarySentences = new ArrayList<MetaAbstraction>();
			
			for(Row r : sentRows) {
				
				scala.collection.mutable.WrappedArray Wsent = (scala.collection.mutable.WrappedArray)r.get(0);
				scala.collection.mutable.WrappedArray Wtag = (scala.collection.mutable.WrappedArray)r.get(1);
				String[] sent = new String[Wsent.length()];
				String[] tag = new String[Wtag.length()];
				Wsent.copyToArray(sent);
				Wtag.copyToArray(tag);
				
				MetaAbstraction abstraction = 
						AbstractionBuilder.globalAbstraction(Arrays.asList(sent), Arrays.asList(tag));
				String sumry = abstraction.getText();
				String fullSntc = abstraction.originalSentence;
				
				//System.out.println(sumry);
				systemWriter.append(sumry);	
				extractWriter.append(fullSntc);
			}
			
			/**/
			systemWriter.close();
			extractWriter.close();
		
		}catch(IOException exc){
			System.err.print("Exception1: "+exc.toString());
		}
    }//METHOD
    
    public static String getOutputPath(String path) throws IOException{
    	//src/main/resources/newsarticles/entertainment/289.txt
    	
    	String files[] = path.split("[/\\\\\\\\]");
    	String taskname = files[4];
    	String filename = files[5];
    	
    	String summaryPath 		= path.replace("/newsarticles/", "/Summaries/");
    	String referencePath 	= files[0]+"/"+files[1]+"/"+files[2]+"/reference/"+taskname+"_"+filename;
    	String systemPath 		= files[0]+"/"+files[1]+"/"+files[2]+"/system/"+taskname+"_"+filename;
    	
    	InputStream reader = new FileInputStream(summaryPath);
    	OutputStreamWriter writer = new OutputStreamWriter(
    									new FileOutputStream(referencePath)
    									);
    	writer.write(
    			new String(
    					Files.readAllBytes(Paths.get(summaryPath))
    					)
    			);
    	/**/
    	writer.close();
    	return systemPath;
    }
}
