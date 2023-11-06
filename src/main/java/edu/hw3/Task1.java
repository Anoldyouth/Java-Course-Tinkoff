package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class Task1 {
    private Task1() {
    }

    /**
     * <p>Шифр Атбаша - это метод шифрования, при котором каждая буква слова заменяется на свою
     * "зеркальную" букву в алфавите: A <=> Z; B <=> Y; C <=> X и т.д.</p>
     * <p>Создайте функцию, которая принимает строку и применяет к ней шифр.</p>
     * Замечания:
     * <li>используется латинский алфавит</li>
     * <li>регистр букв нужно сохранить</li>
     * <li>символы не латинского алфавита нужно писать как есть</li>
     *
     * @param string строка для шифрации
     * @return зашифрованная строка
     */
    public static String atbash(@NotNull String string) {
        StringBuilder answer = new StringBuilder();

        for (char ch: string.toCharArray()) {
            if (isCapitalLetter(ch)) {
                answer.append((char) (('Z' - ch) + 'A'));
                continue;
            }

            if (isLowercaseLetter(ch)) {
                answer.append((char) (('z' - ch) + 'a'));
                continue;
            }

            answer.append(ch);
        }

        return answer.toString();
    }

    private static boolean isCapitalLetter(char ch) {
        return ('A' <= ch) && (ch <= 'Z');
    }

    private static boolean isLowercaseLetter(char ch) {
        return ('a' <= ch) && (ch <= 'z');
    }
}
