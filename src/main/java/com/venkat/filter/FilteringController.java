package com.venkat.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * Implementation of dynamic filtering 
 * @author venkateswaran.T
 */

@RestController
public class FilteringController {

	@GetMapping("filtering")
	public MappingJacksonValue getSomeBean() {
		SomeBean someBean = new SomeBean("field1", "field2", "field3");
		Set<String> filterFileds = new HashSet<>();
		filterFileds.add("field1");
		filterFileds.add("field2");
		MappingJacksonValue mapping = dynamicFiltering(someBean, filterFileds);
		return mapping;
	}

	@GetMapping("filteringList")
	public MappingJacksonValue getLstSomeBean() {
		List<SomeBean> asList = Arrays.asList(new SomeBean("field1", "field2", "field3"),
				new SomeBean("field1", "field2", "field3"));
		Set<String> filterFileds = new HashSet<>();
		filterFileds.add("field1");
		filterFileds.add("field3");
		MappingJacksonValue mapping = dynamicFiltering(asList, filterFileds);
		return mapping;
	}

	private MappingJacksonValue dynamicFiltering(Object someBean, Set<String> filterFields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filterFields);
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanfilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
}
