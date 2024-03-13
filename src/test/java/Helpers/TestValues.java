package Helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
}
