package tests;

import api.RandomUserApi;
import api.Specifications;
import endpoints.Endpoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.randomUser.RandomUsersResult;
import pojo.randomUser.Result;

import java.util.List;

public class RandomUserTests {
    @Test
    @DisplayName("Все пользователи female")
    public void generateUserGenderFemaleTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("gender", "female", "results", 100);
        List<Result> result = randomUsersResult.getResults();
        result.stream().forEach(x -> Assertions.assertEquals("female", x.getGender()));
        Assertions.assertEquals(100, result.stream().count());
    }

    @Test
    @DisplayName("Все пользователи из CA")
    public void generateUserNatCATest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("nat", "CA", "results", 200);
        List<Result> result = randomUsersResult.getResults();
        result.stream().forEach(x -> Assertions.assertEquals("CA", x.getNat()));
        Assertions.assertEquals(200, result.stream().count());
    }

    @Test
    @DisplayName("Генерация специального пароля")
    public void generateUserSpecialPasswordTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("password", "special,32", "results", 100);
        List<Result> result = randomUsersResult.getResults();
        result.stream().forEach(x -> Assertions.assertEquals(32, x.getLogin().getPassword().length()));
    }

    @Test
    @DisplayName("Генерация случайного пользователя без параментров")
    public void generateUserWithoutParamsTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri();
        Assertions.assertNotNull(randomUsersResult);
    }

    @Test
    @DisplayName("Запрос к предыдущей версии приложения 1.3")
    public void checkVersionInfoTest() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("1.3");
        Assertions.assertEquals(randomUsersResult.getInfo().getVersion(), "1.3");
    }

    @Test
    @DisplayName("Количество пользователей больше допустимого")
    public void generateUserCountMoreMaxAllowed() {
        Specifications.installSpecCleanUri(Specifications.reqSpec(Endpoints.BASE_URL_RANDOM_USER, Endpoints.BASE_PATH_RANDOM_USER), Specifications.resSpec(200));
        RandomUsersResult randomUsersResult = RandomUserApi.sendUri("gender", "female", "results", 5001);
        List<Result> result = randomUsersResult.getResults();
        Assertions.assertNotEquals(5001, result.stream().count());
    }
}
