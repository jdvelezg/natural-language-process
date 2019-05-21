/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import java.util.List;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;
import com.local.nlp.summarization.text_summarizer.tags.Tag;

/**
 * @author danielvelez
 *
 */
public class Subject implements Tag {
	
	private PennTreebankTags Tag;
	
	private String Word;
	
	private Tag ActionLink;
	
	private Tag Intention;
	
	private Tag Means;
	
	private List<Tag> Belongings;
	
	
	
	public Tag getActionLink() {
		return ActionLink;
	}

	public void setActionLink(Tag actionLink) {
		ActionLink = actionLink;
	}

	public Tag getIntention() {
		return Intention;
	}

	public void setIntention(Tag intention) {
		Intention = intention;
	}

	public Tag getMeans() {
		return Means;
	}

	public void setMeans(Tag means) {
		Means = means;
	}

	public List<Tag> getBelongings() {
		return Belongings;
	}

	public void addBelongings(Tag belonging) {
		Belongings.add(belonging);
	}

	public void setTag(PennTreebankTags tag) {
		Tag = tag;
	}

	public void setWord(String word) {
		Word = word;
	}

	public Subject() {
		
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
