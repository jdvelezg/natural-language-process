package com.local.nlp.summarization.text_summarizer;

import java.util.Arrays;
import java.util.Map;

import com.local.nlp.summarization.text_summarizer.abstractions.AbstractionBuilder;
import com.local.nlp.summarization.text_summarizer.abstractions.metauniverse.MetaAbstraction;

import junit.framework.TestCase;

public class AbstractionTest extends TestCase {
	
	public void singleAbstractionTest() {
		
		MetaAbstraction test;
		test = AbstractionBuilder.buildModifierConcept("very", "RB");
		System.out.println(test.toString());
		test = AbstractionBuilder.buildFeatureConcept("lazy", "JJ");
		System.out.println(test.toString());
		test = AbstractionBuilder.buildSubjectFromPRP("it", "PRP");
		System.out.println(test.toString());
		test = AbstractionBuilder.buildToBeAbstraction(
				AbstractionBuilder.buildSubjectFromPRP("it", "PRP"),
				AbstractionBuilder.buildFeatureConcept("lazy", "JJ")
				);
		System.out.println(test.toString());
		/**
		Map<Integer,MetaAbstraction> test;
		test = AbstractionBuilder.featureAbstraction(Arrays.asList(words), Arrays.asList(tags));
		System.out.println(test.toString());
		
		MetaAbstraction test;
		test = AbstractionBuilder.subjectAbstraction(Arrays.asList(words), Arrays.asList(tags));
		System.out.println(test.toString());
		/**/
	}

}
