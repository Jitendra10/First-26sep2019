package com.example.demo.json;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class VehicleDataTest {

//	private static String[] paramNames = new String [] {"id","parameter1","parameter2","parameter3","parameter4","parameter5","parameter6","parameter7"};
	private static Set<String> paramNames = null;
	
	private JSONObject getVehicleDataSum(JSONObject resultBucket, List<VehicleData> totalVehicleDataList) {
		if (null == totalVehicleDataList || totalVehicleDataList.isEmpty())
			return null;
		if (resultBucket == null)
			resultBucket = new JSONObject();
		for (VehicleData vehicleData : totalVehicleDataList) {
			if(paramNames == null) {
				paramNames = new JSONObject(vehicleData).keySet();
			}
			JSONObject jsonObject = new JSONObject(vehicleData);
			for (String name : paramNames) {
				try {
					Object existValue = resultBucket.get(name);
					if (!(existValue instanceof String)) {
						double value = (resultBucket.getDouble(name)) + jsonObject.getDouble(name);
						resultBucket.put(name, value);
					}
				} catch (Exception e) {
					resultBucket.put(name, jsonObject.get(name));
				}

			}
		}

		return resultBucket;
	}

	public VehicleData aggregateVehicleDataSum(List<VehicleData> totalVehicleData) {
		VehicleData finalVehicleData = new VehicleData();
		JSONObject resultBucket = getVehicleDataSum(null, totalVehicleData);
		ObjectMapper mapper = new ObjectMapper();
		try {
			finalVehicleData = mapper.readValue(resultBucket.toString(), VehicleData.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return finalVehicleData;
	}

	public static void main(String[] args) {
		VehicleDataTest vehicleDataTest = new VehicleDataTest();
		// preparing vehicleData
		VehicleData p1 = new VehicleData("VU09897","volvo1",10, 5, 15, 20, 5, 25, 5);
		VehicleData p2 = new VehicleData("VU09897","volvo1",40, 27, 82, 41, 34, 72, 5);
		VehicleData p3 = new VehicleData("VU09897","volvo1",20, 47, 32, 71, 24, 82, 5);
		VehicleData p4 = new VehicleData("VU09897","volvo1",30, 77, 12, 21, 54, 32, 5);
		VehicleData p5 = new VehicleData("VU09897","volvo1",70, 37, 42, 51, 84, 12, 5);
		VehicleData p6 = new VehicleData("VU09897","volvo1",10, 87, 72, 31, 74, 22, 5);

		List<VehicleData> vehicleDataList = Stream.of(p1, p2, p3, p4, p5, p6).toList();

		VehicleData finalVehicleData = vehicleDataTest.aggregateVehicleDataSum(vehicleDataList);
		System.out.println("Result Sum:: \n" + finalVehicleData);

	}

}
