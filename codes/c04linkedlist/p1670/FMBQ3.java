package codes.c04linkedlist.p1670;

public class FMBQ3 {
    // 创建一个单双向表, 有头节点, 尾指针, 中间节点指针
}
/*
class FrontMiddleBackQueue {
    Node head, tail, mid;
    int size;

    public FrontMiddleBackQueue() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        size = 0;
    }
    
    public void pushFront(int val) {
        Node node = new Node(val);
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
        ++size;
        if (size == 1) {
            mid = node;
        }
        else if ((size & 1) == 1) {
            mid = mid.pre;
        }
    }
    
    public void pushMiddle(int val) {
        if (size == 0) {
            mid = new Node(val);
            mid.next = tail;
            mid.pre = head;
            tail.pre = mid;
            head.next = mid;
            ++size;
            return;
        }
        Node node = new Node(val);
        node.next = mid;
        node.pre = mid.pre;
        mid.pre.next = node;
        mid.pre = node;
        ++size;
        
        if ((size & 1) == 1) {
            mid = mid.pre;
        }
    }
    
    public void pushBack(int val) {
        Node node = new Node(val);
        node.next = tail;
        node.pre = tail.pre;
        tail.pre.next = node;
        tail.pre = node;
        ++size;

        if (size == 1) {
            mid = node;
        } else if ((size & 1) == 0) {
            mid = mid.next;
        }

    }
    
    public int popFront() {
        if (isEmpty()) {
            return -1;
        }
        Node node = head.next;
        node.next.pre = head;
        head.next = node.next;
        --size;
        if ((size & 1) == 0) {
            mid = mid.next;
        }
        node.next = node.pre = null;
        return node.val;
    }
    
    public int popMiddle() {
        if (isEmpty()) {
            return -1;
        }
        Node node = new Node();
        if ((size & 1) == 0) {
            node = mid.pre;
        } else {
            node = mid;
            mid = mid.next;
        }
        mid.pre = node.pre;
        node.pre.next = mid;
        --size;
        node.next = node.pre = null;
        return node.val;
    }
    
    public int popBack() {
        if (isEmpty()) {
            return -1;
        }
        Node node = tail.pre;
        tail.pre = node.pre;
        node.pre.next = tail;
        --size;
        if ((size & 1) == 1) {
            mid = mid.pre;
        }
        node.next = node.pre = null;
        return node.val;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }
}

class Node{
    Node pre, next;
    int val;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }
}
 */