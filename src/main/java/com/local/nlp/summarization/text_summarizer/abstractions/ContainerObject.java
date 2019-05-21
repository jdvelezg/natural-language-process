/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;

/**
 * @author danielvelez
 *
 */
public class ContainerObject<T> extends NounObject {
	
	protected T ObjectContained;

	ContainerObject(String wrd, PennTreebankTags tg) {
		super(wrd, tg);
		// TODO Auto-generated constructor stub
	}
	
	ContainerObject(String wrd, PennTreebankTags tg, T object) {
		super(wrd, tg);
		this.ObjectContained = object;
	}

	public T getObjectContained() {
		return ObjectContained;
	}

	public void setObjectContained(T objectContained) {
		ObjectContained = objectContained;
	}
	
	

}
