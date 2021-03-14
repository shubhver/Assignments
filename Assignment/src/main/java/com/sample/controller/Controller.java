package com.sample.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.CityCodeDTO;
import com.sample.dto.CurrencyConversionDTO;
import com.sample.dto.IndiaPhoneDTO;
import com.sample.dto.LocationDTO;
import com.sample.dto.ResponseDTO;
import com.sample.dto.SalesDTO;
import com.sample.dto.SalesIncentiveDTO;
import com.sample.dto.TargetDataDTO;
import com.sample.service.IndiaPhoneService;

@RestController
public class Controller {
	
	@Autowired
	IndiaPhoneService indiaPhoneService;
	
	static List<IndiaPhoneDTO> sourceList = new ArrayList<IndiaPhoneDTO>();
	static List<SalesIncentiveDTO> salesIncentives = new ArrayList<SalesIncentiveDTO>();
	static List<SalesDTO> salesData = new ArrayList<SalesDTO>();
	static List<LocationDTO> locations = new ArrayList<LocationDTO>();
	static List<CurrencyConversionDTO> currConversion = new ArrayList<CurrencyConversionDTO>();

	static {
		String [] strArr = {"india_phoneno_not_null","CurrencyConversion","Location","SalesData","SalesIncentive"};
		for(String str : strArr) {
			extractData(str);			
		}
	}

	@RequestMapping(value = "/cityCodes",produces = { "application/json" })
	public ResponseDTO allCityCodes() {
		List<CityCodeDTO> list = indiaPhoneService.allCityCodes(sourceList);
		ResponseDTO dto = new ResponseDTO();
		dto.setStatus("Success");
		dto.setData(list);
		return dto;
	}
	
	@RequestMapping(value = "/topTwo",produces = { "application/json" })
	public ResponseDTO topTwoCityCodes(@RequestParam String city) {
		List<CityCodeDTO> list = indiaPhoneService.topTwoCityCodes(sourceList,city);
		ResponseDTO dto = new ResponseDTO();
		dto.setStatus("Success");
		dto.setData(list);
		return dto;
	}
	
	@RequestMapping(value = "/transformData",produces = { "application/json" })
	public ResponseDTO transformData() {
		List<TargetDataDTO> list = indiaPhoneService.transformData(salesIncentives,salesData,locations,currConversion);
		ResponseDTO dto = new ResponseDTO();
		dto.setStatus("Success");
		dto.setData(list);
		return dto;
	}
	
	private static void extractData(String fileName) {
		try {
			String line = "";
			BufferedReader bfr = new BufferedReader(new FileReader(fileName));
			while ((line = bfr.readLine()) != null) {
				String[] data = line.split("\\t+");
				
				if("india_phoneno_not_null".equals(fileName)) {
					IndiaPhoneDTO dto = new IndiaPhoneDTO();
					dto.setPhoneNo(data[0]);
					dto.setCity(data[1]);
					dto.setState(data[2]);
					dto.setCountry(data[3]);
					sourceList.add(dto);				
				}
				else if("CurrencyConversion".equals(fileName)) {
					CurrencyConversionDTO dto = new CurrencyConversionDTO();
					dto.setCurrPair(data[0]);
					dto.setExchRate(data[1]);
					currConversion.add(dto);
				}else if("Location".equals(fileName)) {
					LocationDTO dto = new LocationDTO();
					dto.setCityName(data[0]);
					dto.setCityAbbr(data[1]);
					dto.setCountryName(data[2]);
					dto.setCountryAbbr(data[3]);
					dto.setRegion(data[4]);
					locations.add(dto);
				}else if("SalesData".equals(fileName)) {
					SalesDTO dto = new SalesDTO();
					dto.setSalesPersonID(data[0]);
					dto.setRegion(data[1]);
					dto.setCountry(data[2]);
					dto.setCity(data[3]);
					dto.setSalesPersonLevelRegionWise(data[4]);
					dto.setSalesAmtBaseCurr(data[5]);
					dto.setMonthYear(data[6]);
					salesData.add(dto);
				}else if("SalesIncentive".equals(fileName)) {
					SalesIncentiveDTO dto = new SalesIncentiveDTO();
					dto.setRegion(data[0]);
					dto.setSalesPersonLevelRegionWise(data[1]);
					dto.setSalesIncentiveFormula(data[2]);
					salesIncentives.add(dto);
				}				
			}
			bfr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
