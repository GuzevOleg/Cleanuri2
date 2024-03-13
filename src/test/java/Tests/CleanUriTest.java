package Tests;

import Helpers.TestValues;
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
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(0);
        ResultUrl resultUrl = Specifications.sendUri(new Urls(testValue), 200);
        Assertions.assertTrue(resultUrl.getResultUrl().contains(Specifications.BASE_URL));
    }

    @Test
    @DisplayName("Тест на различие тестового url-адреса со сгенерированным")
    public void checkUriNotEquals() {
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(1);
        ResultUrl resultUrl = Specifications.sendUri(new Urls(testValue), 200);
        Assertions.assertNotEquals(testValue, resultUrl.getResultUrl());
    }

    @Test
    @DisplayName("Тест невалидного url-адреса")
    public void checkNotValidUri() {
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(2);
        ResultUrl resultUrl = Specifications.sendUri(new Urls(testValue), 400);
        Assertions.assertEquals("API Error: URL is invalid (check #1)", resultUrl.getError());
    }

    @Test
    @DisplayName("Проверка url-адреса без строки https")
    public void checkUriNotHttp() {
        TestValues testValues = new TestValues();
        String testValue = testValues.getList().get(3);
        ResultUrl resultUrl = Specifications.sendUri(new Urls(testValue), 400);
        Assertions.assertEquals("API Error: URL is invalid (check #1)", resultUrl.getError());
    }

    @Test
    @DisplayName("Тест с пустым значением")
    public void checkIsEmptyUri() {
        Urls urls = new Urls("");
        ResultUrl resultUrl = Specifications.sendUri(urls, 400);
        Assertions.assertEquals("API Error: After sanitization URL is empty", resultUrl.getError());
        Assertions.assertTrue(urls.getUrl().isEmpty());
    }
}
