# srf-weather-connector

Axon Ivy's [SRF](https://developer.srgssr.ch/api-catalog/srf-weather) Weather data API
connector helps you query weather forecasts

The connector:

* provides various weather data such as next seven day forecasts and more.
* supports you with an easy-to-copy demo implementation to reduce your
  integration effort.
* enables low code citizen developers to enhance existing business processes
  with currency converter features.

## Demo

Install the SRF Weather Connector and call it as a sub-process. Adjust the mapping accordingly.
![set connector as subprocess](images/demo1.png)

In the demo, you can now specify either the name of the location or its ZIP code and use the "Get Weather" button to output the weather for the next seven days at this location.
![enter data name or zip](images/demo2.png)

If the location is valid, the corresponding output looks like this:
![final output](images/demo3.png)

## Setup

The use of the SRF Weather API is not free of charge. For development purposes there is a free version with minimal API calls.
To use the connector, you must select a suitable API package via the [SRF API Developer website](https://developer.srgssr.ch/api-catalog/srf-weather) and generate a "bearer token". 
This is described in the following instructions: [SRF instruction](https://developer.srgssr.ch/getting-started/easy-description-get-accesstoken)

After a Bearer Token is available, you can store it in the Connector project in the variable "srfWeatherConnector.Token"(under Config-->variables.yaml).
1. Register an account on [developer.srgssr.ch/](https://developer.srgssr.ch/).
2. Once logged in, click Apps on the top right and add a new app with the "+ ADD APP" button.
3. After the app is created, you will receive a Consumer Key and Consumer Secret with which you can generate the Bearer Token.
4. Store the API-Key/Token in your variables.yaml under `Variables.SrfWeatherConnector.Token`

```
@variables.yaml@
```