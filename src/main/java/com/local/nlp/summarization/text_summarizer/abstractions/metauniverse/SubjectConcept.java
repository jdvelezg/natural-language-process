/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import com.local.nlp.summarization.text_summarizer.abstractions.util.GenderAbstractions;

/**
 * @author danielvelez
 *
 */
public class SubjectConcept extends ObjectConcept{
	
	private float Score;
	
	protected String Pronoun;
	
	protected GenderAbstractions Gender;
	
	/**
	 * Constructor
	 * @param name
	 */
	public SubjectConcept(String name) {
		super(name);
		this.Score = 0.5f;
	}

	public String getPronoun() {
		return Pronoun;
	}

	public void setPronoun(String pronoun) {
		Pronoun = pronoun;
	}

	public String getGender() {
		return Gender.toString();
	}

	public void setGender(String gender) {
		
		if(this.Gender != null) {
			this.Score = Float.sum(Score, (-1)*this.Gender.getEntropy());
		}
		
		Gender = GenderAbstractions.valueOf(gender);
		this.Score += this.Gender.getEntropy();
	}
	
	@Override
	public float getConceptScore() {
		
		Float totalScore = new Float(Score);
		
		/*/modifiers
		if(this.Modifiers!=null && this.Modifiers.size()!=0) {
			totalScore = this.Modifiers.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}/**/
		//features
		if(this.Features!=null && this.Features.size()!=0) {
			totalScore = this.Features.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}
		//belongings
		if(this.Belongings!=null && this.Belongings.size()!=0) {
			totalScore = this.Belongings.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}
		
		return totalScore;
	}
	

}
