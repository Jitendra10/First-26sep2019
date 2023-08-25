package com.example.demo.test;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Stream;

public class ParameterTest {

	private static final String[] fieldConstatnts = new String[] { "parameter1", "parameter2", "parameter3",
			"parameter4", "parameter5", "parameter6", "parameter7" };
	
	private ReflectionUtils reflectionUtils = new ReflectionUtils();

	private Hashtable<String, Double> getParameterResultSum(Hashtable<String, Double> resultBucket,
			List<Parameter> parameterList) {
		if (null == parameterList || parameterList.isEmpty())
			return null;
		if (resultBucket == null)
			resultBucket = new Hashtable<>();
		
//		ReflectionUtils reflectionUtils = new ReflectionUtils();
//		List<String> fieldConstatnts = reflectionUtils.getParameterFieldNameList(); // if your field parameter may varied
		for (Parameter parameter : parameterList) {
			for (String paramKey : fieldConstatnts) {
				Double value = reflectionUtils.getObjectFieldNameAndVale(paramKey, parameter);
				if (resultBucket.containsKey(paramKey)) {
					Double newValue = resultBucket.get(paramKey) + value;
					resultBucket.put(paramKey, newValue);
				} else {
					resultBucket.put(paramKey, value);
				}
			}
		}

		return resultBucket;

	}

	
	
	// Test Code
	public void testParamSum() {
		Parameter p1 = new Parameter(10,5,15,20,5,25,5);
		Parameter p2 = new Parameter(40,27,82,41,34,72,5);
		Parameter p3 = new Parameter(20,47,32,71,24,82,5);
		Parameter p4 = new Parameter(30,77,12,21,54,32,5);
		Parameter p5 = new Parameter(70,37,42,51,84,12,5);
		Parameter p6 = new Parameter(10,87,72,31,74,22,5);

		
		List<Parameter> paramList1 =Stream.of(p1,p2,p3,p4,p5,p6).toList();
		
		//case1
		Parameter finalParam1 = new Parameter();
		Hashtable<String, Double> resultBucket1 = getParameterResultSum(null,paramList1);
		for(var entry1 : resultBucket1.entrySet()) {
			System.out.println(entry1.getKey() + " :: "+entry1.getValue());
			reflectionUtils.setObjectFieldNameAndVale(entry1.getKey(), entry1.getValue(), finalParam1);
		}
		
		System.out.println("================================");
		System.out.println(finalParam1);
		//case 2
		finalParam1 = new Parameter();
		System.out.println("=================================");
		List<Parameter> paramList2 =Stream.of(p1,p2,p3,p4,p5,p6).toList();
		List<Parameter> paramList3 =Stream.of(p1,p2,p3,p4,p5,p6).toList();
		List<List<Parameter>> paramLists = Stream.of(paramList1,paramList2,paramList3).toList();
		Hashtable<String, Double> resultBucket2 = null;
		
		for(List<Parameter> paramList : paramLists) {
			resultBucket2 = getParameterResultSum(resultBucket2,paramList);
		}
		
		for(var entry2 : resultBucket2.entrySet()) {
			System.out.println(entry2.getKey() + " :: "+entry2.getValue());
			reflectionUtils.setObjectFieldNameAndVale(entry2.getKey(), entry2.getValue(), finalParam1);
		}
		
		System.out.println("================================");
		System.out.println(finalParam1);
		
	}
	
	public static void main(String[] args) {
		ParameterTest parameterTest = new ParameterTest();
		parameterTest.testParamSum();
	}

	

}
