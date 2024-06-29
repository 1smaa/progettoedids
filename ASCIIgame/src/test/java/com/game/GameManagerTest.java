package com.game;

import com.atomic.Entity;
import com.boss.CallBack;
import com.boss.Labirinth;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {

    @Test
    public void testMoveBossWins() throws FileNotFoundException {
        // Create entities
        Entity player = new Entity("player", 10, 1, 2); // health = 10, speed = 1, damage = 2
        RoomMap map;
        Labirinth labirinth=new Labirinth(null);
        try(FileInputStream fin=new FileInputStream("stdmap.config");
            ObjectInputStream oin=new ObjectInputStream(fin)){
            map=(RoomMap)oin.readObject();
        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
            return;
        }

        //Create string for boss fight long enough that it is very likely to win
        String s="";
        for(int i=0;i<10;i++) s+="g\n";

        InputStream sub=new ByteArrayInputStream(s.getBytes());
        InputStream og=System.in;
        System.setIn(sub);
        // Create GameManager instance
        GameManager gameManager = new GameManager(player, map, labirinth);

        // Perform moves to trigger boss fights
        assertEquals(0, gameManager.move('w')); // Move right to room 2 (no boss fight)

        // Assert that GameManager proceeds to next phase (index 1)
        assertEquals(-1, gameManager.move('d')); // Boss fight won, move to next phase (room 5)
    }

    @Test
    public void testMovePlayerWins() throws FileNotFoundException {
        // Create entities
        Entity player = new Entity("player", 1000, 1, 2); // health = 10, speed = 1, damage = 2
        RoomMap map;
        Labirinth labirinth=new Labirinth(null);
        try(FileInputStream fin=new FileInputStream("stdmap.config");
            ObjectInputStream oin=new ObjectInputStream(fin)){
            map=(RoomMap)oin.readObject();
        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
            return;
        }

        //Create string for boss fight long enough that it is very likely to win
        String s="";
        for(int i=0;i<1000;i++) s+="w\n";

        InputStream sub=new ByteArrayInputStream(s.getBytes());
        InputStream og=System.in;
        System.setIn(sub);
        // Create GameManager instance
        GameManager gameManager = new GameManager(player, map, labirinth);

        // Perform moves to trigger boss fights
        assertEquals(0, gameManager.move('w')); // Move right to room 2 (no boss fight)

        // Assert that GameManager proceeds to next phase (index 1)
        assertEquals(-1, gameManager.move('d')); // Boss fight won, move to next phase (room 6)
    }
}
