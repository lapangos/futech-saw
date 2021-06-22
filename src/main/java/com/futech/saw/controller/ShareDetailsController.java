package com.futech.saw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.futech.saw.response.GenericShareResponse;
import com.futech.saw.service.ShareDetailsService;

@RestController
public class ShareDetailsController {

	public static final Logger logger = LoggerFactory.getLogger(ShareDetailsController.class);

	@Autowired
	ShareDetailsService shareDetailsService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
	public GenericShareResponse getShareDetailsById(@PathVariable String id) {
		logger.info("Start of getShareDetailsById : ");
		GenericShareResponse response = shareDetailsService.process(id);
		logger.info("End of getShareDetailsById : ");
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
	public GenericShareResponse getShareDetailsByName(@PathVariable String name) {
		logger.info("Start of getShareDetailsByName : ");
		GenericShareResponse response = new GenericShareResponse();
		logger.info("End of getShareDetailsByName : ");
		return response;
	}
}
