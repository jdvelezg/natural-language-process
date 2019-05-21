/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.tags;

/**
 * @author danielvelez
 *
 */
public interface Tag {
	
	/**
	 * Returns the tag code
	 * @return tag code
	 */
	String getTag();
	/**
	 * Returns the tagged word
	 * @return tagged word
	 */
	String getWord();
	/**
	 * Returns the tag name
	 * @return tag name
	 */
	String getTagName();
	/**
	 * Returns the previous Tag in the input-Stream
	 * @return previous Tag Object in the input-Stream
	 */
	Tag getPreTag();
	/**
	 * Returns the following Tag in the input-Stream
	 * @return following Tag Object in the input-Stream
	 */
	Tag getPosTag();
	/**
	 * Returns the tag label as a string
	 * @return
	 */
	String toString();

}
