package com.axonivy.connector.srf.weather.test.context;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import com.axonivy.connector.srf.weather.test.constant.SrfWeatherTestConstants;

public class MultiEnvironmentContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		return true;
	}

	@Override
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		String testEnv = System.getProperty(SrfWeatherTestConstants.END_TO_END_TESTING_ENVIRONMENT_KEY);
		return switch (testEnv) {
		case SrfWeatherTestConstants.END_TO_END_TESTING_ENVIRONMENT_VALUE ->
			Stream.of(new TestEnironmentInvocationContext(SrfWeatherTestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME));
		default ->
			Stream.of(new TestEnironmentInvocationContext(SrfWeatherTestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME));
		};
	}
}