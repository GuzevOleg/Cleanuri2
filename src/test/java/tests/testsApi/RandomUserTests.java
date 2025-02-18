package tests.testsApi;

import controllers.RandomUserApi;
import controllers.Specifications;
import endpoints.Endpoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pojo.randomUser.RandomUsersResult;
import pojo.randomUser.Result;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RandomUserTests {

    @ParameterizedTest
    @MethodSource("helpers.TestValuesApi#isGenderTestData")
    @DisplayName("Все пользователи female/male")
    public void generateUserGenderTest(String gender) {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("gender", gender, "results", 100);
        List<Result> result = randomUsersResult.getResults();
        result.stream().forEach(x -> assertEquals(gender, x.getGender()));
        assertEquals(100, result.stream().count());
    }

    @ParameterizedTest
    @MethodSource("helpers.TestValuesApi#isNatTestData")
    @DisplayName("Все пользователи одной нации")
    public void generateUserNatTest(String nat) {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("nat", nat, "results", 200);
        List<Result> result = randomUsersResult.getResults();
        result.stream().forEach(x -> assertEquals(nat, x.getNat()));
        assertEquals(200, result.stream().count());
    }

    @Test
    @DisplayName("Генерация специального пароля")
    public void generateUserSpecialPasswordTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("password", "special,32", "results", 100);
        List<Result> result = randomUsersResult.getResults();
        result.stream().forEach(x -> assertEquals(32, x.getLogin().getPassword().length()));
    }

    @Test
    @DisplayName("Генерация случайного пользователя без параментров")
    public void generateUserWithoutParamsTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri();
        assertNotNull(randomUsersResult);
    }

    @Test
    @DisplayName("Запрос к предыдущей версии приложения 1.3")
    public void checkVersionInfoTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("1.3");
        assertEquals(randomUsersResult.getInfo().getVersion(), "1.3");
    }

    @Test
    @DisplayName("Количество пользователей больше допустимого")
    public void generateUserCountMoreMaxAllowed() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("gender", "female", "results", 5001);
        List<Result> result = randomUsersResult.getResults();
        assertNotEquals(5001, result.stream().count());
    }
}
