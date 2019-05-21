/**
 * 
 */
package com.local.nlp.summarization.text_summarizer.abstractions.metauniverse;

/**
 * @author danielvelez
 *	A node of a structured Tree
 */
public interface Node {
	
	/**
	 * Returns the index of the node
	 * @return
	 */
	int getNodePossition();
	
	/**
	 * Returns the index of the right node
	 * @return
	 */
	int getRightNodePossition();
	
	/**
	 * Returns the index of the left node
	 * @return
	 */
	int getLeftNodePossition();
	
	/**
	 * Returns <code>True</code> if the node is a leaf, so it doesn´t have left or right links
	 * @return
	 */
	boolean isLeaf();
	/**
	 * Return the score assigned to the node
	 * @return
	 */
	float getNodeScore();
	
	/**
	 * Get the text to be used in writing
	 * @return
	 */
	String getText();

}
