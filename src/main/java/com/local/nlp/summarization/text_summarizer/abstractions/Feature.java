/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

/**
 * @author danielvelez
 *	Helps to describe an extracted feature.
 *	-quantity
 *	-
 */
public class Feature<T> {
	
	private String Name;
	private T Measure;
	private String description;
	
	Feature(String name, T measure){
		
		this.Name = name;
		this.Measure = measure;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMeasure() {
		return Measure.toString();
	}

	public void setMeasure(T measure) {
		Measure = measure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
