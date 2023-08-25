package com.example.demo.paramsum;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Stream;

public class VehicleDataTest {

	private ReflectionUtils reflectionUtils = new ReflectionUtils();

	
	private Hashtable<String, Object> getVehicleDataSum(Hashtable<String, Object> resultBucket,
			List<VehicleData> totalVehicleDataList) {
		if (null == totalVehicleDataList || totalVehicleDataList.isEmpty())
			return null;
		if (resultBucket == null)
			resultBucket = new Hashtable<>();
		List<String> fieldConstatnts = reflectionUtils.getParameterFieldNameList(); // as parameter may varied
		for (VehicleData vehicleData : totalVehicleDataList) {
			// modified forloop to accept string value
			for (String paramKey : fieldConstatnts) {
				Object paramValue = reflectionUtils.getVehicleValueByFieldName(paramKey, vehicleData);
				if (paramValue != null) {
					if (paramValue.getClass().getSimpleName().equalsIgnoreCase("String"))
						resultBucket.put(paramKey, paramValue);
					else {
						Double value = Double.parseDouble(paramValue.toString());
						if (resultBucket.containsKey(paramKey)) {
							Double newValue = Double.parseDouble(resultBucket.get(paramKey).toString()) + value;
							resultBucket.put(paramKey, newValue);
						} else {
							resultBucket.put(paramKey, value);
						}
					}
				}
			}
		}
		return resultBucket;
	}

	//
	public VehicleData aggregateVehicleDataSum(List<VehicleData> totalVehicleData) {
		VehicleData finalVehicleData = new VehicleData();
		Hashtable<String, Object> resultBucket = getVehicleDataSum(null, totalVehicleData);
		for (var entry1 : resultBucket.entrySet()) {
			reflectionUtils.setVehicleFieldldNameAndValue(entry1.getKey(), entry1.getValue(), finalVehicleData);
		}
		return finalVehicleData;
	}

	public static void main(String[] args) {
		VehicleDataTest vehicleDataTest = new VehicleDataTest();
		// preparing vehicleData
		VehicleData p1 = new VehicleData(10, 5, 15, 20, 5, 25, 5);
		p1.setId("VU09897");
		VehicleData p2 = new VehicleData(40, 27, 82, 41, 34, 72, 5);
		VehicleData p3 = new VehicleData(20, 47, 32, 71, 24, 82, 5);
		VehicleData p4 = new VehicleData(30, 77, 12, 21, 54, 32, 5);
		VehicleData p5 = new VehicleData(70, 37, 42, 51, 84, 12, 5);
		VehicleData p6 = new VehicleData(10, 87, 72, 31, 74, 22, 5);
		p6.setId("VU06597");

		List<VehicleData> vehicleDataList = Stream.of(p1, p2, p3, p4, p5, p6).toList();

		VehicleData finalVehicleData = vehicleDataTest.aggregateVehicleDataSum(vehicleDataList);
		System.out.println("Result Sum:: \n" + finalVehicleData);

	}

}
