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
public class Belongs implements Operator, Tag {
	
	private PennTreebankTags Tag;
	
	private String Word;
	
	private Tag Owner;
	
	private Tag Owned;
	
	
	public Belongs(String word, PennTreebankTags tag) {
		
		this.Word 	= word;
		this.Tag	= tag;
	}

	public Belongs(String word, PennTreebankTags tag, Tag owner, Tag own) {
		
		this.Owner 	= owner;
		this.Owned	= own;
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
