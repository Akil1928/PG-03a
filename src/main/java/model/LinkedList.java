package model;

public class LinkedList <T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            throw new ListException("Linked list is empty");
        }
        Node<T> aux = head;
        int count = 0;
        while (aux != null) {
            count++;
            aux = aux.next;
        }
        return count;
    }

    @Override
    public void clear() {
        head = tail = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null; //si head es nulo entonces la lista esta vacia
    }

    @Override
    public void add(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
            tail = node;
        } else { //significa que head apunta a un nodo existente
            Node<T> aux = head;
            //me muevo por la lista hasta alcanzar el último elemento
            while (aux.next != null) {
                aux = aux.next;//lo mueve al siguiente nodo
            }
            //cuando se sale del while aux.next es nulo
            aux.next = node;
            tail = node; //dejamos el apuntador del ultimo nodo de la lista
        }

    }

    @Override
    public void addFirst(T element) {
        Node<T> node = new Node<>(element);
        tail = node;
    }

    @Override
    public void addLast(T element) {
        add(element);//el metodo add por default agrega al final
    }

    @Override
    public void addInSortedList(T element) {

    }

    @Override
    public void remove(T element) throws ListException {

    }

    @Override
    public T removeFirst() throws ListException {
        return null;
    }

    @Override
    public T removeLast() throws ListException {
        return null;
    }

    @Override
    public boolean contains(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Linked list is empty");
        }

        Node<T> aux = head;
        while (aux != null) {
            if (equals(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    @Override
    public void sort() throws ListException {

    }

    @Override
    public int indexOf(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Linked list is empty");
        }
        Node<T> aux = head;
        int index = 1; //el indice d ela lista enlazada inicia en 1
        while (aux != null) {
            index++;
            aux = aux.next;
        }
        return -1; //indica que no se encontró el elemento
    }

    @Override
    public T getFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("Linked list is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() throws ListException {
        if(isEmpty()){
            throw new ListException("Linked list is empty");
        }
        return tail.data;
    }

    @Override
    public T getPrev(T element) throws ListException {
        return null;
    }

    @Override
    public T getNext(T element) throws ListException {
        return null;
    }

    @Override
    public T get(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("Linked list is empty");
        }
        Node<T> aux = head;
        int count = 1;
        while (aux != null) {
            if (count++ == index) {
                return aux.data;
            }
            aux = aux.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HEAD → ");
        Node<T> aux = head;
        while(aux != null){
            sb.append("[").append(aux.data).append("]");
            if(aux.next != null) sb.append(" → ");
            aux = aux.next;


        }
        sb.append(" → NULL"); //para que apunte nulo en ultimo nodo
        return sb.toString();
    }

    private boolean equals(T a, T b) {
        return a == null ? b == null : a.equals(b);
    }

    public Node<T> getNode(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("Linked list is empty");
        }

        if (index < 0) {
            throw new ListException("Invalid index");
        }

        Node<T> aux = head;
        int current = 0;

        while (aux != null) {
            if (current == index) {
                return aux;
            }
            aux = aux.next;
            current++;
        }

        throw new ListException("Index out of bounds: " + index);
    }
}