/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import com.local.nlp.summarization.text_summarizer.tags.Tag;

/**
 * @author danielvelez
 *
 */
public enum RegularObjects implements Tag{
	
	/**
	 * 
	 */
	NOUN_OBJECT (0.5f),
	/**
	 * 
	 */
	NAMED_OBJECT (0.4f),
	/**
	 * 
	 */
	CHARACTERIZED_OBJECT (0.3f);
	
	
	/**
	 * Entropia calculada para el ojeto
	 */
	private final float entropy;
	/**
	 * POS-Tag reconocido para el objeto
	 */
	private String POSTag;
	/**
	 * Processed word that originates the object
	 */
	private String Word;
	
	RegularObjects(float entr){
		entropy = entr;
	}

	@Override
	public String getTag() {
		return this.POSTag;
	}

	@Override
	public String getWord() {
		return this.Word;
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
