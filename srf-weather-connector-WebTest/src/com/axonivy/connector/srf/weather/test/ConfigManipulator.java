package com.axonivy.connector.srf.weather.test;

import com.axonivy.connector.srf.weather.connector.WeatherMock;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IApplicationInternal;

@SuppressWarnings("restriction")
public class ConfigManipulator {
	private static final String MOCK_REST_CONFIG = "RestClients.'srfweatherService (SRF Weather)'.Url";

	public static void setMockConfig() {
		((IApplicationInternal) IApplication.current()).getConfiguration()
			.set(MOCK_REST_CONFIG, WeatherMock.URI);
	}
	
	public static void cleanupMockConfig() {
		((IApplicationInternal) IApplication.current()).getConfiguration()
			.remove(MOCK_REST_CONFIG);
	}
}
