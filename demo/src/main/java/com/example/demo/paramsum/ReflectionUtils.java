package com.example.demo.paramsum;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

@Component
public class ReflectionUtils {

	/// Reflection Utils codes
	public List<String> getParameterFieldNameList() {
		List<String> fieldNameList = new ArrayList<>();
		try {
			Field[] fields = VehicleData.class.getDeclaredFields();
			for (Field field : fields) {
				fieldNameList.add(field.getName());
			}
		} catch (Exception e) {
			// No Impl available
		}
		return fieldNameList;
	}

	//return type changed to object to accept primitive and object type value
	public Object getVehicleValueByFieldName(String fieldName, VehicleData parameter) {
		Object value =null;
		try {
			value = new PropertyDescriptor(fieldName, VehicleData.class).getReadMethod().invoke(parameter);
		} catch (Exception e) {
			// No Impl available
		}
		return value;
	}

	public  void setVehicleFieldldNameAndValue(String fieldName, Object value, VehicleData parameter) {
		PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(parameter);
		// a `setSomeProperty()` method will be used
		myAccessor.setPropertyValue(fieldName, value);
	}

}
