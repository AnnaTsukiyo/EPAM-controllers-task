package com.epam.rd.java.basic.task8;

import com.epam.rd.java.basic.task8.controller.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			return;
		}
		
		String xmlFileName = args[0];
		System.out.println("Input ==> " + xmlFileName);
		
		////////////////////////////////////////////////////////
		// DOM
		////////////////////////////////////////////////////////
		
		// get container
		DOMController domController = new DOMController(xmlFileName);
		// PLACE YOUR CODE HERE

		// sort (case 1)
		// PLACE YOUR CODE HERE
		
		// save
		String outputXmlFile = "output.dom.xml";
		// PLACE YOUR CODE HERE

		////////////////////////////////////////////////////////
		// SAX
		////////////////////////////////////////////////////////
		
		// get
		SAXController saxController = new SAXController(xmlFileName);
		// PLACE YOUR CODE HERE
		
		// sort  (case 2)
		// PLACE YOUR CODE HERE
		
		// save
		outputXmlFile = "output.sax.xml";
		// PLACE YOUR CODE HERE
		
		////////////////////////////////////////////////////////
		// StAX
		////////////////////////////////////////////////////////
		
		// get
		STAXController staxController = new STAXController(xmlFileName);
		// PLACE YOUR CODE HERE
		
		// sort  (case 3)
		// PLACE YOUR CODE HERE
		
		// save
		outputXmlFile = "output.stax.xml";
		// PLACE YOUR CODE HERE
	}

}
