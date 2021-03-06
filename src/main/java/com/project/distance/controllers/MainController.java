package com.project.distance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.distance.builders.ResponseBuilder;
import com.project.distance.models.CitiesList;
import com.project.distance.services.ICitiesService;

/**
 * This is the main controller
 * 
 * @author Diego Francklin Martins dos Santos
 */
@RestController
public class MainController {

	@Autowired
	private ICitiesService service;

	/**
	 * Answers the request with a list of cities, combined
	 * in pairs and with the distance between them
	 * 
	 * @param unity
	 * @param mediaType
	 * 
	 * @return ResponseEntity<CitiesList>
	 * 
	 * @throws Exception
	 */
	@RequestMapping(
		value="/{unity}/{mediaType}",
		method=RequestMethod.GET,
		produces={
			MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE
		}
	)
	public @ResponseBody ResponseEntity<CitiesList> index(
		@PathVariable String unity,
		@PathVariable String mediaType
	) throws Exception {
		CitiesList cityList = service.combinedCitiesWithDistance(unity);
		
		return new ResponseBuilder<CitiesList>()
			.setContentType(mediaType)
			.setBody(cityList)
			.build()
		;
	}

}
