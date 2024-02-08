package codes.linkedlist.p1670;

import java.util.LinkedList;

public class FrontMiddleBackQueue {
    LinkedList<Integer> head;
    public FrontMiddleBackQueue() {
        head = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        head.addFirst(val);
    }
    
    public void pushMiddle(int val) {
        head.add(head.size()/2, val);
    }
    
    public void pushBack(int val) {
        head.addLast(val);
    }
    
    public int popFront() {
        Integer val = head.pollFirst();
        return val == null? -1: val;
    }
    
    public int popMiddle() {
        if (head.isEmpty())
            return -1;
        return head.remove((head.size()-1)/2);
    }
    
    public int popBack() {
        Integer val = head.pollLast();
        return val == null? -1: val;
    }
}
