package com.axonivy.connector.srf.weather.test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import static com.axonivy.ivy.webtest.engine.EngineUrl.isDesigner;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@IvyWebTest
public class DemoWebIT {

	public static void assertCurrentUrlContains(String contains) {
		webdriver().shouldHave(urlContaining(contains));
	}

	@BeforeEach
	void beforeEach() {
		open(EngineUrl.createProcessUrl("srf-weather-connector-webTest/18A17804D90373B1/setMockConfig.ivp"));
		assertCurrentUrlContains(isDesigner() ? "/default-workflow/faces" : "end");
		open(EngineUrl.createProcessUrl("srf-weather-connector-demo/189FE26D94E3ECBA/start.ivp"));
	}

	@AfterEach
	void afterEach() {
		open(EngineUrl.createProcessUrl("srf-weather-connector-webTest/18A17804D90373B1/cleanupMockConfig.ivp"));

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