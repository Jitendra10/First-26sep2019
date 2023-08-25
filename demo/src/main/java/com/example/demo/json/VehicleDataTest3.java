package com.example.demo.json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class VehicleDataTest3 {

	private static List<String> paramNames = new ArrayList<>() ;
	private static Gson gson = new Gson();
	private static ObjectMapper mapper = new ObjectMapper();
	
	private JsonObject getVehicleDataSum(JsonObject resultBucket, List<VehicleData> totalVehicleDataList) {
		if (null == totalVehicleDataList || totalVehicleDataList.isEmpty())
			return null;
		if (resultBucket == null)
			resultBucket = new JsonObject();
		
		for (VehicleData vehicleData : totalVehicleDataList) {
			String inputJsonData = gson.toJson(vehicleData);
			if(paramNames.isEmpty()) {
				try {
				mapper.readTree(inputJsonData).fieldNames().forEachRemaining(paramNames::add);
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			JsonObject JsonObject = JsonParser.parseString(inputJsonData).getAsJsonObject();
			for (String name : paramNames) {
				try {
					Object existValue = resultBucket.get(name);
					if (existValue!=null && !(existValue instanceof String)) {
						double value = (resultBucket.get(name).getAsDouble()) + JsonObject.get(name).getAsDouble();
						resultBucket.addProperty(name, value);
					}else {
						resultBucket.add(name, resultBucket);
					}
				} catch (Exception e) {
					resultBucket.add(name, resultBucket);
				}

			}
		}

		return resultBucket;
	}

	public VehicleData aggregateVehicleDataSum(List<VehicleData> totalVehicleData) {
		VehicleData finalVehicleData = new VehicleData();
		JsonObject resultBucket = getVehicleDataSum(null, totalVehicleData);
		ObjectMapper mapper = new ObjectMapper();
		try {
			finalVehicleData = mapper.readValue(resultBucket.toString(), VehicleData.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return finalVehicleData;
	}

	public static void main(String[] args) {
		VehicleDataTest3 vehicleDataTest = new VehicleDataTest3();
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
