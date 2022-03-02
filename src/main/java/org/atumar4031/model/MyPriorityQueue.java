package org.atumar4031.model;

import java.util.Iterator;

public class MyPriorityQueue implements Iterable<Customer> {
    private Node front;
    private int currentSize;

    @Override
    public Iterator<Customer> iterator() {
        return null;
    }

    private class Node{
        Customer customer;
        Node next;
    }


    public boolean isEmpty(){
        return currentSize == 0;
    }



    //To remove from the beginning of a list.

    public void removeFromQueue(){
        Customer customer = front.customer;
        front = front.next;
        currentSize--;
    }
    private boolean shouldComeInFront(Node a, Node b){
        var customer1productName = a.customer.getShoppingCart().getProduct().getProductName();
        var customer1productQuantity = a.customer.getShoppingCart().getQuantity();

        var customer2productName = b.customer.getShoppingCart().getProduct().getProductName();
        var customer2productQuantity = b.customer.getShoppingCart().getQuantity();

        return customer1productName.equals(customer2productName) &&
                customer1productQuantity > customer2productQuantity;
    }


    //To add data at the end of the list.
    public void addToQueue(Customer customer){
        Node newNode = new Node();
        newNode.customer = customer;

        Node previous = null;
        Node current = front;
        if (isEmpty()) {front = newNode;}
        else if (currentSize == 1){
            if (shouldComeInFront(front, newNode)){
                newNode.next = front;
                front = newNode;
            } else {
                newNode.next = null;
                front.next = newNode;
            }
        } else {
            for (int i = 0; i < currentSize; i++){
                if (shouldComeInFront(current, newNode)){
                    if (i == 0){
                        newNode.next = front;
                        front = newNode;
                    } else {
                        newNode.next = current;
                        previous.next = newNode;
                    }
                    break;
                } else if (current.next == null){
                    current.next = newNode;
                    newNode.next = null;
                }

                previous = current;
                current = current.next;
            }
        }
        currentSize++;
    }

    //To check for the top of the queue.
    public Customer peek(){
        if(isEmpty()) return null;
        return front.customer;
    }

    // To check for the size of the queue
    public int size(){
        return currentSize;
    }

    public boolean hasNext() {
        return currentSize > 0;
    }

}
