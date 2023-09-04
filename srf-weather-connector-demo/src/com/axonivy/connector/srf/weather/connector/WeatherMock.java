package com.axonivy.connector.srf.weather.connector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

@Path(WeatherMock.PATH_SUFFIX)
@PermitAll
public class WeatherMock {
	static final String PATH_SUFFIX = "weatherMock";
	// URI where this mock can be reached: to be referenced in tests that use it!
	public static final String URI = "{ivy.app.baseurl}/api/" + PATH_SUFFIX;
	
	@GET
	@Path("geolocationNames")
	@Produces(MediaType.APPLICATION_JSON)
	public Response forecastpoint(@QueryParam("zip") int zip, @QueryParam("location") String location) throws IOException {
		 if (zip != 6300) {
	            // Return an empty JSON array
	            return Response.status(200).entity("[]").build();
	        }

	        try (InputStream is = WeatherMock.class.getResourceAsStream("json/geolocation.json")) {
	            var json = IOUtils.toString(is, StandardCharsets.UTF_8);
	            return Response.status(200).entity(json).build();
	        }
	}
	
	@GET
	@Path("forecastpoint/{geolocationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response forecastpoint(@PathParam("geolocationId") String geolocationId) throws IOException {
		try(InputStream is = WeatherMock.class.getResourceAsStream("json/forecast.json")) {
			var json = IOUtils.toString(is, StandardCharsets.UTF_8);
			return Response.status(200).entity(json).build();
		}
	}

}
