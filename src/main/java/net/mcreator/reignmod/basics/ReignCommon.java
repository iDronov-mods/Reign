package net.mcreator.reignmod.basics;

public class ReignCommon {

    /**
     * Безопасно преобразует строку в целое число.
     * В случае ошибки возвращает значение по умолчанию.
     * @param str строка, которую нужно преобразовать
     * @param defaultValue значение по умолчанию, если преобразование не удалось
     * @return int значение, полученное из строки, или defaultValue
     */
    public static int safeParseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Высчитывает площадь прямоугольника по двум параметрам типа String.
     * Использует safeParseInt для безопасного преобразования строк в числа.
     * @param widthStr строковое значение ширины
     * @param heightStr строковое значение высоты
     * @return площадь прямоугольника или -1, если входные данные некорректны
     */
    public static int calculateRectangleArea(String widthStr, String heightStr, int defaultValue) {
        int width = safeParseInt(widthStr, -1);
        int height = safeParseInt(heightStr, -1);

        if (width < 0 || height < 0) {
            return defaultValue; // Некорректные значения
        }
        return width * height;
    }

    /**
     * Проверяет, что прямоугольник с указанными сторонами удовлетворяет следующим условиям:
     * 1) Площадь не превышает maxArea.
     * 2) Ширина и высота не меньше 3 и являются нечётными.
     * 3) Одна сторона не превышает другую более, чем в 3 раза.
     * Возвращает true, если все условия соблюдены, иначе false.
     *
     * @param widthStr   строковое значение ширины
     * @param heightStr  строковое значение высоты
     * @param maxArea строковое значение максимально допустимой площади
     * @return boolean результат проверки
     */
    public static boolean isRectangleCorrect(String widthStr, String heightStr, double maxArea) {
        int width = safeParseInt(widthStr, -1);
        int height = safeParseInt(heightStr, -1);

        // Проверяем корректность сторон
        if (width < 3 || height < 3) {
            return false;
        }
        // Проверяем, что стороны нечётные
        if (width % 2 == 0 || height % 2 == 0) {
            return false;
        }

        // Вычисляем площадь
        int area = width * height;
        // Сравниваем с максимально допустимой площадью
        if (area > maxArea) {
            return false;
        }

        // Проверяем условие, что одна сторона не больше другой более чем в 3 раза
        return Math.max(width, height) <= 3 * Math.min(width, height);
    }

}