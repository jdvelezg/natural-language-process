/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.local.nlp.summarization.text_summarizer.abstractions.util.TimeAbstractions;

/**
 * @author danielvelez
 *
 */
public class ActionConcept implements Concept{
	
	private float Score;
	
	protected String Name;
	
	protected TimeAbstractions Time;
	
	protected SubjectConcept Subject;
	
	protected ObjectConcept Object;
	
	protected List<MetaAbstraction> Modifiers;
	
	/**
	 * Constructor
	 * @param name
	 */
	public ActionConcept(String name) {
		this.Modifiers = new ArrayList<MetaAbstraction>();
		
		this.Name = name;
	}

	public TimeAbstractions getTime() {
		return Time;
	}

	public void setTime(TimeAbstractions time) {
		
		if(this.Time!=null) {
			this.Score = Float.sum(Score, (-1)*this.Time.getEntropy() );
		}
		this.Score += time.getEntropy();
		Time = time;
	}

	public SubjectConcept getSubject() {
		return Subject;
	}

	public void setSubject(SubjectConcept subject) {
		
		if(this.Subject!=null) {
			this.Score = Float.sum(Score, (-1)*this.Subject.getConceptScore() );
		}
		this.Score += subject.getConceptScore();
		Subject = subject;
	}

	public ObjectConcept getObject() {
		return Object;
	}

	public void setObject(ObjectConcept object) {
		
		if(this.Object!=null) {
			this.Score = Float.sum(Score, (-1)*this.Object.getConceptScore() );
		}
		this.Score += object.getConceptScore();
		Object = object;
	}

	@Override
	public void setModifiers(List<MetaAbstraction> modifiers) {
		this.Modifiers = modifiers;
	}

	@Override
	public void addFeature(MetaAbstraction feature) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModifier(MetaAbstraction modifier) {
		if(this.Modifiers == null)
			this.Modifiers = new ArrayList<MetaAbstraction>();
		
		this.Modifiers.add(modifier);
	}

	@Override
	public void addBelongsTo(MetaAbstraction owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBelonging(MetaAbstraction belonging) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getConceptScore() {
		
		Float totalScore = new Float(this.Score);
		
		//modifiers
		if(this.Modifiers!=null && this.Modifiers.size()!=0) {
			totalScore = this.Modifiers.stream().map(m -> m.getNodeScore()).reduce((f1,f2) -> f1+f2).get();
		}
		
		return totalScore;
	}
	
	@Override
	public String getTextualConcept(String POS) {
		
		String mySubject =Subject!=null?this.Subject.getTextualConcept(""):"";
		String myObject =Object!=null?this.Object.getTextualConcept(""):"";
		String myModifiers="";
		Iterator<MetaAbstraction> mdIter = this.Modifiers.iterator();
		
		while(mdIter.hasNext()) {
			myModifiers +=mdIter.next().getText()+" ";
		}
			
		return mySubject+" "+myModifiers+" "+Name+" "+myObject;
	}
	

}
