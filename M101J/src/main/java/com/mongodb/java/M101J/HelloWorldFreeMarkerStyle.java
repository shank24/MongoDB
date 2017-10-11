package com.mongodb.java.M101J;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public class HelloWorldFreeMarkerStyle {

	public static void main(String[] args)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {

		@SuppressWarnings("deprecation")
		Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");


	    
		try {
			Template helloTemp = conf.getTemplate("resources/hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "TTN");

			helloTemp.process(helloMap, writer);
			System.out.println(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

