package com.epam.rd.java.basic.task8;

import java.io.File;
import java.util.stream.Stream;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.w3c.dom.*;

public class TaskTest {
	
    @ParameterizedTest
    @MethodSource("testCases")
	void test(String xmlFileName) throws Exception {
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source schemaFile = new StreamSource(new File("input.xsd"));
		Schema schema = factory.newSchema(schemaFile);

		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(new File(xmlFileName)));
	}
    
    static Stream<Arguments> testCases() {
        return Stream.of(
			Arguments.of("input.xml"),
			Arguments.of("output.dom.xml"),
			Arguments.of("output.sax.xml"),
			Arguments.of("output.stax.xml")
        );
    }
    
    @Test
    void testTargetNamespace() throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new File("input.xsd"));

		NamedNodeMap map = document.getDocumentElement().getAttributes();
		boolean flag = false;
		for (int k = 0; k < map.getLength(); k++) {
			flag |= "targetNamespace".equals(map.item(k).getNodeName());
		}
		Assertions.assertTrue(flag, "input.xsd must contain targetNamespace attribute");

    }

}
