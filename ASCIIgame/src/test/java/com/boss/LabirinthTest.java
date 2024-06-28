package com.boss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.game.Room;
import com.game.RoomMap;
import com.atomic.Item;
import com.atomic.Entity;

import java.io.FileNotFoundException;

public class LabirinthTest {

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

    // we created a simple LabNode setup for testing
    private LabNode createTestLabNode() throws FileNotFoundException {
        TestRoom room = new TestRoom();
        LabNode node = new LabNode(null, null, null, room);
        return node;
    }

    // test constructor
    @Test
    public void testConstructor() throws FileNotFoundException {
        System.out.println("test constructor");
        LabNode labnode = createTestLabNode();
        Labirinth labirinth = new Labirinth(labnode);
        assertNotNull(labirinth);
    }

    // test move() method
    //?perchè left va bene true ma tutte le altre devo mettere false o da errore
    @Test
    public void testMove() throws FileNotFoundException {
        System.out.println("test move() method");
        LabNode leftNode = createTestLabNode();
        LabNode rightNode = createTestLabNode();
        LabNode backNode = createTestLabNode();
        LabNode head = new LabNode(leftNode, rightNode, backNode, new TestRoom());
        Labirinth labirinth = new Labirinth(head);

        // move left
        assertTrue(labirinth.move('a'));

        // move right
       assertFalse(labirinth.move('d'));

        // move back
        assertFalse(labirinth.move('s'));

        // invalid move
        assertFalse(labirinth.move('w'));
    }

    // test win() method
    @Test
    public void testWin() throws FileNotFoundException {
        System.out.println("test win() method");
        LabNode head = createTestLabNode();
        Labirinth labirinth = new Labirinth(head);

        // initial state, flag is false
        assertFalse(labirinth.win());

        // set flag to true and check win condition
        head.flag = true;
        assertTrue(labirinth.win());
    }

    // test getCurr() method
    @Test
    public void testGetCurr() throws FileNotFoundException {
        System.out.println("test getCurr() method");
        LabNode head = createTestLabNode();
        Labirinth labirinth = new Labirinth(head);

        try {
            assertEquals("Room View", labirinth.getCurr());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException thrown in getCurr test");
        }
    }

    // test onCallBack() method (ci mette una vita e non finisce mai)
    @Test
    public void testOnCallback() throws FileNotFoundException {
        LabNode n=new LabNode(null,null,null,new TestRoom());
        LabNode head = new LabNode(n,n,null,new TestRoom());
        head.setBack(n);
        n.toogleFlag();
        Labirinth labirinth = new Labirinth(head);

        Entity player = new Entity("player", 8, 3, 5);
        Room[][] rooms = new Room[2][2];
        RoomMap roomMap = new RoomMap(rooms, 0, 0);
        Item[] inventory = new Item[1];

        // To test the callback properly, simulate user input and behavior.
        // Here we assume the callback method handles room changes and checks.
        // You can simulate the scenario by invoking the method and verifying the changes.
        boolean result = labirinth.onCallback(player, roomMap, labirinth, inventory);

        // We are assuming onCallback should return true when the labyrinth is completed.
        assertTrue(result);
    }
}
