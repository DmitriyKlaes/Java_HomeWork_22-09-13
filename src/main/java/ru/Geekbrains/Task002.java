package ru.Geekbrains;

/*
3. Реализовать алгоритм перевода из инфиксной записи
в постфиксную для арифметического выражения.
Вычислить запись если это возможно.
*/

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Task002 {
    public static Map<String, Integer> priorities = Map.of("^", 3,
                                                           "*", 2,
                                                           "/", 2,
                                                           "+", 1,
                                                           "-", 1);
    public static void main(String[] args) {
        /*
         Алгоритм сработает, только если операция стоит между пробелами,
         а скобки без пробелов.
         Как в примере на 26 строке.
         Я мог заморочится с валидностью входной строки, но задача не об этом =)
         */

        String expression = "5 * 6 - (9 + 7) + (10 - 2) / 2 ^ 2";
//        String expression = "a ^ 2 - ((3 + 5) * 2)";

        String postFixExpression = fromInfixToPostfix(expression);
        if (postFixExpression == null) {
            System.out.println("Преобразование не удалось!");
            return;
        }
        System.out.printf("Выражение в инфиксной форме: %s", expression);
        System.out.printf("\nВыражение в постфиксной форме: %s", postFixExpression);
        System.out.printf("\nРезультат выражения: %d", postfixSolution(postFixExpression));
    }

    public static Integer postfixSolution(String postFixString) {
        String[] arrString = postFixString.split(" ");
        int result;
        LinkedList<Integer> stackListInt = new LinkedList<>();
        for (String s : arrString) {
            if (Character.isDigit(s.charAt(0))) {
                stackListInt.push(Integer.parseInt(s));
            } else {
                if (!Character.isLetter(s.charAt(0))) {
                    switch (s) {
                        case "+":
                            result = stackListInt.pop() + stackListInt.pop();
                            stackListInt.push(result);
                            break;
                        case "-":
                            result = -stackListInt.pop() + stackListInt.pop();
                            stackListInt.push(result);
                            break;
                        case "*":
                            result = stackListInt.pop() * stackListInt.pop();
                            stackListInt.push(result);
                            break;
                        case "/":
                            int temp = stackListInt.pop();
                            result = stackListInt.pop() / temp;
                            stackListInt.push(result);
                            break;
                        case "^":
                            double pow = stackListInt.pop();
                            double number = stackListInt.pop();
                            result = (int) Math.pow(number, pow);
                            stackListInt.push(result);
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.print("\nВыражение невожможно вычислить!");
                    return 0;
                }
            }
        }
        return stackListInt.pop();
    }

    public static String fromInfixToPostfix(String str) throws NullPointerException{
        String result;
        LinkedList<String> queList = new LinkedList<>();
        LinkedList<String> stackList = new LinkedList<>();
        String[] arrString = str.replace("(", "( ")
                                .replace (")", " )")
                                .split(" ");
        try {
            for (String s : arrString) {
                if (Character.isLetterOrDigit(s.charAt(0))) {
                    queList.offer(s);
                }
                else if (priorities.containsKey(s)) {
                    if (stackList.isEmpty() || stackList.peek().equals("(")) {
                        stackList.push(s);
                    } else {
                        if (priorities.get(s) > priorities.get(stackList.peek())) {
                            stackList.push(s);
                        } else {
                            while (!stackList.isEmpty() && (!stackList.peek().equals("(") ||
                                    priorities.get(s) <= priorities.get(stackList.peek()))) {
                                queList.offer(stackList.pop());
                            }
                            stackList.push(s);
                        }
                    }
                }
                else if (s.equals("(")) {
                    stackList.push(s);
                } else {
                    while (!stackList.peek().equals("(")) {
                        queList.offer(stackList.pop());
                    }
                    stackList.pop();
                }
            }

            Iterator<String> iter = stackList.iterator();
            while (iter.hasNext()) {
                queList.offer(stackList.pop());
            }

            StringBuilder sb = new StringBuilder();
            for (String value : queList) {
                sb.append(value);
                sb.append(" ");
            }
            result = sb.toString();

            return result;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
