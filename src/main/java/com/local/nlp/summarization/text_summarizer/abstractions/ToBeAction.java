/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;

/**
 * @author danielvelez
 *
 */
public class ToBeAction extends Action{
	
	private String Pronoun;
	
	private String MainVerb;

	public ToBeAction(String word, PennTreebankTags tag) {
		super(word, tag);
	}

}
