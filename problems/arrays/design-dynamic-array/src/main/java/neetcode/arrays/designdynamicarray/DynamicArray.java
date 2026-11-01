package neetcode.arrays.designdynamicarray;

import java.util.NoSuchElementException;

class DynamicArray {

    private int size;
    private int[] array;

    public DynamicArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0, got: " + capacity);
        }
        this.array = new int[capacity];
        this.size = 0;
    }

    public int get(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("index " + i + " out of bounds for size " + this.size);
        }
        return this.array[i];
    }

    public void set(int i, int n) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("index " + i + " out of bounds for size " + this.size);
        }
        this.array[i] = n;
    }

    public void pushback(int n) {
        if (this.array.length <= size) {
            resize();
        }
        this.array[size] = n;
        this.size++;
    }

    public int popback() {
        if (this.size < 1) {
            throw new NoSuchElementException("popback() on empty array");
        }
        int value = this.array[this.size - 1];
        this.size--;
        return value;
    }

    private void resize() {
        int newCapacity = this.array.length * 2;
        int[] newArray = new int[newCapacity];
        System.arraycopy(this.array, 0, newArray, 0, this.size);
        this.array = newArray;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.array.length;
    }
}
