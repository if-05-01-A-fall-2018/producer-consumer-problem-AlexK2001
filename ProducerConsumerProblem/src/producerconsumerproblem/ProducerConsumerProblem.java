/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumerproblem;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ProducerConsumerProblem {

    static int amountOfItems = 10;
    static int[] items = new int[amountOfItems];
    private static int count = 0;
    static Thread consumer;
    static Thread producer;
    private static final Long max = Long.MAX_VALUE;
    
    public static void main(String[] args) {
        
        
         producer = new Thread(new Runnable(){           
            
            @Override
            public void run() {
                int item;
                while (true) {
                    item = produce();
                    if (count == amountOfItems) {
                        try {
                            
                            System.out.println("Producer stopped");
                            Thread.sleep(max);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ProducerConsumerProblem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    insert_item(item);
                    count++;
                    if (count == 1) {
                        System.out.println("Consumer started");
                        consumer.start();
                    }
                    
                }   
                
            }          
            private int produce() {
                System.out.println("Producer started");
                return ThreadLocalRandom.current().nextInt(1, 11);
            }
        });
        
        consumer = new Thread(new Runnable(){
            @Override
            public void run() {
                int item = 0;
                
                    consumeItem(item);
                
  
            }
            
        });
        
        producer.start();
        
    }
    
    public static void insert_item(int item){
        items[count] = item; 
    }
    
    public static void consumeItem(int item){
        while (true) {
            if (count == 0) {
                try {
                    System.out.println("Consumer stopped");
                    Thread.sleep(max);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerProblem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            item  = remove();
            count--;
            if (count == -1) {
                System.out.println("start Producer");
                producer.start();
            }
        }
    } 
    
    public static int remove(){
        int item = items[count - 1];
        items[count-1] = 0;
        return item;
    }
    
    
}
