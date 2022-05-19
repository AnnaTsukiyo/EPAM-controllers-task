package com.epam.rd.java.basic.task8;

import com.epam.rd.java.basic.task8.controller.DOMController;
import com.epam.rd.java.basic.task8.controller.SAXController;
import com.epam.rd.java.basic.task8.controller.STAXController;
import com.epam.rd.java.basic.task8.controller.Sorter;
import com.epam.rd.java.basic.task8.entity.Storage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException, XMLStreamException {
        if (args.length != 1) {
            return;
        }
        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);
        Storage storage;
        ////////////////////////////////////////////////////////
        // DOM
        ////////////////////////////////////////////////////////

        DOMController domController = new DOMController(xmlFileName);
        storage = domController.readDocument();
        System.out.println(storage);
        // sort (case 1)
        Sorter.sortProductsByYear(storage);
        // save
        String outputXmlFile = "output.dom.xml";
        System.out.println("Output ==> " + outputXmlFile);

        ////////////////////////////////////////////////////////
        // SAX
        ////////////////////////////////////////////////////////

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXController handler = new SAXController(xmlFileName);

        parser.parse(new File(xmlFileName), handler);
        storage = SAXController.getStorage();
        System.out.println(storage);
        Sorter.sortProductsByTitle(storage);
        SAXController.saveToXML(storage, outputXmlFile);
        outputXmlFile = "output.sax.xml";
        System.out.println("Output ==> " + outputXmlFile);

        ////////////////////////////////////////////////////////
        // StAX
        ////////////////////////////////////////////////////////

        // get
        STAXController staxController = new STAXController(xmlFileName);
        storage = staxController.readDocument();
        System.out.println(storage);
        // sort  (case 3)
        Sorter.sortProductsByRating(storage);
        // save
        outputXmlFile = "output.stax.xml";
        System.out.println("Output ==> " + outputXmlFile);

    }
}


