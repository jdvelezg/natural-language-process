/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.tags;

/**
 * @author danielvelez
 *
 */

public enum PennTreebankTags implements Tag{
	/**
	 * CC: Coordinating conjunction
	 */
	CC 		("Coordinating conjunction"),
	/**
	 * CD: Cardinal Number
	 */
	CD 		("Cardinal number"),
	/**
	 * DT: Determiner
	 */
	DT 		("Determiner"),
	/**
	 * EX: Existential There
	 */
	EX 		("Existential there"),
	/**
	 * FW: Foreign word
	 */
	FW 		("Foreign word"),
	/**
	 * IN: Preposition or subordinating conjunction
	 */
	IN 		("Preposition or subordinating conjunction"),
	/**
	 * JJ: Ajective
	 */
	JJ 		("Adjective"),
	/**
	 * JJR: Adjective comparative
	 */
	JJR 	("Adjective, comparative"),
	/**
	 * JJS: Adjective Superlative
	 */
	JJS 	("Adjective, superlative"),
	/**
	 * LS: List Item marker
	 */
	LS 		("List item marker"),
	/**
	 * MD: Modal
	 */
	MD 		("Modal"),
	/**
	 * NN: Noun, Singular or mass
	 */
	NN 		("Noun, singular or mass"),
	/**
	 * NNS: Noun, plural
	 */
	NNS 	("Noun, plural"),
	/**
	 * NNP: Proper Noun, Singular
	 */
	NNP 	("Proper noun, singular"),
	/**
	 * NNPS: Proper Noun Plural
	 */
	NNPS 	("Proper noun, plural"),
	/**
	 * PDT: Predeterminer
	 */
	PDT 	("Predeterminer"),
	/**
	 * POS: Possessive ending
	 */
	POS 	("Possessive ending"),
	/**
	 * PRP: Personal Pronoun
	 */
	PRP 	("Personal pronoun"),
	/**
	 * PRP: Possessive pronoun
	 */
	PRP$ 	("Possessive pronoun"),
	/**
	 * RB: Adverb
	 */
	RB 		("Adverb"),
	/**
	 * RBR: Adverb comparative
	 */
	RBR 	("Adverb, comparative"),
	/**
	 * RBS: Adverb superlative
	 */
	RBS 	("Adverb, superlative"),
	/**
	 * RP: Particle
	 */
	RP 		("Particle"),
	/**
	 * SYM: Symbol
	 */
	SYM 	("Symbol"),
	/**
	 * TO: to
	 */
	TO 		("to"),
	/**
	 * UH: Interjection
	 */
	UH 		("Interjection"),
	/**
	 * VB: Verb, base form
	 */
	VB 		("Verb, base form"),
	/**
	 * VBD: Verb, past tense
	 */
	VBD 	("Verb, past tense"),
	/**
	 * VBG: Verb, gerund or present participle
	 */
	VBG 	("Verb, gerund or present participle"),
	/**
	 * VBN: Verb, past participle
	 */
	VBN 	("Verb, past participle"),
	/**
	 * VBP: Verb, non-3rd person singular present
	 */
	VBP 	("Verb, non-3rd person singular present"),
	/**
	 * VBZ: Verb, 3rd person singular present
	 */
	VBZ 	("Verb, 3rd person singular present"),
	/**
	 * WDT: Wh-determiner
	 */
	WDT 	("Wh-determiner"),
	/**
	 * WP: Wh-pronoun
	 */
	WP 		("Wh-pronoun"),
	/**
	 * WP$: Possessive wh-pronoun
	 */
	WP$ 	("Possessive wh-pronoun"),
	/**
	 * WRB: Wh-adverb
	 */
	WRB 	("Wh-adverb");
	
	private final String name;
	private String word;
	private Tag preTag;
	private Tag posTag;
	
	/**
	 * Enumeration Constructor
	 * @param nm name of the Tag
	 */
	PennTreebankTags(String nm){
		name=nm;
	}
	
	public String getTag() {return this.toString();}
	
	public String getTagName() {return name;}
	
	public String getWord() {return word;}
	
	public void setWord(String word) {this.word=word;}

	public Tag getPreTag() {return this.preTag;}

	public Tag getPosTag() {return this.posTag;}

}
