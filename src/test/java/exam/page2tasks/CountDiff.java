package exam.page2tasks;

import org.testng.Assert;

//Найти количество различных элементов массива. Пример: для 1 4 5 1 1 3 ответ 4.
public class CountDiff {
    public static void main(String[] args) {

        int[] arr = new int[]{1, 4, 5, 1, 1, 3};
        int count = countUniqIntems(arr);
        System.out.printf("Количество различных элементов массива: %d", count);

        Assert.assertEquals(count, 4);
    }

    public static int countUniqIntems(int[] arr) {

        int uniqCount = 0;

        for (int i = 0; i < arr.length; i++) {
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    isUnique = false;
                }
            }
            if (isUnique) {
                uniqCount++;
            }
        }
        return uniqCount;
    }
}