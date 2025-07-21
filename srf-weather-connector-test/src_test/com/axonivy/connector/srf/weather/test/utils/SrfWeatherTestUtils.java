package com.axonivy.connector.srf.weather.test.utils;

import com.axonivy.connector.srf.weather.connector.WeatherMock;
import com.axonivy.connector.srf.weather.test.constant.SrfWeatherTestConstants;

import ch.ivyteam.ivy.environment.AppFixture;

public class SrfWeatherTestUtils {

	public static void setUpConfigForContext(String contextName, AppFixture fixture) {
		switch (contextName) {
			case SrfWeatherTestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME:
				setUpConfigForApiTest(fixture);
				break;
			case SrfWeatherTestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME:
				setUpConfigForMockServer(fixture);
				break;
			default:
				break;
		}
	}

	private static void setUpConfigForApiTest(AppFixture fixture) {
		String url = System.getProperty(SrfWeatherTestConstants.URL);
		String token = System.getProperty(SrfWeatherTestConstants.TOKEN);
		fixture.var("SrfWeatherConnector.Url", url);
		fixture.var("SrfWeatherConnector.Token", token);
	}

	private static void setUpConfigForMockServer(AppFixture fixture) {
		fixture.config("RestClients.'srfweatherService (SRF Weather)'.Url", WeatherMock.URI);
	}
}
