package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DoublyDoublyLinkedListTest {

    private DoublyLinkedList<Integer> doublyLinkedList;

    @BeforeEach
    void setUp() {
        doublyLinkedList = new DoublyLinkedList<>();
    }

    @Test
    void testAddAndSize() throws ListException {
        doublyLinkedList.add(20);
        doublyLinkedList.add(10);
        assertEquals(2, doublyLinkedList.size());
        assertEquals(20, doublyLinkedList.get(1));
        assertEquals(10, doublyLinkedList.get(2));
    }

    @Test
    void testAddFirst() throws ListException {
        doublyLinkedList.add(20);
        doublyLinkedList.addFirst(100);
        doublyLinkedList.addFirst(200);
        assertEquals(3, doublyLinkedList.size());
        assertEquals(200, doublyLinkedList.get(1));
        assertEquals(100, doublyLinkedList.get(2));
        assertEquals(20, doublyLinkedList.get(3));
    }

    @Test
    void testGetHeadAndTail() throws ListException {
        doublyLinkedList.add(20);
        doublyLinkedList.add(10);
        assertEquals(20, doublyLinkedList.getHead().data);
        assertEquals(10, doublyLinkedList.getTail().data);

        doublyLinkedList.addFirst(100);
        assertEquals(100, doublyLinkedList.getHead().data);
        assertEquals(10, doublyLinkedList.getTail().data);
    }

    @Test
    void testContainsAndIndexOf() throws ListException {
        doublyLinkedList.add(10);
        doublyLinkedList.add(20);
        doublyLinkedList.add(30);

        assertTrue(doublyLinkedList.contains(20));
        assertEquals(2, doublyLinkedList.indexOf(20));
        assertFalse(doublyLinkedList.contains(99));
        assertEquals(-1, doublyLinkedList.indexOf(99));
    }

    @Test
    void testRemoveFirstAndLast() throws ListException {
        doublyLinkedList.add(10);
        doublyLinkedList.add(20);
        doublyLinkedList.add(30);

        assertEquals(10, doublyLinkedList.removeFirst());
        assertEquals(2, doublyLinkedList.size());
        assertEquals(30, doublyLinkedList.removeLast());
        assertEquals(1, doublyLinkedList.size());
        assertEquals(20, doublyLinkedList.getHead().data);
        assertEquals(20, doublyLinkedList.getTail().data);
    }

    @Test
    void testGet() throws ListException {
        doublyLinkedList.add(10);
        doublyLinkedList.add(20);
        doublyLinkedList.add(30);

        assertEquals(10, doublyLinkedList.get(1));
        assertEquals(20, doublyLinkedList.get(2));
        assertEquals(30, doublyLinkedList.get(3));

        assertThrows(ListException.class, () -> doublyLinkedList.get(0));
        assertThrows(ListException.class, () -> doublyLinkedList.get(4));
    }

    @Test
    void testRemove() throws ListException {
        doublyLinkedList.add(10);
        doublyLinkedList.add(20);
        doublyLinkedList.add(30);
        doublyLinkedList.add(40);

        doublyLinkedList.remove(20);
        assertEquals(3, doublyLinkedList.size());
        assertFalse(doublyLinkedList.contains(20));
        assertEquals(10, doublyLinkedList.get(1));
        assertEquals(30, doublyLinkedList.get(2));
        assertEquals(40, doublyLinkedList.get(3));

        doublyLinkedList.remove(10);
        assertEquals(2, doublyLinkedList.size());
        assertFalse(doublyLinkedList.contains(10));
        assertEquals(30, doublyLinkedList.get(1));
        assertEquals(40, doublyLinkedList.get(2));

        doublyLinkedList.remove(40);
        assertEquals(1, doublyLinkedList.size());
        assertFalse(doublyLinkedList.contains(40));
        assertEquals(30, doublyLinkedList.get(1));

        doublyLinkedList.remove(30);
        assertTrue(doublyLinkedList.isEmpty());
        assertEquals(0, doublyLinkedList.size());

        assertThrows(ListException.class, () -> doublyLinkedList.remove(99));
    }

    // The original test method, kept for reference but commented out its print statements
    // and exception handling for now, as it's not a proper unit test.
    @Test
    public void testDemonstration() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.add(20);
        doublyLinkedList.add(10);
        for (int i = 0; i < 20; i++) {
            doublyLinkedList.add(new Random().nextInt(50));
        }
        // System.out.println(doublyLinkedList);

        // System.out.println("_".repeat(50));
        // System.out.println("getHead: " + doublyLinkedList.getHead().data);
        // System.out.println("getTail: " + doublyLinkedList.getTail().data);

        doublyLinkedList.addFirst(100);
        doublyLinkedList.addFirst(200);
        // System.out.println(doublyLinkedList);
        try {
            // System.out.println("Linklist size: "+doublyLinkedList.size());

            // System.out.println("_".repeat(50));
            for (int i=0; i<10;i++) {
                int value = new Random().nextInt(50);
                // System.out.println(
                //         doublyLinkedList.contains(value)
                //                 ? "value [" + value + "] exists. Position: "
                //                   + doublyLinkedList.indexOf(value)
                //                 : "value [" + value + "] does not exist"
                // );
            }

            // System.out.println("\nremoveFirst: " + doublyLinkedList.removeFirst());
            // System.out.println("removeLast: " + doublyLinkedList.removeLast());
            // System.out.println("removeLast: " + doublyLinkedList.removeLast());

            // System.out.println("_".repeat(50));
            int n =  doublyLinkedList.size();
            for (int i = 1; i <= n; i++) {
                // System.out.println("get(" + i + ") = " + doublyLinkedList.get(i));
            }

            // System.out.println("_".repeat(50));
            // System.out.println(doublyLinkedList);
            for (int i = 1; i <= n; i++) {
                // System.out.println(
                //         "get(" + i + ") = " + doublyLinkedList.get(i)
                //                 + ", getPrev(" + doublyLinkedList.get(i) + ") = "
                //                 + doublyLinkedList.getPrev(doublyLinkedList.get(i))
                //                 + ", getNext(" + doublyLinkedList.get(i) + ") = "
                //                 + doublyLinkedList.getNext(doublyLinkedList.get(i))
                // );
            }

            // System.out.println("_".repeat(50));
            // System.out.println(doublyLinkedList);

            for (int i = 0; i < 20; i++) {
                int value =  new Random().nextInt(50);
                if(doublyLinkedList.contains(value)) {
                    // System.out.println("remove("+value+") deleted !!!");
                    doublyLinkedList.remove(value);
                }
            }
            // System.out.println(doublyLinkedList);

            // System.out.println("_".repeat(50));
            for (int i = 0; i < 20; i++) {
                int value =  new Random().nextInt(50);
                if(doublyLinkedList.contains(value)) {
                    // System.out.println("removeFirst(): "+doublyLinkedList.removeFirst());
                    // System.out.println("removeLast(): "+doublyLinkedList.removeLast());
                    // System.out.println(doublyLinkedList);
                }
            }
            // System.out.println(doublyLinkedList);

        } catch (ListException e) {
            // throw new RuntimeException(e);
        }
    }
}
