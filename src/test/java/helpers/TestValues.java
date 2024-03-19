package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestValues {
    private List<String> list;

    {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\test\\testValues.txt"))) {
            list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Не удалось получить тестовые данные из файла");
            ex.printStackTrace();
        }
    }

    public List<String> getList() {
        return list;
    }
    public static Stream<Arguments> isGenderTestData() {
        return Stream.of(
                arguments("male"),
                arguments("female")
        );
    }
    public static Stream<Arguments> isNatTestData() {
        return Stream.of(
                arguments("US"),
                arguments("TR"),
                arguments("FR"),
                arguments("GB"),
                arguments("UA")
        );
    }
}
