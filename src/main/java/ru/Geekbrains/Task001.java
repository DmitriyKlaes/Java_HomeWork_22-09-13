package ru.Geekbrains;

/*
Написать реализацию очереди на основе массива;
Реализовать методы size(), empty(), offer(), poll(), peek()
*/

public class Task001<T> {
    private T[] array;
    private int startIndex;
    private int endIndex;

    public Task001(int length) {
        this.endIndex = 0;
        this.startIndex = 0;
        this.array = (T[]) new Object[length];
    }

    public int size() {
        return this.endIndex;
    }

    public boolean empty() {
        return this.size() == 0;
    }

    public boolean offer(T value) {
        if (endIndex < array.length) {
            this.array[endIndex++] = value;
            return true;
        } else {
            System.out.println("\nЭлемент '" + value + "' не добавлен!");
            return false;
        }
    }

    public T peek() {
        if (empty()) {
            System.out.println("\nМассив не заполнен!");
            return null;
        } else {
            return this.array[startIndex];
        }
    }


    public T poll() {
        if (empty()) {
            System.out.println("\nМассив не заполнен!");
            return null;
        } else {
            T result = this.array[startIndex];
            for (int i = 1; i < this.array.length; i++) {
                this.array[i - 1] = this.array[i];
                this.array[i] = null;
            }
            endIndex--;
            return result;
        }
    }

    public static void main(String[] args) {
        Task001<String> testList = new Task001<>(2);
        System.out.println(testList.offer("qwe1")); // true
        System.out.println(testList.offer("qwe2")); // true
        System.out.println(testList.offer("qwe3")); // false
        System.out.println(testList.peek()); // qwe1
        System.out.println(testList.size()); // 2
        System.out.println(testList.empty()); // false
        System.out.println();
        System.out.println(testList.poll()); // qwe1
        System.out.println(testList.peek()); // qwe2
        System.out.println(testList.size()); // 1
        System.out.println(testList.empty()); // false
        System.out.println();
        System.out.println(testList.poll()); // qwe2
        System.out.println(testList.peek()); // null
        System.out.println(testList.size()); // 0
        System.out.println(testList.empty()); // true
    }
}