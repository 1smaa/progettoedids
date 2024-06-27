package com.atomic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    private Entity entity;

    // test constructor
    @Test
    public void TestConstructor() {
        System.out.println("test Constructor");
        entity = new Entity("Name", 50, 10, 3);
        
        // check if the value return
        assertEquals("Name", entity.name);
        assertEquals(50, entity.health);
        assertEquals(10, entity.damage);
        assertEquals(3, entity.speed);
    }
}
