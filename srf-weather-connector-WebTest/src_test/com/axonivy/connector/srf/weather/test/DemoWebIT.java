package com.axonivy.connector.srf.weather.test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.connector.srf.weather.connector.WeatherMock;
import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;

import ch.ivyteam.ivy.environment.AppFixture;

/**
 * This sample WebTest orchestrates a real browser to verify that your workflow
 * application and especially it's Html Dialogs are running as expected.
 * 
 * <p>
 * The test can either be run
 * <ul>
 * <li>in the Designer IDE ( <code>right click > run as > JUnit Test </code>
 * )</li>
 * <li>or in a Maven continuous integration build pipeline (
 * <code>mvn clean verify</code> )</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Detailed guidance on writing these kind of tests can be found in our <a href=
 * "https://developer.axonivy.com/doc/10.0/concepts/testing/web-testing.html">WebTesting
 * docs</a>
 * </p>
 */
@IvyWebTest(headless = false)
public class DemoWebIT {

	@BeforeEach
	void beforeEach() {
	    open(EngineUrl.createProcessUrl("srf-weather-connector-WebTest/18A17804D90373B1/setMockConfig.ivp"));
	    open(EngineUrl.createProcessUrl("srf-weather-connector-demo/189FE26D94E3ECBA/start.ivp"));
	}
	
	@AfterEach
	void afterEach() {
	    open(EngineUrl.createProcessUrl("srf-weather-connector-WebTest/18A17804D90373B1/cleanupMockConfig.ivp"));
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