package edu.hw3;

import edu.hw3.exceptions.InvalidStringContentException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task2 {
    private Task2() {
    }

    /**
     * <p>Напишите функцию, которая группирует строку в кластеры, заключенные в скобки.
     * Каждый кластер должен быть сбалансированным.</p>
     *
     * @param string строка для группировки
     * @return массив кластеров
     * @throws InvalidStringContentException строка содержит недопустимые символы или она не сбалансирована
     */
    public static String[] clusterize(String string) throws InvalidStringContentException {
        Stack<Character> stack = new Stack<>();
        List<String> answer = new ArrayList<>();
        char ch;
        int previous = 0;

        for (int i = 0; i < string.length(); i++) {
            ch = string.charAt(i);
            switch (ch) {
                case '(' -> stack.add(ch);
                case ')' -> {
                    if (stack.empty() || stack.pop() != '(') {
                        throw new InvalidStringContentException();
                    }

                    if (!stack.empty()) {
                        break;
                    }

                    answer.add(string.substring(previous, i + 1));
                    previous = i + 1;
                }
                default -> throw new InvalidStringContentException();
            }
        }

        if (!stack.empty()) {
            throw new InvalidStringContentException();
        }

        return answer.toArray(new String[0]);
    }
}
