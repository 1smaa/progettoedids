package com.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

public class RoomTest {
    private Room room;

    // tests with valid file
    @Test
    public void TestConstructorsAndMethodsValid() throws FileNotFoundException {
        System.out.println("Test with valid file");

        // create a valid test file
        String validFile = "valid_file.txt";
        createTestFile(validFile, "This is a test file");

        // test constructor 1
        System.out.println("Test constructor 1");
        room = new Room(validFile, 1);

        // test constructor 2
        System.out.println("Test constructor 2");
        room = new Room(validFile, 1, true);

        System.out.println("Test methods");
        // check expected values and test methods
        assertEquals(1, room.getId());       // test getId() method
        assertTrue(room.valid());       // test valid() method
        
        // test setValid() method
        room.setValid(false);
        assertFalse(room.valid());

        // test load method
        String loadedContent = room.load();
        assertTrue(loadedContent.contains("This is a test file"));
        
        // delete test file
        deleteTestFile(validFile);
    }

    // test with invalid file
    @Test
    public void TestConstructorsAndMethodsInvalid() throws FileNotFoundException {
        System.out.println("Test with invalid file");
        
        // non-existent file
        String invalidFile = "invalid_file.txt";

        // ensure the file does not exist
        assertFalse(new File(invalidFile).exists());

        // try to create room with invalid file, should throw FileNotFoundException
        assertThrows(FileNotFoundException.class, () -> new Room(invalidFile, 2));
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
