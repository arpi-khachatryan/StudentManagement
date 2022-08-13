package storage;

import model.User;

public class UserStorage {

    private User[] array = new User[10];
    private int size = 0;

    public void add(User user) {
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = user;
    }

    private void increaseArray() {
        User[] newArray = new User[array.length + 10];
        System.arraycopy(array, 0, newArray, 0, array.length);
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
            System.out.println("User is deleted");
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public User getUserByEmailAndPassword(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }
}