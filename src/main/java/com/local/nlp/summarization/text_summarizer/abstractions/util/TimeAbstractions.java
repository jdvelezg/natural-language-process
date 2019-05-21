/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.util;

/**
 * @author danielvelez
 *
 */
public enum TimeAbstractions {
	
	PAST (0.8f), 
	PRESENT (0.5f), 
	NOW (0.6f), 
	FUTURE (0.8f);
	
	private final float entropy;
	
	private TimeAbstractions(float entr) {
		entropy=entr;
	}
	
	private int BendedTime = 0;
	
	/**
	 * Represent the concept of relative time. 
	 * Positive number means bended to the future.
	 * Negative number means bended to the past.
	 * I.E.:
	 * early: -1 PRESENT bended to the past.
	 * if/would/might/should/could: +1 FUTURE/PAST bended to the future (hypothetical future)
	 * @param b
	 */
	public void bendTime(int b) {
		this.BendedTime = b;
	}
	
	public boolean isPastBended() {
			return this.BendedTime<0? true: false;
	}
	
	public boolean isFutureBended() {
		return this.BendedTime>0? true: false;
	}
	
	public boolean isBended() {
		return this.BendedTime!=0? true: false;
	}
	
	public float getEntropy() {
		return this.entropy;
	}

}
