package com.axonivy.connector.srf.weather.test.utils;

import com.axonivy.connector.srf.weather.connector.WeatherMock;
import com.axonivy.connector.srf.weather.test.constant.SrfWeatherConnectorConstant;

import ch.ivyteam.ivy.environment.AppFixture;

public class SrfWeatherConnectorUtils {

	public static void setUpConfigForContext(String contextName, AppFixture fixture) {
		switch (contextName) {
			case SrfWeatherConnectorConstant.REAL_CALL_CONTEXT_DISPLAY_NAME:
				setUpConfigForApiTest(fixture);
				break;
			case SrfWeatherConnectorConstant.MOCK_SERVER_CONTEXT_DISPLAY_NAME:
				setUpConfigForMockServer(fixture);
				break;
			default:
				break;
		}
	}

	private static void setUpConfigForApiTest(AppFixture fixture) {
		String url = System.getProperty(SrfWeatherConnectorConstant.URL);
		String token = System.getProperty(SrfWeatherConnectorConstant.TOKEN);
		fixture.var("SrfWeatherConnector.Url", url);
		fixture.var("SrfWeatherConnector.Token", token);
	}

	private static void setUpConfigForMockServer(AppFixture fixture) {
		fixture.config("RestClients.'srfweatherService (SRF Weather)'.Url", WeatherMock.URI);
	}
}
