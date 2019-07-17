package exam.page2tasks;

import org.testng.Assert;

//Дан текст. Некоторые его фрагменты выделены группами символов ##. Заменить выделение группами символов '<' и '>)'. Пример: 'Это ##тестовый пример## для задачи ##на## строки' преобразуется в 'Это <тестовый> пример для задачи <на> строки'
public class QuotesReplace {
    public static void main(String[] args) {

        String text = "Это ##тестовый пример## для задачи ##на## строки";

        String newText = QuotesReplace(text);

        System.out.printf("имя файла без расширения: %s", newText);

        Assert.assertEquals(newText, "Это <тестовый пример> для задачи <на> строки");
    }

    public static String QuotesReplace(String text) {

        StringBuilder newText = new StringBuilder(text);

        boolean isOpeningQuote = true;

        int quotesLocation;

        while ((quotesLocation = newText.indexOf("##")) != -1) {

            if (isOpeningQuote) {
                newText.setCharAt(quotesLocation, '<');//replace first '#' with '<'
            } else {
                newText.setCharAt(quotesLocation, '>');//replace first '#' with '>'
            }
            newText.deleteCharAt(quotesLocation + 1);//remove second '#'

            isOpeningQuote = !isOpeningQuote;
        }

        return newText.toString();
    }
}