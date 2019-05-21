/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.ArrayList;
import java.util.List;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;

/**
 * @author danielvelez
 *	Is a structured Tree that can be used as a Node of a higer structured Tree
 */
public class MetaAbstraction implements Node{
	
	protected int Possition;
	
	protected MetaAbstraction Left;
	
	protected MetaAbstraction Right;
	
	protected PennTreebankTags Tag;
	
	protected String Word;
	/**
	 * Abstraction type: Object, Subject, Action, Operation
	 */
	protected String Type;
	
	protected Concept Concept;
	
	protected List<String> WordElements;
	
	protected List<String> PosElements;
	
	public String originalSentence;
	
	
	public MetaAbstraction() {
		
		this.WordElements = new ArrayList<String>();
		this.PosElements = new ArrayList<String>();
	}
	

	public void setPossition(int possition) {
		Possition = possition;
	}



	public MetaAbstraction getLeft() {
		return Left;
	}



	public void setLeft(MetaAbstraction left) {
		this.Left = left;
	}



	public MetaAbstraction getRight() {
		return Right;
	}



	public void setRight(MetaAbstraction right) {
		this.Right = right;
	}



	public PennTreebankTags getTag() {
		return Tag;
	}



	public void setTag(PennTreebankTags tag) {
		this.Tag = tag;
	}



	public String getType() {
		return Type;
	}



	public void setType(String type) {
		Type = type;
	}



	public List<String> getWordElements() {
		return WordElements;
	}



	public void setWordElements(List<String> wordElements) {
		WordElements = wordElements;
	}



	public List<String> getPosElements() {
		return PosElements;
	}



	public void setPosElements(List<String> posElements) {
		PosElements = posElements;
	}
	
	
	public String getWord() {
		return Word;
	}


	public void setWord(String word) {
		Word = word;
	}
	
	
	public Concept getConcept() {
		return Concept;
	}


	public void setConcept(Concept concept) {
		Concept = concept;
	}


	@Override
	public int getNodePossition() {
		return this.Possition;
	}

	@Override
	public int getRightNodePossition() {
		return this.Right.getNodePossition();
	}

	@Override
	public int getLeftNodePossition() {
		return this.Left.getNodePossition();
	}

	@Override
	public boolean isLeaf() {
		
		if(this.Left != null || this.Right != null) {
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Returns the number of indexes for console to string printing
	 * @return
	 */
	public String getTreeIndex() {
		String defaultIndexion = "\t\t\t";
		String childIndexion =(this.Right!=null || this.Left!=null)?"\t\t\t":"";
		
		return childIndexion+defaultIndexion;
		
	}

	@Override
	public float getNodeScore() {
		
		float score = 0;
		if(this.Right != null) 
			score +=this.Right.getNodeScore();
		
		if(this.Left != null) 
			score +=this.Left.getNodeScore();
		
		score +=this.Concept.getConceptScore();
		
		return score;
	}
	
	@Override
	public String toString() {
		
		assert(this.Left!=null && this.Right!=null);
		
		String right = this.Right ==null?"--\n": this.Right.toString()+"\n";
		
		String node = getTreeIndex()+ this.Type+"\n"
					+ getTreeIndex()+"Score:"+getNodeScore()+"\n"
					+ getTreeIndex()+"Right:"+(Right!=null?Float.toString(Right.getNodeScore()):"0")+"\n"
					+ getTreeIndex()+"Left:"+(Left!=null?Float.toString(Left.getNodeScore()):"0")+"\n"
					+ getTreeIndex()+this.Tag.toString()+"\n"
					+ getTreeIndex()+this.Word+"\n";
		
		String left = this.Left ==null?"--\n": this.Left.toString()+"\n";
		
		return right+node+left;
	}


	@Override
	public String getText() {
		
		String rightText = this.Right!=null?this.Right.getText():"";
		String leftText = this.Left!=null?this.Left.getText():"";
		
		return rightText+" "+this.Concept.getTextualConcept(this.Tag.toString())+" "+leftText;
	}
	

}
