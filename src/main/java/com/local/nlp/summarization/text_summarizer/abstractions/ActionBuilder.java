/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions;

import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.ActionConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.BeConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.CanConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.DoConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.HaveConcept;
import com.local.nlp.summarization.text_summarizer.abstractions.util.TimeAbstractions;
import com.local.nlp.summarization.text_summarizer.tags.PennTreebankTags;

/**
 * @author danielvelez
 *
 */
public abstract class ActionBuilder {
	
	/**
	 * Biulds an <code>ActionConcept</code> from a word and a tag
	 * Choose the right tense for the action accordingly with the Tag provided
	 * 
	 * @param word the action
	 * @param tag the POS tag
	 * @return the ActionConcept
	 */
	public static ActionConcept buildActionConcept(String word, PennTreebankTags tag) {
		
		ActionConcept concept = new ActionConcept(word);
		 
		//VB 	Verb, base form
		//VBP 	Verb, non-3rd person singular present
		//VBZ 	Verb, 3rd person singular present 
		if(
				tag.compareTo(PennTreebankTags.VB)	==0 ||
				tag.compareTo(PennTreebankTags.VBP)	==0 ||
				tag.compareTo(PennTreebankTags.VBZ)	==0
				) {
			
			concept.setTime(TimeAbstractions.PRESENT);
		}
		//VBG 	Verb, gerund or present participle
		else if(tag.compareTo(PennTreebankTags.VBG)==0 ) {
			
			concept.setTime(TimeAbstractions.NOW);
		}
		//VBN 	Verb, past participle
		//VBD 	Verb, past tense
		else if(
				tag.compareTo(PennTreebankTags.VBN)==0 || 
				tag.compareTo(PennTreebankTags.VB)	==0 
				) {
			
			concept.setTime(TimeAbstractions.PAST);
		}
		
		return concept;
	}
	
	/**
	 * Builds the <code>DoConcept</code> from a word and a tag
	 * 
	 * @param word
	 * @param tag
	 * @return
	 */
	public static DoConcept buildDoConcept(String word, String tag) {
		
		DoConcept concept = new DoConcept(word);
		concept.setSupportedAction(buildActionConcept(word,PennTreebankTags.valueOf(tag)));
		
		return concept;
	}
	
	/**
	 * Builds the <code>BeConcept</code> from a word and a tag
	 * 
	 * @param word
	 * @param tag
	 * @return
	 */
	public static BeConcept buildBeConcept(String word, String tag) {
		
		BeConcept concept = new BeConcept(word);
		ActionConcept aux = buildActionConcept(word, PennTreebankTags.valueOf(tag)); 
		concept.setTime(aux.getTime());
		
		return concept;
	}
	
	public static HaveConcept buildHaveConcept(String auxVerb, String word, String auxTag, String wordTag) {
		
		HaveConcept concept = new HaveConcept(auxVerb);
		ActionConcept aux = buildActionConcept(auxVerb, PennTreebankTags.valueOf(auxTag)); 
		concept.setTime(aux.getTime());
		concept.setSupportedAction(buildActionConcept(word,PennTreebankTags.valueOf(wordTag)));
		
		return concept;
	}
	
	public static CanConcept buildCanConcept(String auxVerb, String word, String auxTag, String wordTag) {
		
		CanConcept concept = new CanConcept(auxVerb);
		ActionConcept aux = buildActionConcept(auxVerb, PennTreebankTags.valueOf(auxTag)); 
		concept.setTime(aux.getTime());
		concept.setSupportedAction(buildActionConcept(word,PennTreebankTags.valueOf(wordTag)));
		
		return concept;
	}
	
	public static ActionConcept buildSingleVerbActionConcept(String verb, String tag) {
		
		//if be Verb Forms
		String toBeForms = "be,am,are,is,was,were";
		
		if(toBeForms.contains(verb) ) {
			return buildBeConcept(verb,tag);
		}
		//returns Do Verb concept
		else {
			return buildDoConcept(verb,tag);
		}		
	}
	
	public static ActionConcept buildDoubleVerbActionConcept(String verb1, String verb2, String tag1, String tag2) {
		
		ActionConcept concept;
		
		//Do forms
		String doForms = "do,does,did";
		//have forms
		String haveForms = "have,has,had";
		
		String canForms = "can,might,should,must,shall,could";
		
		//Do
		if(doForms.contains(verb1) ) {
			
			concept = buildDoConcept(verb2, tag2);
			ActionConcept aux = buildActionConcept(verb1, PennTreebankTags.valueOf(tag1));
			concept.setTime(aux.getTime());
			
		}
		//have 
		else if(haveForms.contains(verb1) ) {
			
			concept = buildHaveConcept(verb1, verb2, tag1, tag2);
			
		}
		//can
		else if(canForms.contains(verb1) ) {
			
			concept = buildCanConcept(verb1, verb2, tag1, tag2);
			
		}else {
			
			concept = buildActionConcept(verb2, PennTreebankTags.valueOf(tag2));
		}
		
		return concept;
		
	}
}
