package com.axonivy.connector.srf.weather.test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.connector.srf.weather.connector.WeatherMock;
import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.axonivy.ivy.webtest.engine.WebAppFixture;

import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@IvyWebTest
public class DemoWebIT {

    private static final String MOCK_REST_CONFIG = "RestClients.'srfweatherService (SRF Weather)'.Url";

	public static void assertCurrentUrlContains(String contains) {
		webdriver().shouldHave(urlContaining(contains));
	}

	@BeforeEach
	void beforeEach(WebAppFixture fixture) {
		fixture.config(MOCK_REST_CONFIG, WeatherMock.URI);
		open(EngineUrl.createProcessUrl("srf-weather-connector-demo/189FE26D94E3ECBA/start.ivp"));
	}

	@AfterEach
	void afterEach(WebAppFixture fixture) {
		fixture.resetConfig(MOCK_REST_CONFIG);
	}

	@Test
	public void checkWeatherTitle() {
		$(By.id("form:dataZip")).sendKeys("6300");
		$(By.id("form:getWeatherButton")).click();
		$(By.id("form:noMatchMsg")).shouldBe(hidden);
		$(By.id("form:headerWeatherResult")).shouldHave(exactText("Zug"));
	}

	@Test
	public void checkWarningMsg() {
		$(By.id("form:dataZip")).sendKeys("6301456");
		$(By.id("form:getWeatherButton")).click();
		$(By.id("form:noMatchMsg")).shouldBe(visible, text("No matching city found"));
	}

}