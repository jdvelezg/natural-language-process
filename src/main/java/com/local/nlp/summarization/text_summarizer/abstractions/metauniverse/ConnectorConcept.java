/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.List;

/**
 * @author danielvelez
 *
 */
public class ConnectorConcept implements Concept {
	
	private float Score;
	
	protected String Name;
	
	public ConnectorConcept(String name) {
		this.Name = name;
		this.Score=0;
	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.Concept#setModifiers(java.util.List)
	 */
	@Override
	public void setModifiers(List<MetaAbstraction> modifiers) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.Concept#addFeature(com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.MetaAbstraction)
	 */
	@Override
	public void addFeature(MetaAbstraction feature) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.Concept#addModifier(com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.MetaAbstraction)
	 */
	@Override
	public void addModifier(MetaAbstraction modifier) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.Concept#addBelongsTo(com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.MetaAbstraction)
	 */
	@Override
	public void addBelongsTo(MetaAbstraction owner) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.Concept#addBelonging(com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.MetaAbstraction)
	 */
	@Override
	public void addBelonging(MetaAbstraction belonging) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.Concept#getConceptScore()
	 */
	@Override
	public float getConceptScore() {
		return this.Score;
	}
	
	@Override
	public String getTextualConcept(String POS) {
		
		return this.Name;
	}

}
