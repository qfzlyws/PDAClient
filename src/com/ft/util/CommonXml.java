package com.ft.util;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 通用XML操作類
 * 
 * @author 董猛
 * @version 1.0
 */

public class CommonXml {
	/**
	 * 獲取某個節點的值
	 * 
	 * @param filepath
	 *            XML文件的路徑
	 * @param node
	 *            節點的XPath語法
	 * @return 返回單一節點
	 */

	public static NodeList getNode(String filepath, String nodeXPath) {
		NodeList nodelist = null;

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			InputStream in = CommonXml.class.getClassLoader().getResourceAsStream(filepath);
			Document doc = builder.parse(in);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();

			// 獲取節點
			XPathExpression expr = xpath.compile(nodeXPath);
			nodelist = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodelist;
	}

	public static String getNodeValue(Node node) {
		return node.getTextContent();
	}
}
