package com.epam.rd.java.basic.task8.controller;

import com.epam.rd.java.basic.task8.entity.Product;
import com.epam.rd.java.basic.task8.entity.Storage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Controller for DOM parser.
 */

public class DOMController {

    private String xmlFileName;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Storage readDocument() {
        Storage storage = new Storage();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(xmlFileName));

            doc.getDocumentElement().normalize();

            storage.setName(doc.getDocumentElement().getAttribute("title"));

            NodeList list = doc.getElementsByTagName("product");



            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);
                FileOutputStream output = new FileOutputStream("output.dom.xml");
                writeXml(doc, output);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String note = element.getElementsByTagName("note").item(0).getTextContent();
                    int quantity = Integer.parseInt(element.getElementsByTagName("quantity").item(0).getTextContent());
                    String units = element.getElementsByTagName("units").item(0).getTextContent();
                    double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());

                    Product product = new Product(id, title, note, quantity, units, price);
                    storage.addProduct(product);
                }
            }
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