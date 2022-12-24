package org.skyline.datastructure.linkedlist;

import java.util.NoSuchElementException;

public class LinkedList<T> {
    private static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private boolean isIndexInRange(int index, int size) {
        return (index >= 0 && index <= size);
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    private Node<T> getNodeAtIndex(int index) {
        if (this.isEmpty() || !this.isIndexInRange(index, this.size)) return null;
        int currentPosition = 0;
        Node<T> currentNode = this.head;
        while (currentPosition < index && currentNode != null) {
            currentNode = currentNode.next;
            currentPosition++;
        }
        return currentNode;
    }

    public void addAtHead(T data) {
        this.head = new Node<>(data, this.head);
        if (this.tail == null) {
            this.tail = this.head;
        }
        this.size++;
    }

    public void addAtTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.isEmpty()) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }
        this.size++;
    }

    public boolean addAtIndex(int index, T data) {
        if (!this.isIndexInRange(index, this.size)) return false;
        if (index == 0) {
            this.addAtHead(data);
            return true;
        }
        if (index == this.size) {
            this.addAtTail(data);
            return true;
        }
        Node<T> nodeBeforeInsertionPoint  = this.getNodeAtIndex(index-1);
        if (nodeBeforeInsertionPoint == null) {
            throw new IllegalArgumentException("Invalid index");
        }
        nodeBeforeInsertionPoint.next = new Node<>(data, nodeBeforeInsertionPoint.next);
        this.size++;
        return true;
    }

    public T getElementAtIndex(int index) {
        if (!this.isIndexInRange(index, this.size) || this.isEmpty()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<T> nodeFound = this.getNodeAtIndex(index);
        if (nodeFound == null) {
            throw new NullPointerException("Node not found");
        }
        return nodeFound.data;
    }

    public T getFirstElement() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return this.head.data;
    }

    public T getLastElement() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return this.tail.data;
    }

    public boolean removeFirstElement() {
        if(this.isEmpty()) return false;
        this.head = this.head.next;
        this.size--;
        return true;
    }

    public boolean removeLastElement() {
        if(this.isEmpty()) return false;
        Node<T> newTail = this.getNodeAtIndex(this.size - 2);
        newTail.next = null;
        this.tail = newTail;
        this.size--;
        return true;
    }

    private boolean removeElementInTheMiddle(int index) {
        Node<T> nodeBeforeInsertionPoint = this.getNodeAtIndex(index - 1);
        nodeBeforeInsertionPoint.next = nodeBeforeInsertionPoint.next.next;
        this.size--;
        return true;
    }

    public boolean deleteAtIndex(int index) {
        if (this.isEmpty() || !this.isIndexInRange(index, this.size - 1)) return false;
        if (index == 0) {
            return this.removeFirstElement();
        } else if (index == this.size - 1) {
            return this.removeLastElement();
        } else {
            return this.removeElementInTheMiddle(index);
        }
    }
}