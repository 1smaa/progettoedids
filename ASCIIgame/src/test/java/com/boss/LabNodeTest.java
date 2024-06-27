package com.boss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.game.Room;

import java.io.FileNotFoundException;

public class LabNodeTest {

    // we created a TestRoom class so that we can override the load() method and avoid using an actual I/O file
    class TestRoom extends Room {
        public TestRoom() throws FileNotFoundException {
            super("testfile.txt", 1);
        }

        @Override
        public String load() {
            return "Room View";
        }
    }

    // test constructor and get() methods
    @Test
    public void testConstructorAndGet() {
        System.out.println("test constructor and get() methods");
        try {
            Room room = new TestRoom();
            LabNode left = new LabNode(null, null, null, room);
            LabNode right = new LabNode(null, null, null, room);
            LabNode back = new LabNode(null, null, null, room);
            LabNode node = new LabNode(left, right, back, room);

            assertEquals(left, node.getLeft());
            assertEquals(right, node.getRight());
            assertEquals(back, node.getBack());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException thrown in constructor test");
        }
    }

    // test getView() method
    @Test
    public void testGetView() {
        System.out.println("test getView() method");
        try {
            Room room = new TestRoom();
            LabNode node = new LabNode(null, null, null, room);

            assertEquals("Room View", node.getView());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException thrown in getView test");
        }
    }

    // test toggleFlag() method
    @Test
    public void testToggleFlag() {
        System.out.println("test toggleFlag() method");
        try {
            Room room = new TestRoom();
            LabNode node = new LabNode(null, null, null, room);

            assertFalse(node.flag);
            node.toogleFlag();
            assertTrue(node.flag);
            node.toogleFlag();
            assertFalse(node.flag);
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException thrown in toggleFlag test");
        }
    }

    // test setBack() method
    @Test
    public void testSetBack() {
        System.out.println("test setBack() method");
        try {
            Room room = new TestRoom();
            LabNode node = new LabNode(null, null, null, room);
            LabNode back = new LabNode(null, null, null, room);

            assertNull(node.getBack());
            node.setBack(back);
            assertEquals(back, node.getBack());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException thrown in setBack test");
        }
    }
}
