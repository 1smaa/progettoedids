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

    private Entity player;
    private Room room1, room2;
    private RoomMap map;
    private LabNode h;
    private Labirinth labirinth;
    private Item[] inventory;
    private FirstBoss firstBoss;

    @Test
    void testOnCallback() throws IOException {
        player = new Entity("Player", 20, 5, 3);
        room1 = new Room("validFile1", 1, true);
        room2 = new Room("validFile2", 2, true);
        Room[][] rooms = {
                {room1, room2},
                {room2, room1}
        };
        map = new RoomMap(rooms, 0, 0);
        h = new LabNode(null, null, null, room1);
        labirinth = new Labirinth(h);
        inventory = new Item[]{new Item(1, 1, "D", 1, "Dagger")};
        firstBoss = new FirstBoss();
        // Prepare the contents for FirstBoss.txt
        String bossScript = "combat script content";
        createTestFile("src/test/resources/Boss/FirstBoss.txt", bossScript);

        // Prepare the contents for voidover.txt
        String voidOverScript = "void over script with placeholders 1 and 2";
        createTestFile("src/test/resources/voidover.txt", voidOverScript);

        // Simulate user input 'y' for picking up the dagger
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream("y\n".getBytes()));

            // Call the method under test
            boolean result = firstBoss.onCallback(player, map, labirinth, inventory);

            // Assert the result
            assertTrue(result);
            assertEquals("Dagger", inventory[0]);
        } finally {
            // Restore System.in
            System.setIn(stdin);
        }

        // Clean up the test files
        deleteTestFile("src/test/resources/Boss/FirstBoss.txt");
        deleteTestFile("src/test/resources/voidover.txt");
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