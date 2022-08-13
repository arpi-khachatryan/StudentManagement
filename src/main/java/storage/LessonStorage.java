package storage;

import model.Lesson;

public class LessonStorage {

    private Lesson[] array = new Lesson[10];
    private int size = 0;

    public void add(Lesson lesson) {
        if (size == array.length) {
            increaseArray();

        }
        array[size++] = lesson;
    }

    private void increaseArray() {
        Lesson[] newArray = new Lesson[array.length + 10];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public int getSize() {
        return size;
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                array[i] = array[++i];
            }
            size--;
            System.out.println("The lesson was deleted");
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public Lesson getLessonByIndex(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return null;
    }
}