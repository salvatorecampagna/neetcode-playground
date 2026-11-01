package neetcode.arrays.designdynamicarray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

class DynamicArrayTest {

    @Test
    void example1() {
        var da = new DynamicArray(1);

        Assertions.assertEquals(0, da.getSize());
        Assertions.assertEquals(1, da.getCapacity());
    }

    @Test
    void example2() {
        var da = new DynamicArray(1);

        da.pushback(1);
        Assertions.assertEquals(1, da.getCapacity());
        da.pushback(2);
        Assertions.assertEquals(2, da.getCapacity());
    }

    @Test
    void example3() {
        var da = new DynamicArray(1);

        Assertions.assertEquals(0, da.getSize());
        Assertions.assertEquals(1, da.getCapacity());

        da.pushback(1);
        Assertions.assertEquals(1, da.getSize());
        Assertions.assertEquals(1, da.getCapacity());

        da.pushback(2);
        Assertions.assertEquals(2, da.getSize());
        Assertions.assertEquals(2, da.getCapacity());

        Assertions.assertEquals(2, da.get(1));
        da.set(1, 3);
        Assertions.assertEquals(3, da.get(1));

        Assertions.assertEquals(3, da.popback());
        Assertions.assertEquals(1, da.getSize());
        Assertions.assertEquals(2, da.getCapacity());
    }

    @Test
    void testInvalidCapacity() {
        for (int invalidCapacity: Arrays.asList(Integer.MIN_VALUE, -1, 0)) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DynamicArray(invalidCapacity));
        }
    }

    @Test
    void testMinimumCapacity() {
        var da = new DynamicArray(1);

        Assertions.assertEquals(1, da.getCapacity());
        Assertions.assertEquals(0, da.getSize());
    }

    @Test
    void testEmpty() {
        var da = new DynamicArray(10);

        Assertions.assertEquals(10, da.getCapacity());
        Assertions.assertEquals(0, da.getSize());
    }

    @Test
    void testSetAndGet() {
        var da = new DynamicArray(100);

        for (int i = 0; i < 100; i++) {
            da.pushback(0);
            da.set(i, i);
        }

        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(i, da.get(i));
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.set(-1, 0));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.get(101));
    }

    @Test
    void testPushBack() {
        var da = new DynamicArray(10);

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
    void testResize() {
        var da = new DynamicArray(1);

        for (int i = 0; i < 100; i++) {
            da.pushback(i);
        }

        Assertions.assertEquals(128, da.getCapacity());
        Assertions.assertEquals(100, da.getSize());
        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(i, da.get(i));
        }
    }

    @Test
    void testPopBack() {
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
    void testPushBackPopBack() {
        var da = new DynamicArray(10);

        for (int i = 0; i < 100; i++) {
            if (i != 0 && i % 10 == 0) {
                da.popback();
            }
            da.pushback(i);
        }
        Assertions.assertEquals(91, da.getSize());
        Assertions.assertEquals(160, da.getCapacity());
    }

    @Test
    void testGetSetPartlyFilled() {
        var da = new DynamicArray(10);

        for (int i = 0; i < 5; i++) {
            da.pushback(i);
        }

        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(i, da.get(i));
            da.set(i, i + 1);
            Assertions.assertEquals(i + 1, da.get(i));
        }

        for (int i = 5; i < 20; i++) {
            int finalI = i;
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.set(finalI, finalI));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.get(finalI));
        }
    }

    @Test
    void testPushAfterPop() {
        var da = new DynamicArray(10);

        for (int i = 0; i < 3; i++) {
            da.pushback(i);
        }

        Assertions.assertEquals(2, da.get(2));
        Assertions.assertEquals(2, da.popback());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> da.get(2));

        da.pushback(10);
        Assertions.assertEquals(10, da.get(2));
        Assertions.assertEquals(10, da.popback());

        for (int i = 3; i < 20; i++) {
            da.pushback(i);
        }

        Assertions.assertEquals(19, da.get(18));
        Assertions.assertEquals(19, da.getSize());
        Assertions.assertEquals(20, da.getCapacity());
    }
}
