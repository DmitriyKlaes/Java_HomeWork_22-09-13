package ru.Geekbrains;

/*
1. Взять за основу реализацию стека, написанную в конце семенара:
    ---
    Реализовать стэк с помощью массива.
    Нужно реализовать методы:
    size(), empty(), push(), peek(), pop().
    ---
Добавить проверку граничных условий и выводить сообщения об ошибках,
если мы пытаемся извлечь элемент из пустого стека,
добавить элемент в полностью заполненный стек и тд
*/

public class Task000<T> {
    private T[] array;
    private int index;

    public Task000(int length) {
        this.index = 0;
        this.array = (T[]) new Object[length];
    }

    public int size() {
        return this.index;
    }

    public boolean empty() {
        return this.size() == 0;
    }

    public void push(T value) {
        if (index < array.length) {
            this.array[index++] = value;
        } else {
            System.out.printf("\nИндекс %d отсутствует в массиве с длиной %d!\n", index, array.length);
            System.exit(1);
        }
    }

    public T peek() {
        if (empty()) {
            System.out.println("\nМассив не заполнен!");
            System.exit(1);
            return null;
        } else {
            return this.array[index - 1];
        }
    }

    public T pop() {
        if (empty()) {
            System.out.println("\nМассив не заполнен!");
            System.exit(1);
            return null;
        } else {
            return this.array[--index];
        }
    }

    public static void main(String[] args) {
        Task000<String> testList = new Task000<>(2);
        System.out.println(testList.empty()); // true
        System.out.println(testList.size()); // 0
        testList.push("qwe1"); // первое добавление
        System.out.println(testList.empty()); // false
        System.out.println(testList.size()); // 1
        testList.push("qwe1"); // второе добавление
        System.out.println(testList.size()); // 2
//        testList.push("qwe1"); // снять комментарий для просмотра ошибки добавления
        System.out.println(testList.peek()); // qwe1
        System.out.println(testList.pop()); // qwe1
        System.out.println(testList.pop()); // qwe1
//        System.out.println(testList.pop()); // снять комментарий для просмотра ошибки удаления
//        System.out.println(testList.peek()); // снять комментарий для просмотра ошибки извлечения
    }
}