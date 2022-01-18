package com.epam.rd.java.basic.task8.controller;

import org.xml.sax.helpers.DefaultHandler;

/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {

	private String xmlFileName;

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	// PLACE YOUR CODE HERE

}