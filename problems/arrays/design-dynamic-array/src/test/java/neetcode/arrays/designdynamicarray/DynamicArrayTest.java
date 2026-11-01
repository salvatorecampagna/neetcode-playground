package neetcode.arrays.designdynamicarray;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

class DynamicArrayTest {

    @Test
    public void testInvalidCapacity() {
        for (int invalidCapacity: Arrays.asList(Integer.MIN_VALUE, -1, 0)) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DynamicArray(invalidCapacity));
        }
    }

    @Test
    public void testMinimumCapacity() {
        var da = new DynamicArray(1);

        Assertions.assertEquals(1, da.getCapacity());
        Assertions.assertEquals(0, da.getSize());
    }

    @Test
    public void testEmpty() {
        var da = new DynamicArray(10);

        Assertions.assertEquals(10, da.getCapacity());
        Assertions.assertEquals(0, da.getSize());
    }

    @Test
    public void testSetAndGet() {
        var da = new DynamicArray(100);

        for (int i = 0; i < 100; i++) {
            da.pushback(0);
            da.set(i, i);
        }

        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(i, da.get(i));
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.get(101));
    }

    @Test
    public void testPushBack() {
        var da  = new DynamicArray(10);

        for (int i = 0; i < 10; i++) {
            da.pushback(i);
        }


        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(i, da.get(i));
        }
        Assertions.assertEquals(10, da.getSize());
        Assertions.assertEquals(10, da.getCapacity());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.get(11));
    }

    @Test
    public void testResize() {
        var da = new DynamicArray(1);

        for (int i = 0; i < 100; i++) {
            da.pushback(i);
        }

        Assertions.assertEquals(128, da.getCapacity());
        Assertions.assertEquals(100, da.getSize());
    }

    @Test
    public void testPopBack() {
        var da = new DynamicArray(10);

        for (int i = 0; i < 10; i++) {
            da.pushback(i);
        }

        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(10 - i - 1, da.popback());
        }
        Assertions.assertThrows(NoSuchElementException.class, da::popback);
        Assertions.assertEquals(0, da.getSize());
        Assertions.assertEquals(10, da.getCapacity());
    }

    @Test
    public void testPushBackPopBack() {
        var da = new DynamicArray(10);

        for (int i = 0; i < 100; i++) {
            if (i != 0  && i % 10 == 0) {
                da.popback();
            }
            da.pushback(i);
        }
        Assertions.assertEquals(91, da.getSize());
        Assertions.assertEquals(160, da.getCapacity());
    }
}
