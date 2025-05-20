import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class SILab2Test {

    @Test
    public void test1_allItemsNull() {
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(null, "filipavlovskinumerouno")
        );
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));
    }

    @Test
    public void test2_successfulExecution() {
        List<Item> items = List.of(
            new Item("test", 100, 17, 0.7)
        );
        // (100 * 17) - (0.7 * 100 * 17) = 1700 - 1190 = 510 - 30 = 480
        double expected = 1700 - (0.7 * 100 * 17) - 30;
        assertEquals(expected, SILab2.checkCart(items, "1234567888844"));
    }

    @Test
    public void test3_invalidItemName() {
        List<Item> items = List.of(
            new Item("", 1, 100, 0.2)
        );
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "2444666668888888")
        );
        assertTrue(ex.getMessage().contains("Invalid item!"));
    }

    @Test
    public void test4_discountZero() {
        List<Item> items = List.of(
            new Item("apple", 10, 11, 0.0)
        );
        // quantity=11 > 8 па ќе се одземе 30
        // 10 * 11 = 110
        double expected = 110 - 30;
        assertEquals(expected, SILab2.checkCart(items, "1234567812345678"));
    }

    @Test
    public void test5_cardNumberTooShort() {
        List<Item> items = List.of(
            new Item("apple", 10, 1, 0.4)
        );
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "114")
        );
        assertTrue(ex.getMessage().contains("Invalid card number!"));
    }

    @Test
    public void test6_cardNumberWithNonDigitChar() {
        List<Item> items = List.of(
            new Item("banana", 10, 1, 0.1)
        );
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "f1l1p4vl0vsk1")
        );
        assertTrue(ex.getMessage().contains("Invalid character in card number!"));
    }

    @Test
    public void testMultipleCondition_TXX() {
        List<Item> items = List.of(
            new Item("A", 0, 333, 0)
        );

        double expected = 333 - 30;
        assertEquals(expected, SILab2.checkCart(items, "1234567812345678"));
    }

    @Test
    public void testMultipleCondition_FTX() {
        List<Item> items = List.of(
            new Item("B", 0, 280, 15)
        );

        double expected = 280 - 15 * 280 / 100 - 30;
        assertEquals(expected, SILab2.checkCart(items, "1234567812345678"));
    }

    @Test
    public void testMultipleCondition_FFT() {
        List<Item> items = List.of(
            new Item("C", 30, 280, 0)
        );

        double expected = 280 - 30;
        assertEquals(expected, SILab2.checkCart(items, "1234567812345678"));
    }

    @Test
    public void testMultipleCondition_FFF() {
        List<Item> items = List.of(
            new Item("D", 8, 280, 0)
        );

        double expected = 280;
        assertEquals(expected, SILab2.checkCart(items, "1234567812345678"));
    }
}
