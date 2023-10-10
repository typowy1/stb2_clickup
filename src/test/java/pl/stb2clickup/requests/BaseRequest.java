package pl.stb2clickup.requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.stb2clickup.properties.ClickUpProperties;
import pl.stb2clickup.url.ClickUpUrl;

public class BaseRequest {
//tu teorzymy specyfikacje dla naszych requestów, bedzie ona w spec

    private static RequestSpecBuilder requestBuilder;

    public static RequestSpecification requestSpec() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ClickUpUrl.getBaseUrl()); //ustawiamy bazowy adres api
        requestBuilder.setContentType(ContentType.JSON); //ustawiamy ContentType
        requestBuilder.addHeader("Authorization", ClickUpProperties.getToken()); //ustawiamy token
        requestBuilder.addFilter(new AllureRestAssured());//dodanie raportu allurowego

        return requestBuilder.build();

    }

    public static RequestSpecification requestSpecWithLogs() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ClickUpUrl.getBaseUrl()); //ustawiamy bazowy adres api
        requestBuilder.setContentType(ContentType.JSON); //ustawiamy ContentType
        requestBuilder.addHeader("Authorization", ClickUpProperties.getToken()); //ustawiamy token
        requestBuilder.addFilter(new RequestLoggingFilter()); //logowanie
        requestBuilder.addFilter(new ResponseLoggingFilter()); //logowanie
        requestBuilder.addFilter(new AllureRestAssured()); //dodanie raportu allurowego

        return requestBuilder.build();

    }
}
