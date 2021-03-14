package com.sample.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.sample.dto.CityCodeDTO;
import com.sample.dto.CurrencyConversionDTO;
import com.sample.dto.IndiaPhoneDTO;
import com.sample.dto.LocationDTO;
import com.sample.dto.SalesDTO;
import com.sample.dto.SalesIncentiveDTO;
import com.sample.dto.TargetDataDTO;

@Service
public class IndiaPhoneService {
	
	public List<CityCodeDTO> allCityCodes(List<IndiaPhoneDTO> sourceList) {
		Set<String> cityList = new HashSet<>();
		List<CityCodeDTO> result = new ArrayList<>();

		sourceList.forEach(dto -> {
			cityList.add(dto.getCity());
		});

		for (String city : cityList) {

			findCityRecords(sourceList, city, result);
		}

		return result;
	}

	public List<CityCodeDTO> topTwoCityCodes(List<IndiaPhoneDTO> sourceList, String city) {
		List<CityCodeDTO> result = new ArrayList<>();
		List<CityCodeDTO> result2 = new ArrayList<>();

		findCityRecords(sourceList, city, result);

		Collections.sort(result);
		result2.add(result.get(0));
		result2.add(result.get(1));
		return result2;
	}

	private void findCityRecords(List<IndiaPhoneDTO> sourceList, String city, List<CityCodeDTO> result) {
		int cityCode = 0;
		int cityCodeCounter = 0;
		HashMap<Integer, CityCodeDTO> cityCodeRecords = new HashMap<>();

		ArrayList<Integer> tempList = new ArrayList<>();

		for (IndiaPhoneDTO dto : sourceList) {

			if (city.equalsIgnoreCase(dto.getCity())) {
				cityCode = Integer.parseInt(dto.getPhoneNo().substring(0, 2));

				if (useLoopSearch(tempList, cityCode)) {
					CityCodeDTO dto2 = cityCodeRecords.get(cityCode);
					cityCodeCounter = dto2.getRecords();
				} else {
					cityCodeCounter = 0;
					tempList.add(cityCode);
				}

				cityCodeCounter++;

				CityCodeDTO cityCodeDTO = new CityCodeDTO();
				cityCodeDTO.setCity(city);
				cityCodeDTO.setState(dto.getState());
				cityCodeDTO.setCountry(dto.getCountry());
				cityCodeDTO.setCityCode(cityCode);
				cityCodeDTO.setRecords(cityCodeCounter);
				cityCodeRecords.put(cityCode, cityCodeDTO);
			}

		}

		cityCodeRecords.forEach((k, v) -> {
			result.add(v);
		});
	}
	
	public static boolean useLoopSearch(ArrayList<Integer> list, int targetValue) {
		for (int i : list) {
			if (i == targetValue)
				return true;
		}
		return false;
	}

	public List<TargetDataDTO> transformData(List<SalesIncentiveDTO> salesIncentives, List<SalesDTO> salesData,
			List<LocationDTO> locations, List<CurrencyConversionDTO> currConversion) {
		List<TargetDataDTO> result = new ArrayList<>();
		
		for(SalesDTO dto : salesData) {
			TargetDataDTO target = new TargetDataDTO();
			
			target.setSalesPersonID(dto.getSalesPersonID());
			target.setRegion(dto.getRegion());
			target.setCountry(dto.getCountry());
			target.setMonth(dto.getMonthYear());
			
			String baseCurrAmt = dto.getSalesAmtBaseCurr();
			target.setSalesAmtBaseCurr(baseCurrAmt);
			
			String curr = (baseCurrAmt.substring(baseCurrAmt.length() - 3)).equals("CAD") ? "EUR": (baseCurrAmt.substring(baseCurrAmt.length() - 3));
			Optional<CurrencyConversionDTO> optional =currConversion.stream().filter(obj -> (obj.getCurrPair().indexOf(curr) != -1)).findFirst();
			CurrencyConversionDTO currDTO = optional.get();
			double amt = Integer.parseInt(baseCurrAmt.substring(0,baseCurrAmt.length() - 3));
			int amtInUSD = (int)Math.round(amt/Double.parseDouble(currDTO.getExchRate()));
			target.setSalesAmtUSD(String.valueOf(amtInUSD+"USD"));
			
			Optional<SalesIncentiveDTO> optional2 =salesIncentives.stream().filter(o -> o.getSalesPersonLevelRegionWise().equals(dto.getSalesPersonLevelRegionWise())).findFirst();
			SalesIncentiveDTO salesIncentiveDTO = optional2.get();
			String formula = salesIncentiveDTO.getSalesIncentiveFormula();
			String salesIncentivesStr = "";
			int f = 0;
			int f1 = 0;
			if("(20% of Sales Amt Base Currency or 20USD whichever is less)".equals(formula)) {
				f = (int) Math.round(0.2*amtInUSD);
				f1 = (int) Math.round(0.2*amt);
				salesIncentivesStr = (f<20) ? f1+curr : "20USD" ;
			}else if("(30% of Sales Amt Base Currency or 30 USD whichever is less)".equals(formula)) {
				f = (int) Math.round(0.3*amtInUSD);
				f1 = (int) Math.round(0.3*amt);
				salesIncentivesStr = (f<30) ? f1+curr : "30USD" ;	
			}else if("(2% of Sales Amt USD or 20USD whichever is less)".equals(formula)) {
				f = (int) Math.round(0.02*amtInUSD);
				salesIncentivesStr = (f<20) ? f+curr : "20USD" ;	
			}else if("(3% of Sales Amt USD or 30USD whichever is less)".equals(formula)) {
				f = (int) Math.round(0.03*amtInUSD);
				salesIncentivesStr = (f<30) ? f+curr : "30USD" ;	
			}
			target.setSalesIncentive(salesIncentivesStr);
			
			Optional<LocationDTO> optional3 =locations.stream().filter(z -> (z.getCityName().equals(dto.getCity()) || dto.getCity().substring(0,3).equals(z.getCityAbbr()) )).findFirst();
			LocationDTO locationDTO = null;
			if(optional3.isEmpty())
				locationDTO = new LocationDTO();
			else
				locationDTO = optional3.get();
			target.setCountryRank(locationDTO.getCountryAbbr());
			
			if(dto.getCity().length()>3)
				target.setCity(dto.getCity());
			else
				target.setCity(locationDTO.getCityName());
			
			result.add(target);
		}
		
		Collections.sort(result,new Comparator<TargetDataDTO>() {

			@Override
			public int compare(TargetDataDTO o1, TargetDataDTO o2) {
				int a = Integer.parseInt(o1.getSalesAmtUSD().substring(0,o1.getSalesAmtUSD().length() - 3));
				int b = Integer.parseInt(o2.getSalesAmtUSD().substring(0,o2.getSalesAmtUSD().length() - 3));
				return b-a;
			}
			
		});
		int rankCounter = 0;
		for(TargetDataDTO d : result) {
			rankCounter++;
			d.setGlobalRank("G"+rankCounter);
		}
		
		Collections.sort(result, new Comparator<TargetDataDTO>() {

			@Override
			public int compare(TargetDataDTO o1, TargetDataDTO o2) {
				int a = Integer.parseInt(o1.getSalesAmtUSD().substring(0,o1.getSalesAmtUSD().length() - 3));
				int b = Integer.parseInt(o2.getSalesAmtUSD().substring(0,o2.getSalesAmtUSD().length() - 3));
				int c = o1.getRegion().compareTo(o2.getRegion());
				if(c!=0)
					return c;
				
				return b-a;
			}
		});
		rankCounter = 0;
		String temp="";
		for(TargetDataDTO d : result) {
			if(!temp.equals(d.getRegion())) {
				rankCounter = 0;
			}
			temp = d.getRegion();
			rankCounter++;
			d.setRegionRank(temp+rankCounter);
		}
		
		Collections.sort(result, new Comparator<TargetDataDTO>() {

			@Override
			public int compare(TargetDataDTO o1, TargetDataDTO o2) {
				int a = Integer.parseInt(o1.getSalesAmtUSD().substring(0,o1.getSalesAmtUSD().length() - 3));
				int b = Integer.parseInt(o2.getSalesAmtUSD().substring(0,o2.getSalesAmtUSD().length() - 3));
				int c = o1.getCountryRank().compareTo(o2.getCountryRank());
				if(c!=0)
					return c;
				
				return b-a;
			}
		});
		
		rankCounter = 0;
		temp="";
		for(TargetDataDTO d : result) {
			if(!temp.equals(d.getCountryRank())) {
				rankCounter = 0;
			}
			temp = d.getCountryRank();
			rankCounter++;
			d.setCountryRank(temp+rankCounter);
		}
		
		return result;
	}
}
