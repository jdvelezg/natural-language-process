/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danielvelez
 *
 */
public class ObjectConcept implements Concept{
	
	/**
	 * The score is a measure of the information contained by this concept
	 */
	private float Score;
	
	protected String Name;
	
	protected List<MetaAbstraction> Features;
	
	protected List<MetaAbstraction> Modifiers;
	
	protected List<MetaAbstraction> Belongings;
	
	protected List<MetaAbstraction> BelongTo;
	
	
	/**
	 * Constructor
	 * @param name
	 */
	public ObjectConcept(String name) {
		this.Features 	= new ArrayList<MetaAbstraction>();
		this.Modifiers 	= new ArrayList<MetaAbstraction>();
		this.Belongings = new ArrayList<MetaAbstraction>();
		this.BelongTo 	= new ArrayList<MetaAbstraction>();
		
		this.Name = name;
		this.Score = 0.5f;
	}
	
	@Override
	public void addBelonging(MetaAbstraction object) {
		
		if(this.Belongings == null)
			this.Belongings = new ArrayList<MetaAbstraction>();
		
		this.Belongings.add(object);
	}
	
	@Override
	public void addBelongsTo(MetaAbstraction object) {
		
		if(this.BelongTo == null)
			this.BelongTo = new ArrayList<MetaAbstraction>();
		
		this.BelongTo.add(object);
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
	public void setModifiers(List<MetaAbstraction> modifiers) {
		this.Modifiers = modifiers;
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
			totalScore = Float.valueOf(Features.size());
			//totalScore = this.Features.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}
		//belongings
		if(this.Belongings!=null && this.Belongings.size()!=0) {
			totalScore = this.Belongings.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}
		
		return totalScore;
	}

	@Override
	public String getTextualConcept(String POS) {
		
		String textual ="";
		
		if(this.Features.size()!=0)
			textual +=this.Features.get(0).getText()+" ";
		
		if(this.BelongTo.size()!=0)
			textual =BelongTo.get(0).getText()+"'s "
					+textual
					+(Name.equals("it")?"":this.Name);
		else
			textual +=this.Name;
		
		
		return textual;
	}	
	
	

}
