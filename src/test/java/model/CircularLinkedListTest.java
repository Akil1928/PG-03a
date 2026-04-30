package model;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {
    @Test
    public void doublyDoublyLinkedListTest() {
        CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
        circularLinkedList.add(20);
        circularLinkedList.add(10);
        for (int i = 0; i < 20; i++) {
            circularLinkedList.add(new Random().nextInt(50));
        }
        System.out.println(circularLinkedList);

        //quiero ver la data del primero y ult nodo de la lista
        System.out.println("_".repeat(50));
        System.out.println("getHead: " + circularLinkedList.getHead().data);
        System.out.println("getTail: " + circularLinkedList.getTail().data);

        System.out.println("addFirst(100)"); circularLinkedList.addFirst(100);
        System.out.println("addFirst(200)"); circularLinkedList.addFirst(200);
        System.out.println(circularLinkedList);
        try {
            System.out.println("Linklist size: "+circularLinkedList.size());

            //probamos contains
            System.out.println("_".repeat(50));
            for (int i=0; i<10;i++) {
                int value = new Random().nextInt(50);
                System.out.println(
                        circularLinkedList.contains(value)
                                ? "value [" + value + "] exists. Position: "
                                  + circularLinkedList.indexOf(value)
                                : "value [" + value + "] does not exist"
                );
            }

            //Probamos removeFirst, removeLast
            System.out.println("\nremoveFirst: " + circularLinkedList.removeFirst());
            System.out.println("removeLast: " + circularLinkedList.removeLast());
            System.out.println("removeLast: " + circularLinkedList.removeLast());

            //probamos get
            System.out.println("_".repeat(50));
            int n =  circularLinkedList.size();
            for (int i = 1; i <= n; i++) {
                System.out.println("get(" + i + ") = " + circularLinkedList.get(i));
            }

            System.out.println("_".repeat(50));
            System.out.println(circularLinkedList);
            for (int i = 1; i <= n; i++) {
                System.out.println(
                        "get(" + i + ") = " + circularLinkedList.get(i)
                                + ", getPrev(" + circularLinkedList.get(i) + ") = "
                                + circularLinkedList.getPrev(circularLinkedList.get(i))
                                + ", getNext(" + circularLinkedList.get(i) + ") = "
                                + circularLinkedList.getNext(circularLinkedList.get(i))
                );
            }

            //al final volvemos a mostrar la lista
            System.out.println("_".repeat(50));
            System.out.println(circularLinkedList);

            //probamos los removes
            for (int i = 0; i < 20; i++) {
                int value =  new Random().nextInt(50);
                if(circularLinkedList.contains(value)) {
                    System.out.println("remove("+value+") deleted !!!");
                    circularLinkedList.remove(value);
                }
            }
            //al final volvemos a mostrar la lista
            System.out.println(circularLinkedList);

            //probamos los removeFirst, removeLast
            System.out.println("_".repeat(50));
            for (int i = 0; i < 20; i++) {
                int value =  new Random().nextInt(50);
                if(circularLinkedList.contains(value)) {
                    System.out.println("removeFirst(): "+circularLinkedList.removeFirst());
                    System.out.println("removeLast(): "+circularLinkedList.removeLast());
                    System.out.println(circularLinkedList);
                }
            }
            //al final volvemos a mostrar la lista
            System.out.println(circularLinkedList);

        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

}
