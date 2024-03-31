package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestValuesUi {
    public static Stream<Arguments> isDemoQaTestData() {
        return Stream.of(
                arguments("Oleg", "Petrovich", "oleg@mry.ru", "Male", "9626226648", "01 February,2024", "123 Tank", "English", "Reading", "Rajasthan", "Jaipur"),
                arguments("Olga", "Kedr", "oleg2@mry.ru", "Female", "9626226648", "04 February,2024", "12asd3 Tankasdasdas", "Maths", "Music", "NCR", "Gurgaon"),
                arguments("Gena", "Olegovich", "oleg1@mry.ru", "Other", "9626225555", "04 February,2024", "12asd3 Tankasdasdas 12312", "Maths", "Sports", "NCR", "Noida")
        );
    }

    public static Stream<Arguments> isDemoQaTestDataRequiredValues() {
        return Stream.of(
                arguments("Oleg", "Petrovich", "Male", "9626226648"),
                arguments("Olga", "Kedr", "Female", "9626226648"),
                arguments("Gena", "Olegovich", "Other", "9626225555")
        );
    }

    public static Stream<Arguments> isDemoQaTestDataNotValid() {
        return Stream.of(
                arguments("", "Petrovich", "oleg@mry.ru", "Male", "9626226648", "01 February,2024", "123 Tank", "English", "Reading", "Rajasthan", "Jaipur"), //Пустое имя
                arguments("Olga", "", "oleg2@mry.ru", "Female", "9626226648", "04 February,2024", "12asd3 Tankasdasdas", "Maths", "Music", "NCR", "Gurgaon"), //Пустая фамилия
                arguments("Gena", "Olegovich", "oleg1@mry.ru", "Other", "962622555", "04 February,2024", "12asd3 Tankasdasdas 12312", "Maths", "Sports", "NCR", "Noida"), //9 символов в номере
                arguments("Max", "Olegovich", "oleg1@mry.ru", "Other", "qwertyuiop", "04 February,2024", "12asd3 Tankasdasdas 12312", "Maths", "Sports", "NCR", "Noida"), //Буквый в номере
                arguments("Max", "Petrovich", "qweqwe", "Other", "9626226648", "04 February,2024", "12asd3 Tankasdasdas 12312", "Maths", "Sports", "NCR", "Noida") //Email не по маске
        );
    }
}
