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
//    public void push(T value) {
//        try {
//            this.array[index++] = value;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void push(T value) {
        this.array[index++] = value;
        throw new ArrayIndexOutOfBoundsException(123);
    }

    public T peek() {
        return this.array[index - 1];
    }
//    public T pop() {
//        if (index == 0) throw new ArrayIndexOutOfBoundsException();
//        else return this.array[--index];
//    }

//    public T pop() {
//        try {
//            return this.array[--index];
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public static void main(String[] args) {
        Task000<String> lala = new Task000<>(6);
//        lala.push("qwe1");
//        lala.push("qwe2");
//        lala.push("qwe3");
//        lala.push("qwe3");
//        lala.push("qwe3");

        System.out.println();
//        System.out.println(lala.pop());
//        System.out.println(lala.empty());
//        System.out.println(lala.pop());
//        System.out.println(lala.pop());
//        System.out.println(lala.pop());
    }
}