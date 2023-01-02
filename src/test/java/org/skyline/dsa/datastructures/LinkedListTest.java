package org.skyline.dsa.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    private LinkedList<Integer> linkedListInteger;
    private LinkedList<String> linkedListString;

    @BeforeEach
    void beforeOne() {
        this.linkedListInteger = new LinkedList<>();
        this.linkedListString = new LinkedList<>();
    }

    @BeforeEach
    void setUpTwo() {
        this.linkedListInteger = new LinkedList<>();
        this.linkedListString = new LinkedList<>();

        this.linkedListInteger.addAtTail(1);
        this.linkedListInteger.addAtTail(99);
        this.linkedListInteger.addAtTail(250);
        this.linkedListInteger.addAtTail(50);

        this.linkedListString.addAtTail("Java");
        this.linkedListString.addAtTail("Go");
        this.linkedListString.addAtTail("TypeScript");
        this.linkedListString.addAtTail("C++");
    }

    @Test
    void isEmpty() {
        beforeOne();
        assertTrue(this.linkedListInteger.isEmpty());
        assertTrue(this.linkedListString.isEmpty());

        this.linkedListInteger.addAtHead(20);
        this.linkedListInteger.addAtHead(10);
        assertFalse(this.linkedListInteger.isEmpty());

        this.linkedListString.addAtHead("hi");
        this.linkedListString.addAtHead("LOL");
        assertFalse(this.linkedListInteger.isEmpty());
    }

    @Test
    void getSize() {
        this.beforeOne();
        assertEquals(this.linkedListInteger.getSize(), 0);
        assertEquals(this.linkedListString.getSize(), 0);

        this.linkedListInteger.addAtHead(10);
        this.linkedListInteger.addAtHead(20);
        this.linkedListInteger.addAtHead(30);
        this.linkedListInteger.addAtHead(40);
        assertEquals(this.linkedListInteger.getSize(), 4);

        this.linkedListInteger.deleteAtIndex(1);
        this.linkedListInteger.deleteAtIndex(2);
        assertEquals(this.linkedListInteger.getSize(), 2);

        this.linkedListString.addAtHead("Car");
        this.linkedListString.addAtHead("Xbox");
        this.linkedListString.addAtHead("PC");
        assertEquals(this.linkedListString.getSize(), 3);

        this.linkedListString.deleteAtIndex(0);
        this.linkedListString.deleteAtIndex(1);
        assertEquals(this.linkedListString.getSize(), 1);
    }

    @Test
    void addAtHead() {
        this.beforeOne();
        this.linkedListInteger.addAtHead(10);
        assertEquals(this.linkedListInteger.getFirstElement(), 10);

        this.linkedListInteger.addAtHead(20);
        this.linkedListInteger.addAtHead(30);
        this.linkedListInteger.addAtHead(40);
        assertEquals(this.linkedListInteger.getFirstElement(), 40);

        this.linkedListString.addAtHead("Car");
        this.linkedListString.addAtHead("Xbox");
        assertEquals(this.linkedListString.getFirstElement(), "Xbox");
    }

    @Test
    void addAtTail() {
        this.beforeOne();
        this.linkedListInteger.addAtTail(100);
        assertEquals(this.linkedListInteger.getLastElement(), 100);

        this.linkedListInteger.addAtTail(20);
        this.linkedListInteger.addAtTail(40);
        assertEquals(this.linkedListInteger.getLastElement(), 40);

        this.linkedListString.addAtTail("Car");
        this.linkedListString.addAtTail("Xbox");
        assertEquals(this.linkedListString.getLastElement(), "Xbox");
    }

    @Test
    void addAtIndex() {
        this.beforeOne();
        this.linkedListInteger.addAtTail(100);
        this.linkedListInteger.addAtTail(300);
        this.linkedListInteger.addAtTail(500);

        this.linkedListInteger.addAtIndex(1, 200);
        this.linkedListInteger.addAtIndex(3, 400);

        assertEquals(this.linkedListInteger.getElementAtIndex(1), 200);
        assertEquals(this.linkedListInteger.getElementAtIndex(3), 400);

        this.linkedListString.addAtTail("Car");
        this.linkedListString.addAtTail("Xbox");

        this.linkedListString.addAtIndex(0, "CPU");
        this.linkedListString.addAtIndex(3, "CAR");
        assertEquals(this.linkedListString.getElementAtIndex(0), "CPU");
        assertEquals(this.linkedListString.getElementAtIndex(3), "CAR");
    }

    @Test
    void getElementAtIndex() {
        this.setUpTwo();
        assertEquals(this.linkedListInteger.getElementAtIndex(2), 250);
        assertEquals(this.linkedListInteger.getElementAtIndex(0), 1);
        assertEquals(this.linkedListInteger.getElementAtIndex(3), 50);
        assertEquals(this.linkedListInteger.getElementAtIndex(1), 99);

        assertEquals(this.linkedListString.getElementAtIndex(3), "C++");
        assertEquals(this.linkedListString.getElementAtIndex(2), "TypeScript");
        assertEquals(this.linkedListString.getElementAtIndex(0), "Java");
        assertEquals(this.linkedListString.getElementAtIndex(1), "Go");
    }

    @Test
    void getFirstElement() {
        this.setUpTwo();

        assertEquals(this.linkedListInteger.getFirstElement(), 1);
        assertNotEquals(50, (int) this.linkedListInteger.getFirstElement());

        assertEquals(this.linkedListString.getFirstElement(), "Java");
        assertNotEquals("C++", this.linkedListString.getFirstElement());
    }

    @Test
    void getLastElement() {
        this.setUpTwo();

        assertEquals(this.linkedListInteger.getLastElement(), 50);
        assertNotEquals(1, (int) this.linkedListInteger.getLastElement());

        assertEquals(this.linkedListString.getLastElement(), "C++");
        assertNotEquals("Java", this.linkedListString.getLastElement());
    }

    @Test
    void removeFirstElement() {
        this.setUpTwo();

        int firstElementAbove = this.linkedListInteger.getFirstElement();
        this.linkedListInteger.removeFirstElement();
        assertNotEquals((int) this.linkedListInteger.getFirstElement(), firstElementAbove);
        assertEquals(this.linkedListInteger.getFirstElement(), 99);
        assertEquals(this.linkedListInteger.getSize(), 3);

        this.linkedListInteger.removeFirstElement();
        this.linkedListInteger.removeFirstElement();
        assertEquals(this.linkedListInteger.getFirstElement(), 50);
        assertEquals(this.linkedListInteger.getSize(), 1);

        this.linkedListString.removeFirstElement();
        assertEquals(this.linkedListString.getFirstElement(), "Go");

        this.linkedListString.removeFirstElement();
        this.linkedListString.removeFirstElement();
        assertEquals(this.linkedListString.getFirstElement(), "C++");

        this.linkedListString.removeFirstElement();
        assertFalse(this.linkedListString.removeFirstElement());
        assertEquals(this.linkedListString.getSize(), 0);
        assertTrue(this.linkedListString.isEmpty());
    }

    @Test
    void removeLastElement() {
        this.setUpTwo();

        this.linkedListInteger.removeLastElement();
        assertEquals(this.linkedListInteger.getLastElement(), 250);

        this.linkedListInteger.removeLastElement();
        this.linkedListInteger.removeLastElement();
        assertEquals(this.linkedListInteger.getSize(), 1);

        this.linkedListString.removeLastElement();
        assertEquals(this.linkedListString.getLastElement(), "TypeScript");
        assertEquals(this.linkedListString.getSize(), 3);
    }

    @Test
    void deleteAtIndex() {
        this.setUpTwo();

        this.linkedListInteger.deleteAtIndex(0);
        assertEquals(this.linkedListInteger.getFirstElement(), 99);

        this.linkedListInteger.deleteAtIndex(2);
        assertEquals(this.linkedListInteger.getLastElement(), 250);
    }
}