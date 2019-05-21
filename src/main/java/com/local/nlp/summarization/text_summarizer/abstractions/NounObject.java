/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import java.util.ArrayList;
import java.util.List;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;
import com.local.nlp.summarization.text_summarizer.tags.Tag;

/**
 * @author danielvelez
 *
 */
public class NounObject implements Tag{
	
	/**
	 * Type of object asbtraction
	 */
	private RegularObjects object;
	/**
	 * Tag recognized for the word that originates the object abstraction
	 */
	private PennTreebankTags tag;
	/**
	 * Word that originates the object abstraction
	 */
	private String word;
	
	private List<Feature> ObjectFeatures;
	
	private List<Tag> Belongings;
	
	private List<Tag> AbstracteDescriptions;
	
	
	/**
	 * Constructor for basic noun object
	 * @param wrd
	 * @param tg
	 */
	NounObject(String wrd, PennTreebankTags tg) {
		
		this.tag = tg;
		this.word = wrd;
		
		ObjectFeatures = new ArrayList<Feature>();
		AbstracteDescriptions = new ArrayList<Tag>();
	}
	
	/**
	 * Set the tye of object by checking the Tag
	 */
	private void setObjectType() {
		
		String PTT = this.tag.getTag();
		
		if(PTT.matches("NNP[S]*")) {
			
			this.object = RegularObjects.NAMED_OBJECT;
			
		}else{
			
			this.object = RegularObjects.NOUN_OBJECT;
		}
		
		//if plural adds the feature of quantity
		if(PTT.matches("NN[P]*S")) {
			
			this.ObjectFeatures.add(new Feature<String>("quantity","many") );
			
		}
		
		
	}	

	public RegularObjects getObject() {
		return object;
	}

	public void setObject(RegularObjects object) {
		this.object = object;
	}

	public List<Feature> getObjectFeatures() {
		return ObjectFeatures;
	}

	public void addFeatures(Feature feature) {
		ObjectFeatures.add(feature);
	}

	public List<Tag> getBelongings() {
		return Belongings;
	}

	public void addBelongings(Tag belonging) {
		Belongings.add(belonging);
	}

	public List<Tag> getAbstracteDescriptions() {
		return AbstracteDescriptions;
	}

	public void setAbstracteDescriptions(List<Tag> abstracteDescriptions) {
		AbstracteDescriptions = abstracteDescriptions;
	}

	public void setTag(PennTreebankTags tag) {
		this.tag = tag;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag getPreTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag getPosTag() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
