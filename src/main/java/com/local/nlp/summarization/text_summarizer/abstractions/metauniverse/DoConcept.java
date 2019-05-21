/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

import java.util.Iterator;

/**
 * @author danielvelez
 *
 */
public class DoConcept extends ActionConcept {
	
	protected ActionConcept SupportedAction;
	
	public DoConcept(String name) {
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
		
		return mySubject+" "+myModifiers+Name+myNegation+" "+myObject;
	}

}
