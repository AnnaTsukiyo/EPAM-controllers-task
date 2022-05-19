package com.epam.rd.java.basic.task8.controller;

import com.epam.rd.java.basic.task8.entity.Product;
import com.epam.rd.java.basic.task8.entity.Storage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for SAX parser.
 */

public class SAXController extends DefaultHandler {

    private String xmlFileName;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }
    List<Product> result;
   private static Storage storage = new Storage();

    public static Storage getStorage() {
        return storage;
    }

    public List<Product> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    private int id;
    private String title;
    private String note;
    private int quantity;
    private String units;
    private Double price;
    private String lastElementName;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();
        if (!information.isEmpty()) {
            if (lastElementName.equals("id")) {
                id = Integer.parseInt(information);
            }
            if (lastElementName.equals("title")) {
                title = information;
            }
            if (lastElementName.equals("note")) {
                note = information;
            }
            if (lastElementName.equals("quantity")) {
                quantity = Integer.parseInt(information);
            }
            if (lastElementName.equals("units")) {
                units = information;
            }
            if (lastElementName.equals("price")) {
                price = Double.valueOf(information);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ((title != null && !title.isEmpty()) &&
                id != 0 &&
                (note != null && !note.isEmpty()) &&
                quantity != 0 &&
                (units != null && !units.isEmpty()) &&
                (price != null)) {

            storage.addProduct(new Product(id, title, note, quantity, units, price));
            id = 0;
            title = null;
            note = null;
            quantity = 0;
            units = null;
            price = null;
        }
    }

    public static Document getDocument(Storage storage) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element tElement = document.createElement(XML.STORAGE.value());
        document.appendChild(tElement);

        for (Product product : storage.getProducts()) {

            Element productElement = document.createElement(XML.PRODUCT.value());
            tElement.appendChild(productElement);

            Element titleElement = document.createElement(XML.TITLE.value());
            titleElement.setTextContent(product.getTitle());
            productElement.appendChild(titleElement);

            Element idElement = document.createElement(XML.ID.value());
            idElement.setTextContent(Integer.toString(product.getId()));
            productElement.appendChild(idElement);

            Element noteElement = document.createElement(XML.NOTE.value());
            noteElement.setTextContent(product.getNote());
            productElement.appendChild(noteElement);

            Element quantityElement = document.createElement(XML.QUANTITY.value());
            quantityElement.setTextContent(Integer.toString(product.getQuantity()));
            productElement.appendChild(quantityElement);

            Element unitsElement = document.createElement(XML.UNITS.value());
            unitsElement.setTextContent(String.valueOf(product.getUnits()));
            productElement.appendChild(unitsElement);

            Element priceElement = document.createElement(XML.PRICE.value());
            priceElement.setTextContent(String.valueOf(product.getPrice()));
            productElement.appendChild(priceElement);
        }
        return document;
    }

    public static void saveToXML(Storage storage, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(storage), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);

        t.transform(source, result);
    }

    public Storage readSaxParser() {

        Storage storage = new Storage();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = getXMLFileAsStream()) {

            SAXParser saxParser = factory.newSAXParser();
            // parse XML and map to object, it works, but not recommend, try JAXB
            SAXController handler = new SAXController(xmlFileName);
            XMLReader xmlReader = saxParser.getXMLReader();

            saxParser.parse(xmlFileName, handler);

            // print all
            List<Product> result = getResult();
            String output = new String("output.dom.xml");
            saveToXML(storage,output);
            for (int i = 0; i < result.size(); i++) {
                storage.addProduct(result.get(i));
            }
            result.forEach(System.out::println);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
        return storage;
    }

    private static InputStream getXMLFileAsStream() {
        return SAXController.class.getClassLoader().getResourceAsStream("input.xml");
    }
}