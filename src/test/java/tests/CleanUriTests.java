package tests;

import api.CleanUriApi;
import endpoints.Endpoints;
import helpers.TestValues;
import api.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.cleanUri.ResultUrl;
import pojo.cleanUri.Urls;

import static org.junit.jupiter.api.Assertions.*;

public class CleanUriTests {

    @Test
    @DisplayName("Тест на содержание базового url-адреса в результате")
    public void checkResultContainsBaseUriTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_CLEAN_URI, Endpoints.BASE_PATH_CLEAN_URI), Specifications.resSpec(200));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(0);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        assertTrue(resultUrl.getResultUrl().contains(Endpoints.BASE_URL_CLEAN_URI));
    }

    @Test
    @DisplayName("Тест на различие тестового url-адреса со сгенерированным")
    public void checkUriNotEqualsTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_CLEAN_URI, Endpoints.BASE_PATH_CLEAN_URI), Specifications.resSpec(200));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(1);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        assertNotEquals(testValue, resultUrl.getResultUrl());
    }

    @Test
    @DisplayName("Тест невалидного url-адреса")
    public void checkNotValidUriTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_CLEAN_URI, Endpoints.BASE_PATH_CLEAN_URI), Specifications.resSpec(400));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(2);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        assertEquals("API Error: URL is invalid (check #1)", resultUrl.getError());
    }

    @Test
    @DisplayName("Проверка url-адреса без строки https")
    public void checkUriNotHttpTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_CLEAN_URI, Endpoints.BASE_PATH_CLEAN_URI), Specifications.resSpec(400));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(3);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        assertEquals("API Error: URL is invalid (check #1)", resultUrl.getError());
    }

    @Test
    @DisplayName("Тест с пустым значением")
    public void checkIsEmptyUriTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_CLEAN_URI, Endpoints.BASE_PATH_CLEAN_URI), Specifications.resSpec(400));
        Urls urls = new Urls("");
        ResultUrl resultUrl = CleanUriApi.sendUri(urls);
        assertEquals("API Error: After sanitization URL is empty", resultUrl.getError());
        assertTrue(urls.getUrl().isEmpty());
    }
}
