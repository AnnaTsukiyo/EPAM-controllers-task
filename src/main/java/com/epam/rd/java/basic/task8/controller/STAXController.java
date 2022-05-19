package com.epam.rd.java.basic.task8.controller;

import com.epam.rd.java.basic.task8.entity.Storage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {

    private String xmlFileName;

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Storage readDocument() throws FileNotFoundException, XMLStreamException {
        Storage storage = new Storage();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(xmlFileName));

        int eventType = reader.getEventType();
        int id = 0;
        String publisher = "";
        String topic = "";
        Double price = 0.0;

        while (reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                switch (reader.getName().getLocalPart()) {

                    case "storage":
                        String title = reader.getAttributeValue(null, "title");
                        storage.setName(title);
                        break;

                    case "id":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            id = Integer.valueOf(reader.getText());
                        }
                        break;

                }

            }
            if (eventType == XMLEvent.END_ELEMENT) {
                if (reader.getName().getLocalPart().equals("product")) {


                }
            }
        }

        try {
            FileOutputStream output = new FileOutputStream("output.stax.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(xmlFileName));
            writeXml(doc, output);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }

        return storage;

    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

}
