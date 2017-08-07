package com.toy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {
	String pattern ="yyyy-MM-dd";
	
	public JsonDateValueProcessor() {
		super();
	}

	public JsonDateValueProcessor(String pattern) {
		super();
		this.pattern = pattern;
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		return this.process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return this.process(value);
	}
	public Object process(Object value){
		if(value instanceof Date){
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(value);
		}
		return value==null?"":value.toString();
	}
}
