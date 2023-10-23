import java.util.Scanner;
class doublyLinkedList<E> {
    public static class Node <E> {
        public E element;
        public Node<E> prev;
        public Node<E> next;
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
        public E getElement() {return element;}
        public Node<E> getPrev() {return prev;}
        public Node<E> getNext() {return next;}
        public void setPrev(Node<E> p){
            prev = p;
        }
        public void setNext(Node<E> n){
            next = n;
        }

    }
    public Node<E> header;
    public Node<E> trailer;
    public int size = 0;
    public doublyLinkedList(){
        header = new Node<> (null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public E first(){
        if(isEmpty())
            return null;
        return header.getNext().getElement();
    }
    public E last(){
        if(isEmpty())
            return null;
        return trailer.getPrev().getElement();
    }
    public void addFirst(E e){
        addBetween(e, header , header.getNext());
    }
    public void addLast(E e){
        addBetween(e, trailer.getPrev(), trailer);
    }
    public E removeFirst(){
        if(isEmpty())
            return null;
        return remove(header.getNext());
    }
    public E removeLast(){
        if(isEmpty())
            return null;
        return remove(trailer.getNext());
    }

    public void addBetween(E e, Node<E> predecessor, Node <E> successor){
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;

    }
    public E remove(Node<E> node){
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    public void sort() {
        if (size > 1) {
            Node<E> current = header.getNext();
            Node<E> index;
            E temp;

            while (current != null) {
                index = current.getNext();
                while (index != null) {
                    if (current.getElement() != null && index.getElement() != null) {
                        if (((Comparable<E>) current.getElement()).compareTo(index.getElement()) > 0) {
                            temp = current.getElement();
                            current.element = index.getElement();
                            index.element = temp;
                        }
                    }
                    index = index.getNext();
                }
                current = current.getNext();
            }
        }
    }


    public void add(int i, E e) {
        if (i == size) {
            addLast(e);
        } else {
            Node<E> current = header.getNext();
            for (int j = 0; j < i; j++) {
                current = current.getNext();
            }
            addBetween(e, current.getPrev(), current);
        }
    }

    public E remove(int i) {
        Node<E> current = header.getNext();
        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }
        return remove(current);
    }

    public E get(int i) {
        Node<E> current = header.getNext();
        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }
        return current.getElement();
    }


}


public class Main {
    public static void main(String[] args) {
        doublyLinkedList<Integer> myList = new doublyLinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int numInputs = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numInputs; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];

            if (command.equals("addFirst")) {
                int value = Integer.parseInt(parts[1]);
                myList.addFirst(value);
            } else if (command.equals("addLast")) {
                int value = Integer.parseInt(parts[1]);
                myList.addLast(value);
            } else if (command.equals("add")) {
                int index = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                myList.add(index, value);
            } else if (command.equals("size")) {
                System.out.println(myList.size());
            } else if (command.equals("isEmpty")) {
                System.out.println(myList.isEmpty());
            } else if (command.equals("sort")) {
                myList.sort();
            } else if (command.equals("remove")) {
                int index = Integer.parseInt(parts[1]);
                myList.remove(index);
            } else if (command.equals("get")) {
                int index = Integer.parseInt(parts[1]);
                System.out.println(myList.get(index));
            } else if (command.equals("last")) {
                System.out.println(myList.last());
            } else if (command.equals("first")) {
                System.out.println(myList.first());
            }
        }

        scanner.close();

    }

}
