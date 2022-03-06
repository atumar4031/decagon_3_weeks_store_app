package org.atumar4031.model;
import java.util.LinkedList;

public class MyPriorityQueue{
    private LinkedList<Customer> myQueue;
    private int index = 0;

    public MyPriorityQueue(){
        myQueue = new LinkedList<>();
    }

    public void addToQueue(Customer customer){
        boolean searchFlag = false;
        if (myQueue.size() == 0){
            myQueue.add(index,customer);
        }else{
            int customerInListIndex  = 0;
            Customer lastCustomerInQueue = new Customer();
            for (Customer customerInQueue: myQueue){
                if(customerInQueue.getShoppingCart().getProduct().getProductName()
                    .equalsIgnoreCase(customer.getShoppingCart().getProduct().getProductName()) &&
                customerInQueue.getShoppingCart().getQuantity() >= customer.getShoppingCart().getQuantity()
                ){
                    lastCustomerInQueue = customerInQueue;
                    customerInListIndex = myQueue.indexOf(customerInQueue);
                    myQueue.remove(customerInQueue);
                    searchFlag = true;
                    break;
                }
            }

            if (searchFlag){
               myQueue.add(customerInListIndex++,customer);
               myQueue.add(customerInListIndex ,lastCustomerInQueue);
            }else {
                myQueue.add(customer);
            }
        }
    }
    public int size(){
        return myQueue.size();
    }

    public Customer peekMe(){
        return myQueue.peekFirst();
    }

    public boolean isEmpty(){
        return myQueue.isEmpty();
    }

    public Customer poll(){
        return myQueue.poll();
    }

    @Override
    public String toString() {
        return "MyPriorityQueue{" +
                "myQueue=" + myQueue +
                '}';
    }
   
}
