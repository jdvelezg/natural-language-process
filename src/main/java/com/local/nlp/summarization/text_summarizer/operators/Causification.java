/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.operators;

import java.util.List;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;
import com.local.nlp.summarization.text_summarizer.tags.Tag;

/**
 * @author danielvelez
 *
 */
public class Causification implements Tag{
	
	private PennTreebankTags Tag;
	
	private String Word;
	
	private Tag Origin;
	
	private Tag Causality;
	
	public Causification(String word, PennTreebankTags tag){
		
		this.Tag = tag;
		this.Word = word;
	}
	
	public Causification(String word, PennTreebankTags tag, Tag origin, Tag causality){
		
		this.Tag = tag;
		this.Word = word;
		this.Origin = origin;
		this.Causality = causality;
	}

	@Override
	public String getTag() {
		return this.Tag.getTag();
	}

	@Override
	public String getWord() {
		return this.Word;
	}

	@Override
	public String getTagName() {
		return this.Tag.getTagName();
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
