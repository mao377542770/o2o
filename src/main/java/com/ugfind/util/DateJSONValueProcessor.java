package com.ugfind.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJSONValueProcessor implements JsonValueProcessor {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private DateFormat dateFormat;
    
    public DateJSONValueProcessor() {
        dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
    }

    public DateJSONValueProcessor(String datePattern) {
        try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception e) {
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }

    private Object process(Object value) {
        if (value == null) {
            return "";
        }
        return dateFormat.format((Date) value);
    }

}
