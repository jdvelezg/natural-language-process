/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.operators;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;
import com.local.nlp.summarization.text_summarizer.tags.Tag;

/**
 * @author danielvelez
 *
 */
public class Modifier  implements Tag{
	
	private PennTreebankTags Tag;
	
	private String Word;
	
	public Modifier(String word, PennTreebankTags tag) {
		
		this.Tag = tag;
		this.Word = word;
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
