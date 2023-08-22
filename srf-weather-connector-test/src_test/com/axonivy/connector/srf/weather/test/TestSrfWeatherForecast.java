package com.axonivy.connector.srf.weather.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.connector.srf.weather.connector.WeatherMock;
import com.axonivy.connector.srf.weather.connector.SrfWeatherForecastData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest
class TestSrfWeatherForecast {

	private static final BpmProcess testee = BpmProcess.path("srfWeatherForecast");

	@BeforeEach
	void beforeEach(AppFixture fixture) {
		fixture.config("RestClients.'srfweatherService (SRF Weather)'.Url", WeatherMock.URI);
	}

	@Test
	void locationAndWeatherOfZip(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = testee.elementName("call(Number,String)");
		ExecutionResult result = bpmClient.start().subProcess(startable).execute(6300, "");
		SrfWeatherForecastData data = result.data().last();
		assertThat(data.getDay().getTNC()).isEqualTo(18);
		assertThat(data.getLocation().getLocationName()).isEqualTo("Zug");
	}
}
