package com.game;

import com.atomic.Entity;
import com.boss.CallBack;
import com.boss.Labirinth;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {

    @Test
    public void testMoveBossWins() throws FileNotFoundException {
        // Create entities
        Entity player = new Entity("player", 10, 1, 2); // health = 10, speed = 1, damage = 2
        Room[][] rooms = {
                {new Room("src/main/resources/Rooms/1.txt", 1), new Room("src/main/resources/Rooms/2.txt", 2)},
                {new Room("src/main/resources/Rooms/3.txt", 3), new Room("src/main/resources/Rooms/4.txt", 4)}
        };
        RoomMap map = new RoomMap(rooms, 0, 0);
        Labirinth labirinth = new Labirinth(null);

        // Create GameManager instance
        GameManager gameManager = new GameManager(player, map, labirinth);

        // Perform moves to trigger boss fights
        assertEquals(0, gameManager.move('d')); // Move right to room 2 (no boss fight)
        assertEquals(0, gameManager.move('d')); // Move right to room 4 (no boss fight)
        assertEquals(1, gameManager.move('d')); // Move right to trigger first boss fight

        // Assert that GameManager proceeds to next phase (index 1)
        assertEquals(1, gameManager.move('d')); // Boss fight won, move to next phase (room 5)
    }

    @Test
    public void testMovePlayerWins() throws FileNotFoundException {
        // Create entities
        Entity player = new Entity("player", 10, 1, 2); // health = 10, speed = 1, damage = 2
        Room[][] rooms = {
                {new Room("src/main/resources/Rooms/1.txt", 1), new Room("src/main/resources/Rooms/2.txt", 2)},
                {new Room("src/main/resources/Rooms/3.txt", 3), new Room("src/main/resources/Rooms/4.txt", 4)},
                {new Room("src/main/resources/Rooms/5.txt", 5), new Room("src/main/resources/Rooms/6.txt", 6)},
                {new Room("src/main/resources/Rooms/7.txt", 7), new Room("src/main/resources/Rooms/8.txt", 8)}
        };
        RoomMap map = new RoomMap(rooms, 0, 0);
        Labirinth labirinth = new Labirinth(null);

        // Create GameManager instance
        GameManager gameManager = new GameManager(player, map, labirinth);

        // Perform moves to trigger boss fights
        assertEquals(0, gameManager.move('d')); // Move right to room 2 (no boss fight)
        assertEquals(0, gameManager.move('d')); // Move right to room 4 (no boss fight)
        assertEquals(1, gameManager.move('d')); // Move right to trigger first boss fight
        assertEquals(0, gameManager.move('d')); // Move right to room 5 (no boss fight)
        assertEquals(1, gameManager.move('d')); // Move right to trigger second boss fight

        // Assert that GameManager proceeds to next phase (index 2)
        assertEquals(1, gameManager.move('d')); // Boss fight won, move to next phase (room 6)
    }
}
