/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.ArrayList;
import java.util.List;

import scala.xml.dtd.impl.Base.Meta;

/**
 * @author danielvelez
 *
 */
public class ModifierConcept implements Concept{
	
	private float Score;
	
	protected String Name;
	
	protected List<MetaAbstraction> Modifiers;
	
	protected List<MetaAbstraction> Features;
	
	
	public ModifierConcept(String name) {
		this.Modifiers = new ArrayList<MetaAbstraction>();
		this.Features = new ArrayList<MetaAbstraction>();
		
		this.Name = name;
		this.Score = 0.5f;
	}

	@Override
	public void setModifiers(List<MetaAbstraction> modifiers) {
		this.Modifiers = modifiers;	
	}
	
	@Override
	public void addBelonging(MetaAbstraction object) {
		//N/A
	}
	
	@Override
	public void addBelongsTo(MetaAbstraction object) {
		//N/A
	}
	
	@Override
	public void addFeature(MetaAbstraction feature) {
		
		if(this.Features == null)
			this.Features = new ArrayList<MetaAbstraction>();
		
		this.Features.add(feature);
	}
	
	@Override
	public void addModifier(MetaAbstraction modifier) {
		
		if(this.Modifiers == null)
			this.Modifiers = new ArrayList<MetaAbstraction>();
		
		this.Modifiers.add(modifier);
	}
	
	@Override
	public float getConceptScore() {
		
		Float totalScore = new Float(this.Score);
		
		/*/modifiers
		if(this.Modifiers!=null && this.Modifiers.size()!=0) {
			totalScore = this.Modifiers.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}/**/
		//features
		if(this.Features!=null && this.Features.size()!=0) {
			totalScore = this.Features.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}
		
		return totalScore;
	}
	
	@Override
	public String getTextualConcept(String POS) {
		
		String textual ="";
		if(Features.size()!=0)
			textual +=Features.get(0).getText()+" ";
		
		textual +=Name;
		
		return textual;
	}

}
