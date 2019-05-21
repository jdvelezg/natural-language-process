/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.BeConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.Concept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.ConnectorConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.FeatureConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.MetaAbstraction;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.ModifierConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.ObjectConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.SubjectConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.util.AbstractionTypes;
import com.local.nlp.summarization.text_summarizer.abstractions.util.GenderAbstractions;
import com.local.nlp.summarization.text_summarizer.operators.Belongs;
import com.local.nlp.summarization.text_summarizer.operators.Causification;
import com.local.nlp.summarization.text_summarizer.operators.Modifier;
import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;
import com.local.nlp.summarization.text_summarizer.tags.Tag;

import scala.xml.dtd.impl.Base.Meta;

/**
 * @author danielvelez
 *
 */
public abstract class AbstractionBuilder {
	
	
	/**
	 * 
	 * @return
	 */
	public static List<Tag> buildAbstraction(String[] words, String[] postags) {
		
		
		int indx = 0;
		ArrayList<Tag> result = new ArrayList(words.length);
		
		for(String postag : postags) {
			
			switch(postag) {
			
			case "CC":
				result.add(PennTreebankTags.CC);
				break;
			case "CD":
				result.add(PennTreebankTags.CD);
				break;
			case "DT":
				result.add(PennTreebankTags.DT);
				break;
			case "EX":
				result.add(PennTreebankTags.EX);
				break;
			case "FW":
				result.add(PennTreebankTags.FW);
				break;
			case "IN":
				result.add(PennTreebankTags.IN);
				break;
			case "JJ":
				result.add(PennTreebankTags.JJ);
				break;
			case "JJR":
				result.add(PennTreebankTags.JJR);
				break;
			case "JJS":
				result.add(PennTreebankTags.JJS);
				break;
			case "LS":
				result.add(PennTreebankTags.LS);
				break;
			case "MD":
				result.add(PennTreebankTags.MD);
				break;
			case "NN":
				result.add(new NounObject(words[indx],PennTreebankTags.NN));
				break;
			case "NNS":
				result.add(new NounObject(words[indx],PennTreebankTags.NNS));
				break;
			case "NNP":
				result.add(new NounObject(words[indx],PennTreebankTags.NNP));
				break;
			case "NNPS":
				result.add(new NounObject(words[indx],PennTreebankTags.NNPS));
				break;
			case "PDT":
				result.add(PennTreebankTags.PDT);
				break;
			case "POS":
				result.add(new Belongs(words[indx], PennTreebankTags.POS) );
				break;
			case "PRP":
				result.add(PennTreebankTags.PRP);
				break;
			case "PRP$":
				result.add(new Belongs(words[indx], PennTreebankTags.PRP$) );
				break;
			case "RB":
				result.add(PennTreebankTags.RB);
				break;
			case "RBR":
				result.add(PennTreebankTags.RBR);
				break;
			case "RBS":
				result.add(PennTreebankTags.RBS);
				break;
			case "RP":
				result.add(PennTreebankTags.RP);
				break;
			case "SYM":
				result.add(PennTreebankTags.SYM);
				break;
			case "TO":
				result.add(PennTreebankTags.TO);
				break;
			case "UH":
				result.add(PennTreebankTags.UH);
				break;
			case "VB":
				result.add(new Action(words[indx], PennTreebankTags.VB) );
				break;
			case "VBD":
				result.add(new Action(words[indx], PennTreebankTags.VBD) );
				break;
			case "VBG":
				result.add(new Action(words[indx], PennTreebankTags.VBG) );
				break;
			case "VBN":
				result.add(new Action(words[indx], PennTreebankTags.VBN) );
				break;
			case "VBP":
				result.add(new Action(words[indx], PennTreebankTags.VBP) );
				break;
			case "VBZ":
				result.add(new Action(words[indx], PennTreebankTags.VBZ) );
				break;
			case "WDT":
				result.add(PennTreebankTags.WDT);
				break;
			case "WP":
				result.add(new Causification(words[indx],PennTreebankTags.WP) );
				break;
			case "WP$":
				result.add(PennTreebankTags.WP$);
				break;
			case "WRB":
				result.add(PennTreebankTags.WRB);
				break;
			}
			
			indx++;	
		}
		
		
		return result;
	}
	/**/
	
	public static MetaAbstraction globalAbstraction(List<String> words,List<String> tags) {
		
		int operatorIndx =-1;	
		
		//search intention
		MetaAbstraction intention = new MetaAbstraction();
		intention.setPosElements(tags);
		intention.setWordElements(words);
		intention.setType(AbstractionTypes.OPERATION.toString());
		intention.originalSentence = words.stream().reduce((str1,str2) -> str1+" "+str2).get();
		
		if( (operatorIndx = tags.indexOf("TO")) >=0) {
			
			intention.setWord(words.get(operatorIndx));
			intention.setPossition(operatorIndx);
			intention.setTag(PennTreebankTags.valueOf("TO"));	
			intention.setWord(words.get(operatorIndx));
			intention.setConcept(new ConnectorConcept(words.get(operatorIndx)));
						
			List<String> rightTags = tags.subList(0, operatorIndx);
			List<String> rightWords = words.subList(0, operatorIndx);
			
			List<String> intentionTags = tags.subList(operatorIndx+1,tags.size());
			List<String> intentionWords = words.subList(operatorIndx+1,words.size());
			
			if(rightTags.size() > 0) {
				
				intention.setRight(
					globalAbstraction(rightWords,rightTags)
					);
			}
			
			if(intentionTags.size() > 0) {
				
				intention.setLeft(
					globalAbstraction(intentionWords,intentionTags)
				);
			}			
		}		
		//search Means		
		else if( (operatorIndx = tags.indexOf("IN")) >0 ) {
			//&& tags.get(operatorIndx-1).matches("VB[\\w]*+")
			
			intention.setWord(words.get(operatorIndx));
			intention.setPossition(operatorIndx);
			intention.setTag(PennTreebankTags.valueOf("IN"));
			intention.setWord(words.get(operatorIndx));
			intention.setConcept(new ConnectorConcept(words.get(operatorIndx)));
			
			List<String> rightTags = tags.subList(0, operatorIndx);
			List<String> rightWords = words.subList(0, operatorIndx);
			
			List<String> meansTags = tags.subList(operatorIndx+1,tags.size());
			List<String> meansWords = words.subList(operatorIndx+1,words.size());
			
			if(rightTags.size() > 0 && operatorIndx!=0) {
				
				intention.setRight(
					globalAbstraction(rightWords,rightTags)
					);
			}
			
			if(meansTags.size() > 0 && (operatorIndx+1)!=tags.size()) {
				
				intention.setLeft(
					globalAbstraction(meansWords,meansTags)
				);
			}
		}
		//search unions
		else if( (operatorIndx = tags.indexOf("CC")) >=0) {
			
			intention.setWord(words.get(operatorIndx));
			intention.setPossition(operatorIndx);
			intention.setTag(PennTreebankTags.valueOf("CC"));
			intention.setWord(words.get(operatorIndx));
			intention.setConcept(new ConnectorConcept(words.get(operatorIndx)));
			
			List<String> rightTags = tags.subList(0, operatorIndx);
			List<String> rightWords = words.subList(0, operatorIndx);
			
			List<String> unionTags = tags.subList(operatorIndx+1,tags.size());
			List<String> unionWords = words.subList(operatorIndx+1,words.size());
			
			if(rightTags.size() > 0) {
				
				intention.setRight(
					globalAbstraction(rightWords,rightTags)
					);
			}
			
			if(unionTags.size() > 0) {
				
				intention.setLeft(
					globalAbstraction(unionWords,unionTags)
				);
			}
		}		
		//search ActionLink
		else if( (operatorIndx = tags.indexOf("WP")) >=0) {
			
			intention.setWord(words.get(operatorIndx));
			intention.setPossition(operatorIndx);
			intention.setTag(PennTreebankTags.valueOf("WP"));
			intention.setWord(words.get(operatorIndx));
			intention.setConcept(new ConnectorConcept(words.get(operatorIndx)));
			
			List<String> rightTags = tags.subList(0, operatorIndx);
			List<String> rightWords = words.subList(0, operatorIndx);
			
			List<String> actLinkTags = tags.subList(operatorIndx+1,tags.size());
			List<String> actLinkWords = words.subList(operatorIndx+1,words.size());
			
			if(rightTags.size() > 0) {
				
				intention.setRight(
					globalAbstraction(rightWords,rightTags)
					);
			}
			
			if(actLinkTags.size() > 0) {
				
				intention.setLeft(
					globalAbstraction(actLinkWords,actLinkTags)
				);
			}
		}
		//Action abstraction
		else {
			intention = actionAbstraction(words,tags);
		}
		//subject abstractions
		
		return intention;
	}
	
	public static MetaAbstraction actionAbstraction(List<String> words,List<String> tags) {
		
		MetaAbstraction action = new MetaAbstraction();
		action.setPosElements(tags);
		action.setWordElements(words);
		action.setType(AbstractionTypes.ACTION.toString());
		
		Stream<String> verbs = tags.stream().filter(t -> t.contains("VB"));
		Iterator<String> iter = tags.stream().filter(t -> t.contains("VB")).iterator();
		long count = tags.stream().filter(t -> t.contains("VB")).count();
		
		//one single Verb
		if(Long.compare(count, 1) == 0) {
			
			String tag = iter.next();
			int verbIndx = tags.indexOf(tag);
			String word = words.get(verbIndx);
			
			action.setTag(PennTreebankTags.valueOf(tag));
			action.setWord(word);
			action.setConcept(
					ActionBuilder.buildSingleVerbActionConcept(
							words.get(verbIndx), 
							tags.get(verbIndx)
							)
					);
			
			List<String> rightTags = tags.subList(0, verbIndx);
			List<String> rightWords = words.subList(0, verbIndx);
			
			List<String> afterVerbTags = tags.subList(verbIndx+1,tags.size());
			List<String> afterVerbWords = words.subList(verbIndx+1,words.size());
			
			if(rightTags.size() > 0) {
				
				action.setRight(
					subjectAbstraction(rightWords,rightTags)
				);
			}
			
			if(afterVerbTags.size() > 0) {
				
				action.setLeft(
					subjectAbstraction(afterVerbWords,afterVerbTags)
				);
			}
			
		}
		//search for AuxPatterns 2 verbs
		else if(Long.compare(count, 1) > 0) {
			
			String tag1 = iter.next();
			int firstIndx = tags.indexOf(verbs.iterator().next());
			String tag2 = iter.next();
			int secondIndx = firstIndx + tags.subList(firstIndx+1, tags.size()).indexOf(tag2) +1;
			String word1 = words.get(firstIndx);
			String word2 = words.get(secondIndx);
					
			action.setConcept(
					ActionBuilder.buildDoubleVerbActionConcept(							
							word1, 
							word2, 
							tags.get(firstIndx),
							tags.get(secondIndx)
							)
					);
			action.setTag(PennTreebankTags.valueOf(
						tag2
					));
			action.setWord(
						word2
					);
			
			//extract modifiers from within verbs
			int interval = secondIndx-firstIndx-1;
			if(Integer.compare(interval, 0) != 0 ) {
				
				Map<Integer,MetaAbstraction> modf = modifierAbstraction(
											words.subList(firstIndx+1, secondIndx),
											tags.subList(firstIndx+1, secondIndx)
											);
				if(modf.size() > 0 ) {	
					List<MetaAbstraction> tmp = new ArrayList<MetaAbstraction>();
					tmp.addAll(modf.values());		
					action.getConcept().setModifiers(tmp);
				}
							
			}
			
			List<String> rightTags = tags.subList(0, firstIndx);
			List<String> rightWords = words.subList(0, firstIndx);
			
			List<String> afterVerbTags = tags.subList(secondIndx+1,tags.size());
			List<String> afterVerbWords = words.subList(secondIndx+1,words.size());
			
			if(rightTags.size() > 0) {
				
				action.setRight(
					subjectAbstraction(rightWords,rightTags)
				);
			}
			
			if(afterVerbTags.size() > 0) {
				
				action.setLeft(
					subjectAbstraction(afterVerbWords,afterVerbTags)
				);
			}
		}
		//TODO implement strategy for more than 2 followed verbs
		else {
			action = subjectAbstraction(words, tags);
		}
		
		return action;
	}
	
	public static MetaAbstraction subjectAbstraction(List<String> words, List<String> tags) {
		
		Map<Integer,MetaAbstraction> objects 	= objectAbstraction(words,tags); //System.out.println(objects.toString());
		Map<Integer,MetaAbstraction> features 	= featureAbstraction(words,tags);//System.out.println(features.toString());
		Map<Integer,MetaAbstraction> modifiers = modifierAbstraction(words,tags);//System.out.println(modifiers.toString());
		
		int objectCount = objects.size();
		long subjCount = tags.stream().filter(s -> s.matches("PRP[$]?")).count();
		
		/*********/
		//Defines the main Object/Subject
		int size = tags.size();
		if(Integer.compare(size, 1) > 0) {
			
			MetaAbstraction mainObject = new MetaAbstraction();
			int pointer = size-1;
			String tag = tags.get(pointer);
			String word = words.get(pointer);
			
			if(
				Integer.compare(objectCount, 0) == 0 &&
				Long.compare(subjCount, 0) 	== 0
				) {
				
				//builds a generic object
				mainObject = buildSubjectFromPRP("it","PRP");
				pointer++;
			}
			else if(tag.matches("PRP[$]?")) {
				
				mainObject = buildSubjectFromPRP(word,tag);
			}
			else if(objects.containsKey(pointer)) {
				
				mainObject = objects.get(pointer);
			}
			else if(features.containsKey(pointer)) {
				
				mainObject = features.get(pointer);
			}
			else if(modifiers.containsKey(pointer)) {
				
				mainObject = modifiers.get(pointer);
			}
			else if(tags.get(pointer).matches("POS")) {
				
				//builds a generic object
				mainObject = buildSubjectFromPRP("it","PRP");
				pointer++;
			}
			
			MetaAbstraction currentMain = mainObject;			
			pointer--; 
			
			while(pointer >= 0) {
				
				MetaAbstraction nextAbstraction;
				String nxtag = tags.get(pointer);
				String nxword = words.get(pointer);
				
				if(nxtag.equals("PRP")) {
					
					nextAbstraction = buildSubjectFromPRP(nxword,nxtag);
					
					if(mainObject.getType()==null) {
						
						mainObject = nextAbstraction;
						currentMain = nextAbstraction;
					}
					else if(currentMain.getType().equals(AbstractionTypes.FEATURE.toString())) {
						
						nextAbstraction.getConcept().addFeature(currentMain);
						currentMain = nextAbstraction;						
					}
					else if(currentMain.getType().equals(AbstractionTypes.OPERATION.toString())) {
						
						nextAbstraction.getConcept().addModifier(currentMain);
						currentMain = nextAbstraction;						
					}
					else {
						currentMain.getConcept().addFeature(nextAbstraction
								//buildToBeAbstraction(currentMain,nextAbstraction)
								);
					}
					
				}//OBJECT "NN[\\w]*+"
				else if(tags.get(pointer).matches("NN[\\w]*+")) {
					
					nextAbstraction = objects.get(pointer);
					assert(nextAbstraction!=null);
					
					if(mainObject.getType()==null) {
						
						mainObject = nextAbstraction;
						currentMain = nextAbstraction;
					}
					else if(currentMain.getType().equals(AbstractionTypes.FEATURE.toString())) {
						
						nextAbstraction.getConcept().addFeature(currentMain);
						currentMain = nextAbstraction;						
					}
					else if(currentMain.getType().equals(AbstractionTypes.OPERATION.toString())) {
						
						nextAbstraction.getConcept().addModifier(currentMain);
						currentMain = nextAbstraction;						
					}
					else {
						currentMain.getConcept().addFeature(nextAbstraction
								//buildToBeAbstraction(currentMain,nextAbstraction)
								);
					}					
				}///PRP$
				else if(tags.get(pointer).equals("PRP$")) {
					
					nextAbstraction = buildSubjectFromPRP(nxword,nxtag);
					
					if(mainObject.getType()==null) {
						
						mainObject = nextAbstraction;
						currentMain = nextAbstraction;
					}
					else if(currentMain.getType().equals(AbstractionTypes.FEATURE.toString())) {
						
						currentMain.setType(AbstractionTypes.OBJECT.toString());
						currentMain.getConcept().addBelongsTo(nextAbstraction);	
						nextAbstraction.getConcept().addBelonging(currentMain);
					}
					else if(currentMain.getType().equals(AbstractionTypes.OPERATION.toString())) {
						
						nextAbstraction.getConcept().addModifier(currentMain);
						currentMain = nextAbstraction;						
					}
					else {
						currentMain.getConcept().addBelongsTo(nextAbstraction);
						nextAbstraction.getConcept().addBelonging(currentMain);
					}
				}
				else if(nxtag.matches("POS")) {
					
					pointer--;
					nextAbstraction = objects.get(pointer);
					assert(nextAbstraction!=null);
					
					currentMain.getConcept().addBelongsTo(nextAbstraction);	
					nextAbstraction.getConcept().addBelonging(currentMain);
				}//features
				else if(features.containsKey(pointer)) {
					
					nextAbstraction = features.get(pointer);
					assert(nextAbstraction!=null);
					
					if(mainObject.getType()==null) {
						
						mainObject = nextAbstraction;
						currentMain = nextAbstraction;
					}
					else {
						currentMain.getConcept().addFeature(nextAbstraction);
					}					
				}//modifiers
				else if(modifiers.containsKey(pointer)) {
					
					nextAbstraction = modifiers.get(pointer);
					assert(nextAbstraction!=null);
					
					if(mainObject.getType()==null) {
						
						mainObject = nextAbstraction;
						currentMain = nextAbstraction;
					}
					else {
						currentMain.getConcept().addModifier(nextAbstraction);
					}					
				}
				else {
					nextAbstraction = buildModifierConcept(words.get(pointer), tags.get(pointer));
					
					if(mainObject.getType()==null) {
						
						mainObject = nextAbstraction;
						currentMain = nextAbstraction;
					}
					else {
						currentMain.getConcept().addModifier(nextAbstraction);
					}
				}
				
				if(
					mainObject.getType().compareTo(AbstractionTypes.OBJECT.toString())!=0 &&
					mainObject.getType().compareTo(AbstractionTypes.SUBJECT.toString())!=0 
						) {
					mainObject = currentMain;
				}				
				currentMain = nextAbstraction;
				pointer--;
			}
			
			return mainObject;
			
		}//>1
		else {
			
			int objCount = objects.size();
			int fetCount = features.size();
			int modCount = modifiers.size();
			
			if(Integer.compare(objCount, 0) != 0 ) {
				List<MetaAbstraction> tmp = new ArrayList<MetaAbstraction>();
				tmp.addAll(objects.values());
				return tmp.get(0);
			}else if(Integer.compare(fetCount, 0)!=0){
				List<MetaAbstraction> tmp = new ArrayList<MetaAbstraction>();
				tmp.addAll(features.values());
				return tmp.get(0);
			}else if(Integer.compare(modCount, 0)!=0){
				List<MetaAbstraction> tmp = new ArrayList<MetaAbstraction>();
				tmp.addAll(modifiers.values());
				return tmp.get(0);
			}else {
				
				return buildSubjectFromPRP(words.get(0), tags.get(0));
			}
		}
	}
	
	public static MetaAbstraction buildSubjectFromPRP(String word, String tag) {
		
		MetaAbstraction object = new MetaAbstraction();
		object.setPosElements(Arrays.asList(tag));
		object.setWordElements(Arrays.asList(word));
		object.setType(AbstractionTypes.SUBJECT.toString());			
		object.setWord(word);
		object.setPossition(0);
		object.setTag(PennTreebankTags.valueOf(tag));
		
		//Configures the concept
		SubjectConcept concept = new SubjectConcept(word);
		//Decode gender,quantity
		String fem = "she,her,hers";
		String male = "he,his,him";
		String thing = "it,that,this";
		String plural = "we,they,them,our";
		
		if(thing.contains(word)) {
			
			object.setType(AbstractionTypes.OBJECT.toString());
			concept.setGender(GenderAbstractions.UNDETERMINED.toString());
		}		
		else if(fem.contains(word)) {
			
			concept.setGender(GenderAbstractions.FEMALE.toString());
		}
		else if(male.contains(word)) {
			
			concept.setGender(GenderAbstractions.MALE.toString());
		}
		else if(plural.contains(word)) {
			
			concept.addFeature(buildFeatureConcept("many",PennTreebankTags.JJ.toString()));
			
		}else {
			
			concept.setGender(GenderAbstractions.UNDETERMINED.toString());
		}
		object.setConcept(concept);
		
		return object;		
	}

	public static Map<Integer,MetaAbstraction> objectAbstraction(List<String> words, List<String> tags) {
		
		
		Map<Integer,MetaAbstraction> objects 	= new HashMap<Integer,MetaAbstraction>();
		
		Stream<String> objectStream = tags.stream().filter(t -> t.matches("NN[\\w]*+"));
		Iterator<String> objectable = objectStream.iterator();
		List<String> tagview = tags.subList(0, tags.size());
		List<String> wrdview = words.subList(0, tags.size());
		int key = -1;
		
		while(objectable.hasNext()) {
			
			String tag = objectable.next(); //System.out.print(tag);
			int indx = tagview.indexOf(tag); //System.out.print(indx);
			String word = wrdview.get(indx); //System.out.println(word);
			
			MetaAbstraction object = new MetaAbstraction();
			object.setPosElements(tagview);
			object.setWordElements(wrdview);
			object.setType(AbstractionTypes.OBJECT.toString());			
			object.setWord(word);
			object.setPossition(indx);
			object.setTag(PennTreebankTags.valueOf(tag));
			
			//Configures the concept
			ObjectConcept concept = new ObjectConcept(word);
			if(//plural
				tag.compareTo(PennTreebankTags.NNS.toString())==0 || 
				tag.compareTo(PennTreebankTags.NNPS.toString())==0
				) {
				//concept.addFeature(buildFeatureConcept("many",PennTreebankTags.JJ.toString()));
			}
			object.setConcept(concept);
			
			key = (Integer.compare(key, 0) < 0)?indx:key+indx+1;
			objects.put(key,object);
			
			tagview = tags.subList(key+1, tags.size());
			wrdview = words.subList(key+1, tags.size());
		}
		
		return objects;
	}
	
	public static Map<Integer,MetaAbstraction> featureAbstraction(List<String> words, List<String> tags) {
		
		Map<Integer, MetaAbstraction> features = new HashMap<Integer,MetaAbstraction>(); 
		Iterator<String> featurable = tags.stream().filter(t -> t.matches("JJ[\\w]*+")).iterator();
		//Iterator<String> featurable = tags.stream().collect(Collectors.toMap(f, valueMapper))
		List<String> tagview = tags.subList(0, tags.size());
		List<String> wrdview = words.subList(0, tags.size());
		int key = -1;
		while(featurable.hasNext()) {
			
			String tag = featurable.next(); //System.out.print(tag);
			int indx = tagview.indexOf(tag); //System.out.println(indx);
			
			MetaAbstraction feature = new MetaAbstraction();
			feature.setPosElements(tagview);
			feature.setWordElements(wrdview);
			feature.setType(AbstractionTypes.FEATURE.toString());			
			feature.setWord(wrdview.get(indx));
			feature.setPossition(indx);
			feature.setTag(PennTreebankTags.valueOf(tag));
			feature.setConcept(new FeatureConcept(words.get(indx)));
			
			key = (Integer.compare(key, 0) < 0)?indx:key+indx+1;
			features.put(key,feature);
			
			tagview = tags.subList(key+1, tags.size());
			wrdview = words.subList(key+1, tags.size());
		}
		
		return features;
	}
	
	public static Map<Integer,MetaAbstraction> modifierAbstraction(List<String> words, List<String> tags) {
		
		Map<Integer,MetaAbstraction> modifiers = new HashMap<Integer,MetaAbstraction>();
		Iterator<String> buildable = tags.stream().filter(t -> t.matches("RB[\\w]*+")).iterator();
		
		while(buildable.hasNext()) {
			
			String tag = buildable.next();
			int indx = tags.indexOf(tag);
			
			MetaAbstraction modifier = new MetaAbstraction();
			modifier.setPosElements(tags);
			modifier.setWordElements(words);
			modifier.setType(AbstractionTypes.OPERATION.toString());			
			modifier.setWord(words.get(indx));
			modifier.setPossition(indx);
			modifier.setTag(PennTreebankTags.valueOf(tag));
			modifier.setConcept(new ModifierConcept(words.get(indx)));
			
			modifiers.put(indx,modifier);
		}
		
		return modifiers;
	}
	
	public static MetaAbstraction buildModifierConcept(String word, String tag) {
		
		MetaAbstraction modifier = new MetaAbstraction();
		modifier.setPosElements(Arrays.asList(tag));
		modifier.setWordElements(Arrays.asList(word));
		modifier.setType(AbstractionTypes.OPERATION.toString());			
		modifier.setWord(word);
		modifier.setPossition(0);
		modifier.setTag(PennTreebankTags.valueOf(tag));
		modifier.setConcept(new ModifierConcept(word));
		
		return modifier;
	}
	
	public static MetaAbstraction buildFeatureConcept(String word, String tag) {
		
		MetaAbstraction feature = new MetaAbstraction();
		feature.setPosElements(Arrays.asList(tag));
		feature.setWordElements(Arrays.asList(word));
		feature.setType(AbstractionTypes.FEATURE.toString());			
		feature.setWord(word);
		feature.setPossition(0);
		feature.setTag(PennTreebankTags.valueOf(tag));
		feature.setConcept(new FeatureConcept(word));
		
		return feature;
	}
	
	public static MetaAbstraction buildToBeAbstraction(MetaAbstraction subject, MetaAbstraction object) {
		
		MetaAbstraction action = new MetaAbstraction();
		action.setType(AbstractionTypes.ACTION.toString());
		action.setPosElements(Arrays.asList(PennTreebankTags.VB.toString()));
		action.setWordElements(Arrays.asList("be"));
		action.setTag(PennTreebankTags.VB);
		action.setConcept(new BeConcept("be"));
		action.setRight(subject);
		action.setLeft(object);
		
		return action;
	}

	public static AuxAction extractAuxVerbAction(List<String> words, List<String> tags) {

		//gets the first verb Tag
		Optional<String> auxVerb = tags.stream().filter(t -> t.contains("VB")).findFirst();	
		Optional<String> mainVerb = tags.stream().filter(t -> t.contains("VB") && t!=auxVerb.get()).findFirst();
		
		int auxIndx = tags.indexOf(auxVerb.get());
		int mainIndx = tags.indexOf(mainVerb.get());
		
		AuxAction Action = new AuxAction(
				words.get(mainIndx),
				PennTreebankTags.valueOf(tags.get(mainIndx))
				);
		Action.setAuxiliarVerb(
				words.get(auxIndx), 
				tags.get(auxIndx)
				);
		
		int modifiersCount = mainIndx-auxIndx-1;
		while(modifiersCount>1) {
			
			String adverbTag = tags.get(auxIndx+modifiersCount);
			String adverbWrd = words.get(auxIndx+modifiersCount);
			
			Action.addModifier(
					new Modifier(
							words.get(auxIndx+modifiersCount),
							PennTreebankTags.valueOf(
									tags.get(auxIndx+modifiersCount)
									)
							)
					);
			
			modifiersCount--;
		}
		
		return Action;
	}

}
