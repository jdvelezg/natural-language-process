/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.List;

/**
 * @author danielvelez
 *
 */
public interface Concept {
	
	/**
	 * Set the modifiers list.
	 * @param modifiers
	 */
	void setModifiers(List<MetaAbstraction> modifiers);
	
	void addFeature(MetaAbstraction feature);
	
	void addModifier(MetaAbstraction modifier);
	
	void addBelongsTo(MetaAbstraction owner);
	
	void addBelonging(MetaAbstraction belonging);
	
	float getConceptScore();
	/**
	 * Devuleve la forma gramatical correspondiente
	 * a la palabra conceptualizada de acuerdo con el 
	 * POS-Tag especificado.
	 * @param POS
	 * @return
	 */
	String getTextualConcept(String POS);

}
