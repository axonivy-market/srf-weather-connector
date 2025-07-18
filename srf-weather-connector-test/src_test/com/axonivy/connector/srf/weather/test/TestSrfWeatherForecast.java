package com.axonivy.connector.srf.weather.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.connector.srf.weather.test.constant.SrfWeatherTestConstants;
import com.axonivy.connector.srf.weather.test.context.MultiEnvironmentContextProvider;
import com.axonivy.connector.srf.weather.test.utils.SrfWeatherTestUtils;
import com.axonivy.connector.srf.weather.connector.SrfWeatherForecastData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest(enableWebServer = true)
@ExtendWith(MultiEnvironmentContextProvider.class)
class TestSrfWeatherForecast {

	private static final BpmProcess testee = BpmProcess.path("srfWeatherForecast");

	@BeforeEach
	void beforeEach(ExtensionContext context, AppFixture fixture) {
		SrfWeatherTestUtils.setUpConfigForContext(context.getDisplayName(), fixture);
	}

	@TestTemplate
	void locationAndWeatherOfZip(ExtensionContext context, BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = testee.elementName("call(Number,String)");
		ExecutionResult result = bpmClient.start().subProcess(startable).execute(6300, "Zug");
		SrfWeatherForecastData data = result.data().last();
		if (context.getDisplayName().equals(SrfWeatherTestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME)) {
			assertThat(data.getDay().getTNC()).isEqualTo(18);
		} else {
			assertThat(data.getLocation().getZip()).isEqualTo(6300);
		}
		assertThat(data.getLocation().getLocationName()).isEqualTo("Zug");
	}
}
