package model;

import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

    @Test
    void testAdd() {
        try {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

            System.out.println("===== TEST ADD =====");
            System.out.println("Lista vacia? " + list.isEmpty());

            list.add(10);
            list.add(20);
            list.add(30);
            list.add(40);

            System.out.println("Lista despues de agregar elementos:");
            System.out.println(list);
            System.out.println("Size: " + list.size());
            System.out.println("First: " + list.getFirst());
            System.out.println("Last: " + list.getLast());
            System.out.println();

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testAddFirstAndAddLast() {
        try {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

            System.out.println("===== TEST ADD FIRST / ADD LAST =====");

            list.add(20);
            list.add(30);
            System.out.println("Lista inicial:");
            System.out.println(list);

            list.addFirst(10);
            System.out.println("Despues de addFirst(10):");
            System.out.println(list);

            list.addLast(40);
            System.out.println("Despues de addLast(40):");
            System.out.println(list);

            System.out.println("First: " + list.getFirst());
            System.out.println("Last: " + list.getLast());
            System.out.println("Size: " + list.size());
            System.out.println();

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testContainsAndIndexOf() {
        try {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

            System.out.println("===== TEST CONTAINS / INDEX OF =====");

            list.add(15);
            list.add(25);
            list.add(35);
            list.add(45);

            System.out.println("Lista:");
            System.out.println(list);

            System.out.println("Contains 15: " + list.contains(15));
            System.out.println("Contains 35: " + list.contains(35));
            System.out.println("Contains 99: " + list.contains(99));

            System.out.println("Index of 15: " + list.indexOf(15));
            System.out.println("Index of 25: " + list.indexOf(25));
            System.out.println("Index of 35: " + list.indexOf(35));
            System.out.println("Index of 99: " + list.indexOf(99));
            System.out.println();

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testGet() {
        try {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

            System.out.println("===== TEST GET =====");

            list.add(100);
            list.add(200);
            list.add(300);
            list.add(400);

            System.out.println("Lista:");
            System.out.println(list);

            System.out.println("get(1): " + list.get(1));
            System.out.println("get(2): " + list.get(2));
            System.out.println("get(3): " + list.get(3));
            System.out.println("get(4): " + list.get(4));
            System.out.println("get(5): " + list.get(5));
            System.out.println();

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testGetPrevAndGetNext() {
        try {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

            System.out.println("===== TEST GET PREV / GET NEXT =====");

            list.add(10);
            list.add(20);
            list.add(30);
            list.add(40);

            System.out.println("Lista:");
            System.out.println(list);

            System.out.println("Prev de 10: " + list.getPrev(10));
            System.out.println("Prev de 20: " + list.getPrev(20));
            System.out.println("Prev de 30: " + list.getPrev(30));
            System.out.println("Prev de 40: " + list.getPrev(40));

            System.out.println("Next de 10: " + list.getNext(10));
            System.out.println("Next de 20: " + list.getNext(20));
            System.out.println("Next de 30: " + list.getNext(30));
            System.out.println("Next de 40: " + list.getNext(40));
            System.out.println();

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testRemove() {
        try {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

            System.out.println("===== TEST REMOVE =====");

            list.add(10);
            list.add(20);
            list.add(30);
            list.add(40);
            list.add(50);

            System.out.println("Lista original:");
            System.out.println(list);

            list.remove(10);
            System.out.println("Despues de remove(10), eliminando el primero:");
            System.out.println(list);

            list.remove(30);
            System.out.println("Despues de remove(30), eliminando del medio:");
            System.out.println(list);

            list.remove(50);
            System.out.println("Despues de remove(50), eliminando el ultimo:");
            System.out.println(list);

            list.remove(999);
            System.out.println("Despues de remove(999), elemento que no existe:");
            System.out.println(list);

            System.out.println("Size: " + list.size());
            System.out.println("First: " + list.getFirst());
            System.out.println("Last: " + list.getLast());
            System.out.println();

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testRemoveFirstAndRemoveLast() {
        try {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

            System.out.println("===== TEST REMOVE FIRST / REMOVE LAST =====");

            list.add(10);
            list.add(20);
            list.add(30);
            list.add(40);

            System.out.println("Lista original:");
            System.out.println(list);

            System.out.println("removeFirst(): " + list.removeFirst());
            System.out.println("Lista despues de removeFirst:");
            System.out.println(list);

            System.out.println("removeLast(): " + list.removeLast());
            System.out.println("Lista despues de removeLast:");
            System.out.println(list);

            System.out.println("Size: " + list.size());
            System.out.println("First: " + list.getFirst());
            System.out.println("Last: " + list.getLast());
            System.out.println();

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testEmptyListExceptions() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        System.out.println("===== TEST EMPTY LIST EXCEPTIONS =====");

        try {
            System.out.println("Size: " + list.size());
        } catch (ListException e) {
            System.out.println("size(): " + e.getMessage());
        }

        try {
            System.out.println("Contains 10: " + list.contains(10));
        } catch (ListException e) {
            System.out.println("contains(10): " + e.getMessage());
        }

        try {
            System.out.println("removeFirst(): " + list.removeFirst());
        } catch (ListException e) {
            System.out.println("removeFirst(): " + e.getMessage());
        }

        try {
            System.out.println("removeLast(): " + list.removeLast());
        } catch (ListException e) {
            System.out.println("removeLast(): " + e.getMessage());
        }

        try {
            System.out.println("getFirst(): " + list.getFirst());
        } catch (ListException e) {
            System.out.println("getFirst(): " + e.getMessage());
        }

        try {
            System.out.println("getLast(): " + list.getLast());
        } catch (ListException e) {
            System.out.println("getLast(): " + e.getMessage());
        }

        try {
            System.out.println("get(1): " + list.get(1));
        } catch (ListException e) {
            System.out.println("get(1): " + e.getMessage());
        }

        try {
            System.out.println("indexOf(10): " + list.indexOf(10));
        } catch (ListException e) {
            System.out.println("indexOf(10): " + e.getMessage());
        }

        System.out.println();
    }
}