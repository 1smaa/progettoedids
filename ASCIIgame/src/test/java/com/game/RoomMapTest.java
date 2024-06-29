package com.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class    RoomMapTest {

    @Test
    public void testConstructor() throws FileNotFoundException {
        System.out.println("test constructor");

        // create a valid test files
        String validFile1 = "valid_file1.txt";
        createTestFile(validFile1, "This is a test file");
        String validFile2 = "valid_file2.txt";
        createTestFile(validFile2, "This is a test file");

        Room room1 = new Room("valid_file1.txt", 1, true);
        Room room2 = new Room("valid_file2.txt", 2, true);
        Room[][] rooms = {
            {room1, room2},
            {room2, room1}
        };

        RoomMap roomMap = new RoomMap(rooms, 0, 0);
        assertEquals(room1, roomMap.get(0, 0));
        assertEquals(room2, roomMap.get(1, 0));
        assertEquals(room1, roomMap.get(0, 1));

        // delete test files
        deleteTestFile(validFile1);
        deleteTestFile(validFile2);
    }

    // test consturcor with invalid arguments
    @Test
    public void testConstructorInvalidArguments() {
        System.out.println("test constructor with invalid arguments");
        Room[][] rooms = new Room[0][0];
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomMap(rooms, 0, 0);
        });
    }

    // test move() method
    @Test
    public void testMove() throws FileNotFoundException {
        System.out.println("test move() method");

        // create a valid test files
        String validFile1 = "valid_file1.txt";
        createTestFile(validFile1, "This is a test file");
        String validFile2 = "valid_file2.txt";
        createTestFile(validFile2, "This is a test file");

        Room room1 = new Room("valid_file1.txt", 1, true);
        Room room2 = new Room("valid_file2.txt", 2, true);
        Room[][] rooms = {
            {room1, room2},
            {room2, room1}
        };

        RoomMap roomMap = new RoomMap(rooms, 0, 0);
        assertTrue(roomMap.move('d'));
        assertEquals(room2, roomMap.get(1, 0));
    }

    @Test
    public void testMoveOutOfBounds() throws FileNotFoundException {
        Room room1 = new Room("src/main/resources/Rooms/1.txt", 1, true);
        Room[][] rooms = {
            {room1, null},
            {null, null}
        };

        RoomMap roomMap = new RoomMap(rooms, 0, 0);
        assertFalse(roomMap.move('d'));
    }

    @Test
    public void testHasLast() throws FileNotFoundException {
        Room room1 = new Room("src/main/resources/Rooms/1.txt", 1, true);
        Room room2 = new Room("src/main/resources/Rooms/2.txt", 2, true);
        Room[][] rooms = {
            {room1, room2},
            {room2, room1}
        };

        RoomMap roomMap = new RoomMap(rooms, 0, 0);
        assertFalse(roomMap.hasLast());
        roomMap.move('d');
        assertTrue(roomMap.hasLast());
    }

    @Test
    public void testCurr() throws FileNotFoundException {
        Room room1 = new Room("src/main/resources/Rooms/1.txt", 1, true);
        Room room2 = new Room("src/main/resources/Rooms/2.txt", 2, true);
        Room[][] rooms = {
            {room1, room2},
            {room2, room1}
        };

        RoomMap roomMap = new RoomMap(rooms, 0, 0);
        assertEquals(room1.load(), roomMap.curr());
    }

    @Test
    public void testCurrId() throws FileNotFoundException {
        Room room1 = new Room("src/main/resources/Rooms/1.txt", 1, true);
        Room room2 = new Room("src/main/resources/Rooms/2.txt", 2, true);
        Room[][] rooms = {
            {room1, room2},
            {room2, room1}
        };

        RoomMap roomMap = new RoomMap(rooms, 0, 0);
        assertEquals(1, roomMap.currId());
        roomMap.move('d');
        assertEquals(2, roomMap.currId());
    }

    // helper methods to create and delete test files
    private void createTestFile(String fileName, String text) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
