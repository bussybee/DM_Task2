package ru.vsu.cs.maslovaei;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int[] array = readIntArrayFromFile("input.txt");
        int[] newArray = insertionSort(array, array.length);
        writeArrayToFile("output.txt", newArray);
    }

    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая распаковка из объекта
            result[i] = arr[i];
        }
        return result;
    }

    public static int[] toIntArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }

        // из List<Integer> можно получить Integer[]
        Integer[] arr = list.toArray(new Integer[0]);
        // Integer[] -> int[]
        return Main.toPrimitive(arr);
    }

    /**
     * Чтение всего текстового файла в массив строк
     */
    public static String[] readLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            // обязательно, чтобы закрыть открытый файл
        }
        return lines.toArray(new String[0]);
    }

    /**
     * Чтение массива int[] из первой строки текстового файла
     */
    public static int[] readIntArrayFromFile(String fileName) {
        try {
            return toIntArray(readLinesFromFile(fileName)[0]);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static int[] insertionSort(int[] array, int n) {
        int i, j, temp, count_compare = 0;
        // i представляет длину отсортированной части массива, начинаем с 1, потому что один элемент сам по себе считается упорядоченным
        for (i = 1; i < n; i++) {
            temp = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > temp) {
                count_compare++;
                array[j + 1] = array[j];
                j -= 1;
            }
            array[j + 1] = temp;
        }
        return array;
    }

    public static void writeArrayToFile(String fileName, int[] array)
            throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(Arrays.toString(array));
        }
    }
}
