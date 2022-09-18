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
        String expression = "6 ^ 2 - ((3 + 5) * 2)";
//        String expression = "5 * 6 ^ (9 - 7) - 10";

        LinkedList<String> postFixList = fromInfixToPostfix(expression);
        if (postFixList == null) {
            System.out.println("Преобразование не удалось!");
            return;
        }
        System.out.printf("Выражение в инфиксной форме: %s", expression);
        System.out.print("\nВыражение в постфиксной форме: ");
        for (String value : postFixList) {
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.printf("\nРезультат выражения: %d", postfixSolution(postFixList));
    }

    public static Integer postfixSolution(LinkedList<String> postFixList) {
        int result;
        LinkedList<Integer> stackListInt = new LinkedList<>();
        for (String s : postFixList) {
            if (isDigit(s)) {
                stackListInt.push(Integer.parseInt(s));
            } else {
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
            }
        }
        return stackListInt.pop();
    }

    public static LinkedList<String> fromInfixToPostfix(String str) throws NullPointerException{
        LinkedList<String> queList = new LinkedList<>();
        LinkedList<String> stackList = new LinkedList<>();
        String[] arrString = str.replace("(", "( ")
                                .replace(")", " )")
                                .split(" ");
        try {
            for (String s : arrString) {
                if (isDigit(s)) {
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
            return queList;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
