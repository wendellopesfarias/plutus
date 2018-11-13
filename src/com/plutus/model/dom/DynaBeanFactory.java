package com.plutus.model.dom;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DynaBeanFactory {

	private DynaBeanFactory() {
	};

	public static DynaBean newInstance(Map<String, String> attributeMap) {

		DynaBean db = null;
		HashMap<String, DynaBeanAttribute> attributes = new HashMap<>();

		attributeMap.forEach((x, y) -> {

			DynaBeanAttribute attribute;
			try {
				attribute = new DynaBeanAttribute(x, Class.forName(y));
				attributes.put(x, attribute);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		db = new DynaBean("Pessoa", attributes);

		return db;
	}
	
	public static DynaBean newInstance(String name,Map<String, String> attributeMap) {

		DynaBean db = null;
		HashMap<String, DynaBeanAttribute> attributes = new HashMap<>();

		attributeMap.forEach((x, y) -> {

			DynaBeanAttribute attribute;
			try {
				attribute = new DynaBeanAttribute(x, Class.forName(y));
				attributes.put(x, attribute);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		db = new DynaBean(name, attributes);

		return db;
	}

	public static DynaBean newInstance(String namePropertyFile) {

		DynaBean db = null;
		HashMap<String, DynaBeanAttribute> attributes = new HashMap<>();
		try {

			Properties p = new Properties();
			FileInputStream fis = new FileInputStream(namePropertyFile);
			p.load(fis);
			fis.close();

			String attName, attType;

			Enumeration list = p.propertyNames();
			while (list.hasMoreElements()) {
				attName = (String) list.nextElement();
				attType = p.getProperty(attName);

				DynaBeanAttribute attribute = new DynaBeanAttribute(attName, Class.forName(attType));

				attributes.put(attName, attribute);

			}

			db = new DynaBean("pessoa", attributes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return db;
	}

}
