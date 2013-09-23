package com.jsandusky.util;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.*;

public class TaggedData
{
	HashMap<Integer,Object> values = new HashMap<Integer,Object>();
	Object get_(int i) {
		if (values.containsKey(i))
			return values.get(i);
		return null;
	}
	
	public <T> T get(int i) {
		Object val = get_(i);
		if (val != null)
			return (T)val;
		return null;
	}
    
    public <T> void put(int i, T val) {
		values.put(i,val);
	}
	
	public boolean has(int i) {
		return values.containsKey(i);
	}
	
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		toXML_(sb,this,0,0);
		return sb.toString();
	}
	
	static void toXML_(StringBuilder sb, TaggedData d, int curTab, int fld) {
		String tab = "";
		for (int i = 0; i < curTab; ++i)
			tab += "    ";
		if (fld == 0)
			sb.append(tab + "<TaggedData>\n");
		else
			sb.append(tab + "<TaggedData field=\"" + fld + "\">\n");
		
		String varTab = tab + "    ";
		for (Integer i : d.values.keySet()) {
			Object val = d.values.get(i);
			if (val.getClass() == int.class) {
				sb.append("<Int field=\"" + i + "\">");
				sb.append(val.toString());
				sb.append("</Int>");
			} else if (val.getClass() == float.class) {
				sb.append("<Float field=\"" + i + "\">");
				sb.append(val.toString());
				sb.append("</Float>");
			} else if (val.getClass() == String.class) {
				sb.append("<String field=\"" + i + "\">");
				sb.append(val.toString());
				sb.append("</String>");
			} else if (val.getClass() == TaggedData.class) {
				toXML_(sb,(TaggedData)val,curTab + 1,i);
			}
		}
		
		sb.append(tab + "</TaggedData>\n");
	}
	
	public TaggedData() {
		
	}
	
	public TaggedData(String aXML) {
		Document doc = getDomElement(aXML);
		for (Node root = doc.getFirstChild(); root != null; root = root.getNextSibling()) {
			//ROOT
			for (Node group = root.getFirstChild(); group != null; group = group.getNextSibling()) {
				if (group instanceof Element) {
					Element elem = (Element)group;
                    if (elem.getNodeName().equalsIgnoreCase("int")) {
						int val = Integer.parseInt(elem.getAttribute("field"));
						int fldVal = Integer.parseInt(getCharacterDataFromElement(elem));
						values.put(val,fldVal);
					} else if (elem.getNodeName().equalsIgnoreCase("float")) {
						int val = Integer.parseInt(elem.getAttribute("field"));
						float fldVal = Float.parseFloat(getCharacterDataFromElement(elem));
						values.put(val,fldVal);
					} else if (elem.getNodeName().equalsIgnoreCase("string")) {
						int val = Integer.parseInt(elem.getAttribute("field"));
						String fldVal = getCharacterDataFromElement(elem);
						values.put(val,fldVal);
					} else if (elem.getNodeName().equalsIgnoreCase("taggeddata")) {
						int val = Integer.parseInt(elem.getAttribute("field"));
						TaggedData fldVal = new TaggedData(elem);
						values.put(val,fldVal);
					}
				}
			}
		}
	}
	
	TaggedData(Element root) {
		for (Node group = root.getFirstChild(); group != null; group = group.getNextSibling()) {
			if (group instanceof Element) {
				Element elem = (Element)group;
				if (elem.getNodeName().equalsIgnoreCase("int")) {
					int val = Integer.parseInt(elem.getAttribute("field"));
					int fldVal = Integer.parseInt(getCharacterDataFromElement(elem));
					values.put(val,fldVal);
				} else if (elem.getNodeName().equalsIgnoreCase("float")) {
					int val = Integer.parseInt(elem.getAttribute("field"));
					float fldVal = Float.parseFloat(getCharacterDataFromElement(elem));
					values.put(val,fldVal);
				} else if (elem.getNodeName().equalsIgnoreCase("string")) {
					int val = Integer.parseInt(elem.getAttribute("field"));
					String fldVal = getCharacterDataFromElement(elem);
					values.put(val,fldVal);
				} else if (elem.getNodeName().equalsIgnoreCase("taggeddata")) {
					int val = Integer.parseInt(elem.getAttribute("field"));
					TaggedData fldVal = new TaggedData(elem);
					values.put(val,fldVal);
				}
			}
		}
	}
	
	static String getCharacterDataFromElement(Element e) {
		Node child;
		for (child = e.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child.getNodeType() == Node.CDATA_SECTION_NODE || child.getNodeType() == Node.TEXT_NODE) {
				return child.getNodeValue().trim();
			}
		}
		return "";
	}

	static Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is); 

		} catch (ParserConfigurationException e) {
			return null;
		} catch (SAXException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return doc;
    }
}
