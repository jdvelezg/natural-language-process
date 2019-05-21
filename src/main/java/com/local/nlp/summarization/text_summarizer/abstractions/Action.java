/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import java.util.List;

import com.local.nlp.summarization.text_summarizer.abstractions.util.TimeAbstractions;
import com.local.nlp.summarization.text_summarizer.operators.Modifier;
import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;
import com.local.nlp.summarization.text_summarizer.tags.Tag;

/**
 * @author danielvelez
 *
 */
public class Action implements Tag {
	
	protected Tag Tag;
	
	protected String Word;
	
	protected TimeAbstractions Time;
	
	protected Tag Intention;
	
	protected Tag Means;
	
	protected List<Modifier> modifiers;
	
	
	/**
	 * Constructor
	 * @param word
	 * @param tag
	 */
	public Action(String word, PennTreebankTags tag) {
		this.Tag 	= tag;
		this.Word 	= word;
		setTimeAbstraction();
	}
	/**
	 * Constructor
	 * @param word
	 * @param tag
	 * @param time
	 */
	public Action(String word, PennTreebankTags tag, TimeAbstractions time) {
		this.Tag 	= tag;
		this.Word 	= word;
		this.Time 	= time;
	}
	
	/**
	 * infers the time form the tag
	 */
	protected void setTimeAbstraction() {
		
		if(this.Tag.getTag().compareTo("VBD")!=0 || this.Tag.getTag().compareTo("VBN")!=0 ) {
			
			this.Time = TimeAbstractions.PAST;
			
		}else if(this.Tag.getTag().compareTo("VBG")!=0) {
			
			this.Time = this.Word.matches("[\\w]+ing")? TimeAbstractions.NOW : TimeAbstractions.PRESENT;
		}else {
			
			this.Time = TimeAbstractions.PRESENT;
		}
			
	}
	
	public void addModifier(Modifier mdfr) {
		this.modifiers.add(mdfr);
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
	public com.local.nlp.summarization.text_summarizer.tags.Tag getPreTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.local.nlp.summarization.text_summarizer.tags.Tag getPosTag() {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
