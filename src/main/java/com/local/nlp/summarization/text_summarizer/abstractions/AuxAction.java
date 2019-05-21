/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;

/**
 * @author danielvelez
 *
 */
public class AuxAction extends Action {
	
	private String AuxiliarVerb;
	
	private String MainVerb;

	public AuxAction(String word, PennTreebankTags tag) {
		super(word, tag);
		this.MainVerb = word;
	}

	public String getAuxiliarVerb() {
		return AuxiliarVerb;
	}

	public void setAuxiliarVerb(String auxiliarVerb, String posTag) {
		AuxiliarVerb = auxiliarVerb;
	}

	public String getMainVerb() {
		return MainVerb;
	}
	

}
