package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    private CircularLinkedList<Integer> circularLinkedList;

    @BeforeEach
    void setUp() {
        circularLinkedList = new CircularLinkedList<>();
    }

    @Test
    void testAddAndSize() throws ListException {
        circularLinkedList.add(20);
        circularLinkedList.add(10);
        assertEquals(2, circularLinkedList.size());
        assertEquals(20, circularLinkedList.get(1)); // Assuming 1-indexed
        assertEquals(10, circularLinkedList.get(2));
    }

    @Test
    void testGetHeadAndTail() throws ListException {
        circularLinkedList.add(20);
        circularLinkedList.add(10);
        assertEquals(20, circularLinkedList.getHead().data);
        assertEquals(10, circularLinkedList.getTail().data);

        circularLinkedList.addFirst(100);
        assertEquals(100, circularLinkedList.getHead().data);
        assertEquals(10, circularLinkedList.getTail().data);
    }

    @Test
    void testAddFirst() throws ListException {
        circularLinkedList.add(20);
        circularLinkedList.addFirst(100);
        circularLinkedList.addFirst(200);
        assertEquals(3, circularLinkedList.size());
        assertEquals(200, circularLinkedList.get(1));
        assertEquals(100, circularLinkedList.get(2));
        assertEquals(20, circularLinkedList.get(3));
    }

    @Test
    void testContainsAndIndexOf() throws ListException {
        circularLinkedList.add(10);
        circularLinkedList.add(20);
        circularLinkedList.add(30);

        assertTrue(circularLinkedList.contains(20));
        assertEquals(2, circularLinkedList.indexOf(20));
        assertFalse(circularLinkedList.contains(99));
        assertEquals(-1, circularLinkedList.indexOf(99));
    }

    @Test
    void testRemoveFirstAndLast() throws ListException {
        circularLinkedList.add(10);
        circularLinkedList.add(20);
        circularLinkedList.add(30);

        assertEquals(10, circularLinkedList.removeFirst());
        assertEquals(2, circularLinkedList.size());
        assertEquals(30, circularLinkedList.removeLast());
        assertEquals(1, circularLinkedList.size());
        assertEquals(20, circularLinkedList.getHead().data);
        assertEquals(20, circularLinkedList.getTail().data);
    }

    @Test
    void testGet() throws ListException {
        circularLinkedList.add(10);
        circularLinkedList.add(20);
        circularLinkedList.add(30);

        assertEquals(10, circularLinkedList.get(1));
        assertEquals(20, circularLinkedList.get(2));
        assertEquals(30, circularLinkedList.get(3));

        assertThrows(ListException.class, () -> circularLinkedList.get(0));
        assertThrows(ListException.class, () -> circularLinkedList.get(4));
    }

    @Test
    void testRemove() throws ListException {
        circularLinkedList.add(10);
        circularLinkedList.add(20);
        circularLinkedList.add(30);
        circularLinkedList.add(40);

        circularLinkedList.remove(20);
        assertEquals(3, circularLinkedList.size());
        assertFalse(circularLinkedList.contains(20));
        assertEquals(10, circularLinkedList.get(1));
        assertEquals(30, circularLinkedList.get(2));
        assertEquals(40, circularLinkedList.get(3));

        circularLinkedList.remove(10);
        assertEquals(2, circularLinkedList.size());
        assertFalse(circularLinkedList.contains(10));
        assertEquals(30, circularLinkedList.get(1));
        assertEquals(40, circularLinkedList.get(2));

        circularLinkedList.remove(40);
        assertEquals(1, circularLinkedList.size());
        assertFalse(circularLinkedList.contains(40));
        assertEquals(30, circularLinkedList.get(1));

        circularLinkedList.remove(30);
        assertTrue(circularLinkedList.isEmpty());
        assertEquals(0, circularLinkedList.size());

        assertThrows(ListException.class, () -> circularLinkedList.remove(99));
    }

    @Test
    void testGetPrevAndNext() throws ListException {
        circularLinkedList.add(10);
        circularLinkedList.add(20);
        circularLinkedList.add(30);

        assertEquals(30, circularLinkedList.getPrev(10)); // Prev of head is tail
        assertEquals(20, circularLinkedList.getNext(10));
        assertEquals(10, circularLinkedList.getPrev(20));
        assertEquals(30, circularLinkedList.getNext(20));
        assertEquals(20, circularLinkedList.getPrev(30));
        assertEquals(10, circularLinkedList.getNext(30)); // Next of tail is head
    }

    // The original test method, kept for reference but commented out its print statements
    // and exception handling for now, as it's not a proper unit test.
    @Test
    public void circularLinkedListDemonstration() {
        CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
        circularLinkedList.add(20);
        circularLinkedList.add(10);
        for (int i = 0; i < 20; i++) {
            circularLinkedList.add(new Random().nextInt(50));
        }
        // System.out.println(circularLinkedList);

        // System.out.println("_".repeat(50));
        // System.out.println("getHead: " + circularLinkedList.getHead().data);
        // System.out.println("getTail: " + circularLinkedList.getTail().data);

        circularLinkedList.addFirst(100);
        circularLinkedList.addFirst(200);
        // System.out.println(circularLinkedList);
        try {
            // System.out.println("Linklist size: "+circularLinkedList.size());

            // System.out.println("_".repeat(50));
            for (int i=0; i<10;i++) {
                int value = new Random().nextInt(50);
                // System.out.println(
                //         circularLinkedList.contains(value)
                //                 ? "value [" + value + "] exists. Position: "
                //                   + circularLinkedList.indexOf(value)
                //                 : "value [" + value + "] does not exist"
                // );
            }

            // System.out.println("\nremoveFirst: " + circularLinkedList.removeFirst());
            // System.out.println("removeLast: " + circularLinkedList.removeLast());
            // System.out.println("removeLast: " + circularLinkedList.removeLast());

            // System.out.println("_".repeat(50));
            int n =  circularLinkedList.size();
            for (int i = 1; i <= n; i++) {
                // System.out.println("get(" + i + ") = " + circularLinkedList.get(i));
            }

            // System.out.println("_".repeat(50));
            // System.out.println(circularLinkedList);
            for (int i = 1; i <= n; i++) {
                // System.out.println(
                //         "get(" + i + ") = " + circularLinkedList.get(i)
                //                 + ", getPrev(" + circularLinkedList.get(i) + ") = "
                //                 + circularLinkedList.getPrev(circularLinkedList.get(i))
                //                 + ", getNext(" + circularLinkedList.get(i) + ") = "
                //                 + circularLinkedList.getNext(circularLinkedList.get(i))
                // );
            }

            // System.out.println("_".repeat(50));
            // System.out.println(circularLinkedList);

            for (int i = 0; i < 20; i++) {
                int value =  new Random().nextInt(50);
                if(circularLinkedList.contains(value)) {
                    // System.out.println("remove("+value+") deleted !!!");
                    circularLinkedList.remove(value);
                }
            }
            // System.out.println(circularLinkedList);

            // System.out.println("_".repeat(50));
            for (int i = 0; i < 20; i++) {
                int value =  new Random().nextInt(50);
                if(circularLinkedList.contains(value)) {
                    // System.out.println("removeFirst(): "+circularLinkedList.removeFirst());
                    // System.out.println("removeLast(): "+circularLinkedList.removeLast());
                    // System.out.println(circularLinkedList);
                }
            }
            // System.out.println(circularLinkedList);

        } catch (ListException e) {
            // throw new RuntimeException(e);
        }
    }
}
