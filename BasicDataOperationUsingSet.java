import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Short;
import java.time.format.shortValueFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Клас BasicDataOperationUsingSet надає методи для виконання основних операцiй з даними типу Short.
 * 
 * <p>Цей клас зчитує данi з файлу "list/Short.data", сортує їх та виконує пошук значення в масивi та множинi.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив Short.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi Short.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi Short.</li>
 *   <li>{@link #searchSet()} - Виконує пошук значення в множинi Short.</li>
 *   <li>{@link #findMinAndMaxInSet()} - Знаходить мiнiмальне та максимальне значення в множинi Short.</li>
 *   <li>{@link #compareArrayAndSet()} - Порiвнює елементи масиву та множини.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingSet(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #shortValueValueToSearch} - Значення Short для пошуку.</li>
 *   <li>{@link #shortValueArray} - Масив Short.</li>
 *   <li>{@link #shortValueSet} - Множина Short.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingSet "2024-03-16T00:12:38Z"
 * }
 * </pre>
 */
public class BasicDataOperationUsingSet {
    static final String PATH_TO_DATA_FILE = "list/Short.data";

    Short shortValueValueToSearch;
    Short[] shortValueArray;
    Set<Short> shortValueSet = new HashSet<>();

    public static void main(String[] args) {  
        BasicDataOperationUsingSet basicDataOperationUsingSet = new BasicDataOperationUsingSet(args);
        basicDataOperationUsingSet.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingSet(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String valueToSearch = args[0];
        this.shortValueValueToSearch = Short.parse(valueToSearch);

        shortValueArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        shortValueSet = new HashSet<>(Arrays.asList(shortValueArray));
    }

    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та множину об'єктiв Short з файлу, сортує їх та виконує пошук значення.
     */
    private void doDataOperation() {
        // операцiї з масивом дати та часу
        searchArray();
        findMinAndMaxInArray();

        sortArray();

        searchArray();
        findMinAndMaxInArray();

        // операцiї з HashSet дати та часу
        searchSet();
        findMinAndMaxInSet();
        compareArrayAndSet();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(shortValueArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв Short та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    private void sortArray() {
        long startTime = System.nanoTime();

        Arrays.sort(shortValueArray);

        Utils.printOperationDuration(startTime, "сортування масиву дати i часу");
    }

    /**
     * Метод для пошуку значення в масивi дати i часу.
     */
    private void searchArray() {
        long startTime = System.nanoTime();

        int index = Arrays.binarySearch(this.shortValueArray, shortValueValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в масивi дати i часу");

        if (index >= 0) {
            System.out.println("Значення '" + shortValueValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + shortValueValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi Short.
     */
    private void findMinAndMaxInArray() {
        if (shortValueArray == null || shortValueArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Short min = shortValueArray[0];
        Short max = shortValueArray[0];

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в масивi");

        for (Short shortValue : shortValueArray) {
            if (shortValue.isBefore(min)) {
                min = shortValue;
            }
            if (shortValue.isAfter(max)) {
                max = shortValue;
            }
        }

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Метод для пошуку значення в множинi дати i часу.
     */
    private void searchSet() {
        long startTime = System.nanoTime();

        boolean isFound = this.shortValueSet.contains(shortValueValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в HashSet дати i часу");

        if (isFound) {
            System.out.println("Значення '" + shortValueValueToSearch + "' знайдено в HashSet");
        } else {
            System.out.println("Значення '" + shortValueValueToSearch + "' в HashSet не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в множинi Short.
     */
    private void findMinAndMaxInSet() {
        if (shortValueSet == null || shortValueSet.isEmpty()) {
            System.out.println("HashSet порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Short min = Collections.min(shortValueSet);
        Short max = Collections.max(shortValueSet);

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в HashSet");

        System.out.println("Мiнiмальне значення в HashSet: " + min);
        System.out.println("Максимальне значення в HashSet: " + max);
    }

    /**
     * Порiвнює елементи масиву та множини.
     */
    private void compareArrayAndSet() {
        System.out.println("Кiлькiсть елементiв в масивi: " + shortValueArray.length);
        System.out.println("Кiлькiсть елементiв в HashSet: " + shortValueSet.size());

        boolean allElementsMatch = true;
        for (Short shortValue : shortValueArray) {
            if (!shortValueSet.contains(shortValue)) {
                allElementsMatch = false;
                break;
            }
        }

        if (allElementsMatch) {
            System.out.println("Всi елементи масиву присутнi в HashSet.");
        } else {
            System.out.println("Не всi елементи масиву присутнi в HashSet.");
        }
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу Short.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>>> Час виконання операцiї '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв Short з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв Short.
     */
    static Short[] readArrayFromFile(String pathToFile) {
        shortValueFormatter formatter = shortValueFormatter.ISO_DATE_TIME;
        Short[] tempArray = new Short[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Short shortValue = Short.parse(line, formatter);
                tempArray[index++] = shortValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Short[] finalArray = new Short[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктiв Short у файл.
     * 
     * @param shortValueArray Масив об'єктiв Short.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(Short[] shortValueArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (Short shortValue : shortValueArray) {
                writer.write(shortValue.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}