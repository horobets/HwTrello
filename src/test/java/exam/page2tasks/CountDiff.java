package exam.page2tasks;

import org.testng.Assert;

//Найти количество различных элементов массива. Пример: для 1 4 5 1 1 3 ответ 4.
public class CountDiff {
    public static void main(String[] args) {

        int[] arr = new int[]{1, 4, 5, 1, 1, 3};
        int count = countDiff(arr);
        System.out.printf("Количество различных элементов массива: %d", count);

        Assert.assertEquals(count, 4);
    }

    public static int countDiff(int[] arr) {
        int diffCount = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                diffCount++;
            }
        }
        return diffCount;
    }
}
