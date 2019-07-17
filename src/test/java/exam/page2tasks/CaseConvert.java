package exam.page2tasks;

import java.util.ArrayList;
import java.util.List;

//Дан массив названий переменных в camelCase. Преобразовать названия в snake_case. пример массива [myAwsomePhrase, iLoveJustinBieber, captainJackSparrow]
public class CaseConvert {
    public static void main(String[] args) {

        String[] camelStrings = new String[]{"myAwsomePhrase", "iLoveJustinBieber", "captainJackSparrow"};

        String[] snakeStrings = ConvertToSnakeCase(camelStrings);

        for (String snakeString : snakeStrings) {
            System.out.println(snakeString);
        }

    }

    public static String[] ConvertToSnakeCase(String[] camelStrings) {

        List<String> snakeStrings = new ArrayList<>();

        for (String camelString : camelStrings) {

            StringBuilder newString = new StringBuilder();
            for (char c : camelString.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    newString.append('_');
                    newString.append(Character.toLowerCase(c));
                } else {
                    newString.append(c);
                }
            }
            snakeStrings.add(newString.toString());
        }
        return snakeStrings.stream().toArray(String[]::new);
    }
}
