package com.axonivy.connector.srf.weather.test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.axonivy.utils.e2etest.enums.E2EEnvironment.REAL_SERVER;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.connector.srf.weather.test.constant.SrfWeatherTestConstants;
import com.axonivy.utils.e2etest.context.MultiEnvironmentContextProvider;
import com.axonivy.utils.e2etest.utils.E2ETestUtils;
import com.axonivy.connector.srf.weather.connector.SrfWeatherForecastData;
import com.axonivy.connector.srf.weather.connector.WeatherMock;

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
    E2ETestUtils.determineConfigForContext(context.getDisplayName(), runRealEnv(fixture), runMockEnv(fixture));
  }

  @TestTemplate
  void locationAndWeatherOfZip(ExtensionContext context, BpmClient bpmClient) throws NoSuchFieldException {
    BpmElement startable = testee.elementName("call(Number,String)");
    ExecutionResult result = bpmClient.start().subProcess(startable).execute(6300, "Zug");
    SrfWeatherForecastData data = result.data().last();
    if (context.getDisplayName().equals(REAL_SERVER.getDisplayName())) {
      assertThat(data.getDay().getTNC()).isEqualTo(18);
    } else {
      assertThat(data.getLocation().getZip()).isEqualTo(6300);
    }
    assertThat(data.getLocation().getLocationName()).isEqualTo("Zug");
  }

  private Runnable runRealEnv(AppFixture fixture) {
    return () -> {
      String url = System.getProperty(SrfWeatherTestConstants.URL);
      String token = System.getProperty(SrfWeatherTestConstants.TOKEN);
      fixture.var("SrfWeatherConnector.Url", url);
      fixture.var("SrfWeatherConnector.Token", token);
    };
  }

  private Runnable runMockEnv(AppFixture fixture) {
    return () -> {
      fixture.config("RestClients.'srfweatherService (SRF Weather)'.Url", WeatherMock.URI);
    };
  }
}
