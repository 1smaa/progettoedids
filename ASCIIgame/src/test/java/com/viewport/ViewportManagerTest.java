package com.viewport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ViewportManagerTest {

    // test default constructor
    @Test
    public void testConstructor() {
        System.out.println("tets default constructor");
        ViewportManager manager = new ViewportManager();
        assertEquals("", manager.getOverlay());
    }

    // test get and seOverlay methods
    @Test
    public void testSetAndGetOverlay() {
        System.out.println("tets getOverlay() and setOverlay()");
        ViewportManager manager = new ViewportManager();
        String overlay = "###\n###\n###";
        manager.setOverlay(overlay);
        assertEquals(overlay, manager.getOverlay());
    }

    // test format() method without using an overlay
    @Test
    public void testFormatWithoutOverlay() {
        System.out.println("tets format() method without using an overlay");
        ViewportManager manager = new ViewportManager();
        String viewport = "123\n456\n789";
        String result = manager.format(viewport);
        assertEquals(viewport, result);
    }

    // test format() method using an overlay
    @Test
    public void testFormatWithOverlay() throws IllegalArgumentException{
        System.out.println("tets format() method using an overlay");
        ViewportManager manager = new ViewportManager();
        String overlay = "###\n###\n###";
        String viewport = "123\n456\n789";
        manager.setOverlay(overlay);
        String expected = "###\n#12\n#45\n";
        String result = manager.format(viewport);
        assertEquals(expected, result);
    }
    

    // test format() method with empty overlay
    @Test
    public void testFormatWithEmptyViewport() {
        System.out.println("tets format() method with empty overlay");
        ViewportManager manager = new ViewportManager();
        String overlay = "###\n###\n###";
        String viewport = "";
        manager.setOverlay(overlay);
        String expected = "###\n###\n###\n";
        String result = manager.format(viewport);
        assertEquals(expected, result);
    }
}
