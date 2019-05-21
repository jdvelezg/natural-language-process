/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.util;

/**
 * @author danielvelez
 *
 */
public enum GenderAbstractions {
	
	MALE (0.8f),
	FEMALE (0.8f),
	UNDETERMINED (0.5f);
	
	private final float Entropy;
	
	private GenderAbstractions(float entr) {
		this.Entropy =entr;
	}
	
	public float getEntropy() {
		return Entropy;
	}

}
