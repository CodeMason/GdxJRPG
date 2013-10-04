package com.jsandusky.jrpg.model;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.jsandusky.util.*;

public class XMLReader
{
	public static Database readDatabase(FileHandle xmlSrc) {
		Database ret = new Database();
		
		XmlReader rdr = new XmlReader();
		
		try {
			Element elem = rdr.parse(xmlSrc);
		} catch (Exception ex) {
			Gdx.app.log("GdxJRPG","readDatabase",ex);
		}
		
		return ret;
	}
	
	static void readDB(Element elem, Database data) {
		Array<Element> heroes = elem.getChildrenByNameRecursively("hero");
		for (Element hero : heroes) {
			Hero h = new Hero();
			fillObject(h,hero);
		}
		
		Array<Element> npcs = elem.getChildrenByNameRecursively("npc");
		
		Array<Element> party = elem.getChildrenByNameRecursively("party");
		
		Array<Element> shops = elem.getChildrenByNameRecursively("shop");
		
		Array<Element> skills = elem.getChildrenByNameRecursively("skill");
		
		Array<Element> classes = elem.getChildrenByNameRecursively("class");
		
		Array<Element> monsters = elem.getChildrenByNameRecursively("monster");
		
		Array<Element> troops = elem.getChildrenByNameRecursively("troops");
		
		Array<Element> items = elem.getChildrenByNameRecursively("item");
		
		Array<Element> equips = elem.getChildrenByNameRecursively("equip");
	}
	
	static void fillObject(Object tgt, Element elem) {
		for (int i = 0; i < elem.getChildCount(); ++i) {
			Element e = elem.getChild(i);
			String nm = e.getName();
			try {
				Field fld = tgt.getClass().getField(nm);
				if (fld.getType() == int.class) {
					fld.set(tgt,e.getIntAttribute("value"));
				} else if (fld.getType() == float.class) {
					fld.set(tgt,e.getFloatAttribute("value"));
				} else if (fld.getType() == String.class) {
					fld.set(tgt,e.getAttribute("value"));
				} else if (fld.getType() == boolean.class) {
					fld.set(tgt,e.getBooleanAttribute("value"));
				} else if (Reference.class.isAssignableFrom(fld.getType())) {
					Reference ref = (Reference)fld.get(fld);
					
				} else if (ArrayList.class.isAssignableFrom(fld.getType())) {
					ArrayList li = (ArrayList)fld.get(tgt);
					Ref annote = fld.getAnnotation(Ref.class);
					for (int x = 0; x < elem.getChildCount(); ++x) {
						Element ch = elem.getChild(x);
						if (annote.cl() == Integer.class) {
							li.add(ch.getIntAttribute("value"));
						} else if (annote.cl() == Float.class) {
							li.add(ch.getFloatAttribute("value"));
						} else {
							Object obj = annote.cl().newInstance();
							fillObject(obj,ch);
							li.add(obj);
						}
					}
					
				} else if (HashMap.class.isAssignableFrom(fld.getType())) {
					HashMap map = (HashMap)fld.get(tgt);
					RefTwin annote = fld.getAnnotation(RefTwin.class);
					for (int x = 0; x < elem.getChildCount(); ++x) {
						/*<item><key></key><value></value></item>*/
						Element ch = elem.getChild(x);
						
					}
				}
				
			} catch (Exception ex) {
				
			}
		}
	}
}
