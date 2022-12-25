package org.skyline.datastructure.linkedlist;

import java.util.NoSuchElementException;

/**
 * LinkedList class implemented by AlexiSkyline.
 *
 * This class represents a LinkedList data structure that stores elements of type T.
 * It has methods to add, remove, and get elements at specified indices, as well as
 * methods to check if the list is empty, get its size, and get the first and last elements.
 *
 * @author AlexiSkyline
 * @param <T> The type of elements stored in the LinkedList.
 */
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

     /** Checks if an index is within the valid range for a list or array of a specified size.
      *
      * @param index The index to check.
      * @param size The size of the list or array.
      * @return true if the index is within the valid range (i.e., if index >= 0 and index <= size), false otherwise.
      */
    private boolean isIndexInRange(int index, int size) {
        return (index >= 0 && index <= size);
    }

    /**
     * Determines if a LinkedList is empty.
     *
     * @return true if the LinkedList is empty (i.e., if the head node is null), false otherwise.
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Gets the size of a LinkedList.
     *
     * @return The size of the LinkedList, that is, the number of elements it contains.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the node at a specified index in a LinkedList.
     *
     * @param index The index of the node to get.
     * @return the node at the specified index, or null if the index is out of range or the LinkedList is empty.
     */
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

    /**
     * Adds a new node with the specified data at the head of a LinkedList.
     *
     * @param data The data to add to the new node.
     */
    public void addAtHead(T data) {
        this.head = new Node<>(data, this.head);
        if (this.tail == null) {
            this.tail = this.head;
        }
        this.size++;
    }

    /**
     * Adds a new node with the specified data at the tail of a LinkedList.
     *
     * @param data The data to add to the new node.
     */
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

    /**
     * Adds a new node with the specified data at a specified index in a LinkedList.
     *
     * @param index The index at which to add the new node.
     * @param data The data to add to the new node.
     * @return true if the node was added successfully, false if the index is out of range.
     */
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

    /**
     * Gets the element stored in a node at a specified index in a LinkedList.
     *
     * @param index The index of the node whose element to get.
     * @return The element stored in the node at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range or the LinkedList is empty.
     * @throws NullPointerException If the node at the specified index is not found.
     */
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

    /**
     * Gets the first element in a LinkedList.
     *
     * @return The first element in the LinkedList.
     * @throws NoSuchElementException If the LinkedList is empty.
     */
    public T getFirstElement() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return this.head.data;
    }

    /**
     * Gets the last element in a LinkedList.
     *
     * @return The last element in the LinkedList.
     * @throws NoSuchElementException If the LinkedList is empty.
     */
    public T getLastElement() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return this.tail.data;
    }

    /**
     * Removes the first element in a LinkedList.
     *
     * @return true if the element was removed successfully, false if the LinkedList is empty.
     */
    public boolean removeFirstElement() {
        if(this.isEmpty()) return false;
        this.head = this.head.next;
        this.size--;
        return true;
    }

    /**
     * Removes the last element in a LinkedList.
     *
     * @return true if the element was removed successfully, false if the LinkedList is empty.
     */
    public boolean removeLastElement() {
        if(this.isEmpty()) return false;
        Node<T> newTail = this.getNodeAtIndex(this.size - 2);
        newTail.next = null;
        this.tail = newTail;
        this.size--;
        return true;
    }

    /**
     * Removes an element from the middle of a LinkedList.
     *
     * @param index The index of the element to remove.
     * @return true if the element was removed successfully.
     */
    private boolean removeElementInTheMiddle(int index) {
        Node<T> nodeBeforeInsertionPoint = this.getNodeAtIndex(index - 1);
        nodeBeforeInsertionPoint.next = nodeBeforeInsertionPoint.next.next;
        this.size--;
        return true;
    }

    /**
     * Deletes an element at a specified index in a LinkedList.
     *
     * @param index The index of the element to delete.
     * @return true if the element was deleted successfully, false if the index is out of range.
     */
    public boolean deleteAtIndex(int index) {
        if (this.isEmpty() || !this.isIndexInRange(index, this.size - 1)) return false;
        if (index == 0) {
            return this.removeFirstElement();
        } else if (index == this.size - 1) {
            return this.removeLastElement();
        }
        return this.removeElementInTheMiddle(index);
    }
}