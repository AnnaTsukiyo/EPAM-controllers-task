package com.epam.rd.java.basic.task8.controller;

import org.xml.sax.helpers.DefaultHandler;

/**
 * Controller for SAX parser.
 */
public class SAXController extends DefaultHandler {
	
	private String xmlFileName;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	// PLACE YOUR CODE HERE

}