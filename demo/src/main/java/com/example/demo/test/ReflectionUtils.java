package com.example.demo.test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

@Component
public class ReflectionUtils {

	public double getObjectFieldNameAndVale(String fieldName, Parameter parameter) {
		double fieldValue = 0;
		try {
			Object value = new PropertyDescriptor(fieldName, Parameter.class).getReadMethod().invoke(parameter);
			return Double.parseDouble(value.toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			e.printStackTrace();
		}
		return fieldValue;
	}
	
	public void setObjectFieldNameAndVale(String fieldName,Object value, Parameter parameter) {
		PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(parameter);
		// a `setSomeProperty()` method will be used
		myAccessor.setPropertyValue(fieldName, value);
		
	}
	public List<String> getParameterFieldNameList() {
		List<String> fieldNameList = new ArrayList<>();
		try {
			Field[] fields = Parameter.class.getDeclaredFields();
			for (Field field : fields) {
				fieldNameList.add(field.getName());
			}

		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return fieldNameList;

	}

}
