/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.Iterator;
import java.util.List;

/**
 * @author danielvelez
 *
 */
public class CanConcept extends ActionConcept{

	protected ActionConcept SupportedAction;
	
	protected List<String> Implications;
	
	public CanConcept(String name) {
		super(name);
	}

	public ActionConcept getSupportedAction() {
		return SupportedAction;
	}

	public void setSupportedAction(ActionConcept supportedAction) {
		SupportedAction = supportedAction;
	}
	
	@Override
	public String getTextualConcept(String POS) {
		
		String mySubject =Subject!=null?this.Subject.getTextualConcept(""):"";
		String myObject =Object!=null?this.Object.getTextualConcept(""):"";
		String myModifiers="";
		String myNegation="";
		Iterator<MetaAbstraction> mdIter = this.Modifiers.iterator();
		
		while(mdIter.hasNext()) {
			String mdfr =mdIter.next().getText()+" ";
			if(mdfr.matches("[nN]?+['oO]?+t"))
				myNegation = mdfr;
			else
				myModifiers +=mdfr;
		}
		
		return mySubject+" "+Name+myNegation+" "+myModifiers+" "+myObject;
	}

}
