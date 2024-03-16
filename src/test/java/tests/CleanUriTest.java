package tests;

import api.CleanUriApi;
import helpers.TestValues;
import api.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ResultUrl;
import pojo.Urls;

public class CleanUriTest {

    @Test
    @DisplayName("Тест на содержание базового url-адреса в результате")
    public void checkResultContainsBaseUri() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.repSpec(200));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(0);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        Assertions.assertTrue(resultUrl.getResultUrl().contains(Specifications.BASE_URL));
    }

    @Test
    @DisplayName("Тест на различие тестового url-адреса со сгенерированным")
    public void checkUriNotEquals() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.repSpec(200));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(1);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        Assertions.assertNotEquals(testValue, resultUrl.getResultUrl());
    }

    @Test
    @DisplayName("Тест невалидного url-адреса")
    public void checkNotValidUri() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.repSpec(400));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(2);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        Assertions.assertEquals("API Error: URL is invalid (check #1)", resultUrl.getError());
    }

    @Test
    @DisplayName("Проверка url-адреса без строки https")
    public void checkUriNotHttp() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.repSpec(400));
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(3);
        ResultUrl resultUrl = CleanUriApi.sendUri(new Urls(testValue));
        Assertions.assertEquals("API Error: URL is invalid (check #1)", resultUrl.getError());
    }

    @Test
    @DisplayName("Тест с пустым значением")
    public void checkIsEmptyUri() {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.repSpec(400));
        Urls urls = new Urls("");
        ResultUrl resultUrl = CleanUriApi.sendUri(urls);
        Assertions.assertEquals("API Error: After sanitization URL is empty", resultUrl.getError());
        Assertions.assertTrue(urls.getUrl().isEmpty());
    }
}
