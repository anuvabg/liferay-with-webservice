package com.lr.wsrest.calcservice.api;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CalculatorRestInvokeServiceImpl implements CalculatorRestInvokeService {

	private Log _logger = LogFactoryUtil.getLog(CalculatorRestInvokeServiceImpl.class);
	@Override
	public String getCalculation(String operation, String param1, String param2) {
		HttpURLConnection conn = null;
		StringBuffer responseBuffer = new StringBuffer();
		try {
			URL url = new URL(getURL(operation, param1, param2));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "*/*");
			_logger.debug("CalculatorRestInvokeServiceImpl: conn.getResponseCode(): "+conn.getResponseCode());
			if(HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				while ((inputLine = br.readLine()) != null) {
					responseBuffer.append(inputLine);
				}
				br.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseBuffer.toString();
	}
	
	private String getURL(String operation, String param1, String param2) {
		StringBuilder urlBuilder = new StringBuilder("http://localhost:8080/o/calculator-rest");
		if(Validator.isNotNull(operation) && Validator.isNotNull(param1) && Validator.isNotNull(param2)) {
			switch (operation) {
			case "sum":
				urlBuilder.append(StringPool.SLASH+"sum");
				break;
			case "sub":
				urlBuilder.append(StringPool.SLASH+"sub");
				break;
			default:
				break;
			}
			urlBuilder.append(StringPool.QUESTION+"param1"+StringPool.EQUAL+param1);
			urlBuilder.append(StringPool.AMPERSAND+"param2"+StringPool.EQUAL+param2);
		}
		_logger.info("CalculatorRestInvokeServiceImpl : getURL : Service URL with params: " + urlBuilder);
		return urlBuilder.toString();
	}

}
