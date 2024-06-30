package com.boss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.atomic.Entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class CombatTest {
    // test constructor and start() method
    @Test
    public void testConstructorAndStart() {
        System.out.println("test constructor and start() method");
        Entity entity = new Entity("boss", 2, 3, 2);
        Entity player = new Entity("player", 25, 10, 10);

        //Create string for boss fight long enough that it is very likely to win
        String s="";
        for(int i=0;i<10;i++) s+="w\n";

        InputStream sub=new ByteArrayInputStream(s.getBytes());
        InputStream og=System.in;
        System.setIn(sub);

        // create Combat instance
        Combat combat = new Combat(entity, player);

        assertEquals(0, combat.start(s));

        System.setIn(og);
    }
}
