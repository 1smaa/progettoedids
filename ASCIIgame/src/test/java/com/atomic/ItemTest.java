package com.atomic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;

    // test constructor
    @Test
    public void testConstructor() {
        System.out.println("test Constructor");
        item = new Item(2, 5, "kn", 3, "knife");
        
        // check if the value return
        assertEquals(2, item.damage);
        assertEquals(5, item.speed);
        assertEquals("kn", item.item);
        assertEquals(3, item.weight);
        assertEquals("kn", item.longName);
    }
}
