package com.futech.saw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.futech.saw.request.RequestWrapper;
import com.futech.saw.response.ResponseWrapper;
import com.futech.saw.util.CommonRoutines;
import com.futech.saw.util.ResourceConstants;

@Service
@Scope("prototype")
public class GenericRestClient extends ResourceConstants {

	private static final Logger LOG = LoggerFactory.getLogger(GenericRestClient.class);

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Value("${spring.yahooApi.uri}")
	private String yahooApi;

	public RestTemplate getRestTemplate(String userName, String password) {

		if (CommonRoutines.isNullOrBlank(userName)) {
			return restTemplateBuilder.build();
		} else {
			return restTemplateBuilder.basicAuthentication(userName, password).build();
		}
	}

	public ResponseWrapper callWebService(RequestWrapper requestWrapper) {

		/*
		 * GET /market/v2/get-quotes?region=US&symbols=AMD%2CIBM%2CAAPL HTTP/1.1
		 */

		ResponseWrapper response = null;

		int count = 1;

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//		headers.add("Content-Type", APPLICATION_JSON);
//		headers.add("Accept", APPLICATION_JSON);
		headers.add("x-rapidapi-key", "810f13fca5msh0c33a567ca3782cp167774jsn6f0257864eb5");
		headers.add("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
		HttpEntity<Object> requestEntity = new HttpEntity<>(requestWrapper, headers);

		try {

			Long startTime = System.currentTimeMillis();

			ResponseEntity<ResponseWrapper> postResponseEntity = this.getRestTemplate(null, null).exchange(yahooApi,
					HttpMethod.GET, requestEntity, ResponseWrapper.class);

			Long endTime = System.currentTimeMillis();

			LOG.debug("Time taken: {}ms. Processing the response...", (endTime - startTime));

			if (postResponseEntity != null && postResponseEntity.getStatusCodeValue() == 200) {
				response = postResponseEntity.getBody();
			}

		} catch (Exception ex) {
			LOG.error("Retry count: {}", count);
		}

		return response;
	}

}
