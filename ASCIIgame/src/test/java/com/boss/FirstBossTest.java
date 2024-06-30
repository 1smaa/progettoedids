package com.boss;

import com.atomic.Entity;
import com.atomic.Item;
import com.game.Room;
import com.game.RoomMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FirstBossTest {

     // we created a TestRoom class so that we can override the load() method and avoid using an actual I/O file
     class TestRoom extends Room {
        public TestRoom() throws FileNotFoundException {
            super("src/test/resources/testroom.txt", 1);
        }

        @Override
        public String load() {
            return "Room View";
        }
    }

    // we created a simple LabNode setup for testing
    private LabNode createTestLabNode() throws FileNotFoundException {
        TestRoom room = new TestRoom();
        LabNode node = new LabNode(null, null, null, room);
        return node;
    }

    @Test
    void testOnCallback() throws IOException {
        System.out.println("test onCallback() method");

        // create test entities
        FirstBoss firstBoss = new FirstBoss();
        Entity player = new Entity("player", 10, 10, 10);

        // create test roommap
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

        // delete test files
        deleteTestFile(validFile1);
        deleteTestFile(validFile2);

        // create test labirinth
        LabNode labnode = createTestLabNode();
        Labirinth labirinth = new Labirinth(labnode);

        // create test inventory
        Item[] inventory = new Item[2];

        // Simulate user input 'y' for picking up the dagger
        String s = "y\n";
        InputStream sub = new ByteArrayInputStream(s.getBytes());
        InputStream og = System.in;
        System.setIn(sub);
        // Call the method under test
        boolean result = firstBoss.onCallback(player, roomMap, labirinth, inventory);

        // Assert the result
        assertTrue(result);
        assertEquals("Dagger", inventory[0]);

        // Clean up the test files
        deleteTestFile("src/test/resources/Boss/FirstBoss.txt");
        deleteTestFile("src/test/resources/voidover.txt");

        System.setIn(og);
    }

    private void createTestFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }

    private void deleteTestFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
