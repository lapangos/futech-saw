package com.futech.saw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;

import com.futech.saw.request.RequestWrapper;
import com.futech.saw.response.GenericShareResponse;
import com.futech.saw.response.ResponseWrapper;
import com.futech.saw.service.ShareDetailsService;

public class ShareDetailsServiceImpl implements ShareDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(ShareDetailsServiceImpl.class);

	@Autowired
	GenericRestClient restClientService;

	@Override
	public GenericShareResponse process(String id) {

		try {
			RequestWrapper requestWrapper = new RequestWrapper();
			ResponseWrapper responseWrapper = restClientService.callWebService(requestWrapper);

			Gson gson = new Gson();
			System.out.println("responseWrapper :: " + gson.toJson(responseWrapper));
			LOG.debug("responseWrapper :: " + gson.toJson(responseWrapper));
			GenericShareResponse GenericShareResponse = new GenericShareResponse();
			return GenericShareResponse;
		} catch (Exception e) {
			LOG.error("Error Message : ");
			e.printStackTrace();
		}

		return null;
	}

}
