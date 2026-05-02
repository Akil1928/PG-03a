package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>();
    }

    @Test
    void testAddAndSize() throws ListException {
        linkedList.add(20);
        linkedList.add(10);
        assertEquals(2, linkedList.size());
        assertEquals(20, linkedList.get(0)); // Assuming 0-indexed or 1-indexed based on implementation
        assertEquals(10, linkedList.get(1)); // Adjust index if get() is 1-based
    }

    @Test
    void testGetHeadAndTail() throws ListException {
        linkedList.add(20);
        linkedList.add(10);
        assertEquals(20, linkedList.getHead().data);
        assertEquals(10, linkedList.getTail().data);

        linkedList.addFirst(100);
        assertEquals(100, linkedList.getHead().data);
        assertEquals(10, linkedList.getTail().data);
    }

    @Test
    void testAddFirst() throws ListException {
        linkedList.add(20);
        linkedList.addFirst(100);
        linkedList.addFirst(200);
        assertEquals(3, linkedList.size());
        assertEquals(200, linkedList.get(0)); // Assuming 0-indexed
        assertEquals(100, linkedList.get(1));
        assertEquals(20, linkedList.get(2));
    }

    @Test
    void testContains() throws ListException {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        assertTrue(linkedList.contains(20));
        assertFalse(linkedList.contains(99));
    }

    @Test
    void testGet() throws ListException {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        assertEquals(10, linkedList.get(0)); // Assuming 0-indexed
        assertEquals(20, linkedList.get(1));
        assertEquals(30, linkedList.get(2));

        assertThrows(ListException.class, () -> linkedList.get(-1));
        assertThrows(ListException.class, () -> linkedList.get(3)); // Out of bounds
    }

    // The original test method, kept for reference but commented out its print statements
    // and exception handling for now, as it's not a proper unit test.
    @Test
    void linkedListDemonstration() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(20);
        linkedList.add(10);

        for (int i = 0; i < 20; i++) {
            linkedList.add(new Random().nextInt(50));
        }

        // System.out.println(linkedList);

        // System.out.println("getHead: " + linkedList.getHead().data);
        // System.out.println("getTail: " + linkedList.getTail().data);

        // System.out.println("addFirst(100)");
        linkedList.addFirst(100);
        // System.out.println(linkedList);

        try {
            // System.out.println("LinkedList size: " + linkedList.size());

            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(50);
                // System.out.println(
                //         linkedList.contains(value)
                //                 ? "value [" + value + "] exists"
                //                 : "value [" + value + "] does not exist"
                // );
            }

            int n = linkedList.size();
            for(int i = 0; i < 10; i++) { // This loop might go out of bounds if n < 10
                // System.out.println("get(" + i + "): " + linkedList.get(i));
            }

        } catch (ListException e) {
            // throw new RuntimeException(e);
        }
    }
}
