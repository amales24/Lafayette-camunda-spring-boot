package com.example.lafayette;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class FetchExchangeRate implements JavaDelegate{
	
	public double getExchangeRate(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://api.hnb.hr/tecajn-eur/v3?valuta=USD";
		
		ResponseEntity<List<HashMap<String,String>>> response = restTemplate.exchange(
			    url,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<HashMap<String,String>>>(){});
		
		List<HashMap<String,String>> objects = response.getBody();
		String exchangeRate = objects.get(0).get("srednji_tecaj").replace(",", ".");
		
		return Double.parseDouble(exchangeRate);
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		double exchangeRate = getExchangeRate();
		
		execution.setVariable("exchangeRate", exchangeRate);
	}

}
