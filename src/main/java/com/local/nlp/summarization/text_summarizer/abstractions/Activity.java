/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;
import com.local.nlp.summarization.text_summarizer.tags.Tag;

/**
 * @author danielvelez
 *
 */
public class Activity implements Tag {
	
	private PennTreebankTags Tag;
	private String Word;
	//private Subject Subject;
		
	
	
	public Activity() {
		
	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.tags.Tag#getTag()
	 */
	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.tags.Tag#getWord()
	 */
	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.tags.Tag#getTagName()
	 */
	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.tags.Tag#getPreTag()
	 */
	@Override
	public Tag getPreTag() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.tags.Tag#getPosTag()
	 */
	@Override
	public Tag getPosTag() {
		// TODO Auto-generated method stub
		return null;
	}

}
