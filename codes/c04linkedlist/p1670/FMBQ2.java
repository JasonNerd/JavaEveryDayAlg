package codes.c04linkedlist.p1670;

import java.util.ArrayDeque;
import java.util.Deque;

public class FMBQ2 {
    Deque<Integer> front;
    Deque<Integer> tail;

    FMBQ2(){
        front = new ArrayDeque<>();
        tail = new ArrayDeque<>();
    }

    
    void ft2tf(){
        tail.addFirst(front.pollLast());    // 将 front 的尾节点放到 tail 的头节点
    }
    void tf2ft(){
        front.addLast(tail.pollFirst());    // 将 tail 的头节点放到 front 的尾节点
    }
    void adjust(){
        // 调整 front 和 tail 的长度, 使得 len(front) <= len(tail) <= len(front)+1
        int m = front.size(), n = tail.size();
        if (m == n+1)
            ft2tf();
        if (m == n-2)
            tf2ft();
    }

    void pushFront(int val){
        front.addFirst(val);
        adjust();       // pushFront 使得 front 增加, front=tail+1;
    }
    void pushMiddle(int val){
        front.addLast(val);
        adjust();       // pushMiddle 使得 front 增加, front=tail+1;
    }
    void pushBack(int val){
        tail.addLast(val);
        adjust();       // pushBack 使得 back 增加, front=tail-2;
    }

    boolean isEmpty(){
        return tail.size()==0;
    }
    int popMiddle(){
        if(isEmpty())return -1;
        int m = front.size(), n = tail.size();
        if (m == n)
            return front.pollLast();
        else
            return tail.pollFirst();
    }
    int popBack(){
        if(isEmpty())return -1;
        int val = tail.pollLast();
        adjust();       // popBack 使得 tail 减小, front=tail+1;
        return val;
    }
    int popFront(){
        if(isEmpty())return -1;
        Integer val = front.pollFirst();
        if (val == null)    // front 为空
            val = tail.pollFirst();
        adjust();
        return val;     // popFront 使得 front 减小, front=tail-2;
    }
}
/*
作者：力扣官方题解
链接：https://leetcode.cn/problems/design-front-middle-back-queue/solutions/2539737/she-ji-qian-zhong-hou-dui-lie-by-leetcod-b3p0/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

由于左右两个部分都需要支持头部、尾部的插入和删除，因此使用双端队列这一基础数据结构。我们用 left 表示左边，用 right 表示右边。在整个过程中，保持 left 和 right 的长度相同，或者 left 的长度恰好比 right 大 1，即 right.length≤left.length≤right.length+1\textit{right.length} \le \textit{left.length} \le \textit{right.length} + 1right.length≤left.length≤right.length+1（当然也可以反过来，让 left 的长度与 right 的长度相等或者 right 的长度比 left 恰好大 1），这样做是为了能够方便的在中部进行插入和删除操作。

在以下六个基本操作中，你需要设置一些调整让两个双端队列满足长度约束：

pushFront, 若插入后 left 的长度比 right 的长度大 2，
    需要将 left 的尾部元素移动到 right 的头部
pushMiddle，在 left 的尾部插入，若插入前 left 的长度比 right 的长度大 1，
    需要先把 left 的尾部元素移动到 right 的头部，然后再插入新元素
pushBack，在 right 尾部插入，若插入后 right 的长度比 left 的长度大 1，
    需要将 right 的头部元素移动到 left 的尾部
popFront，若 left 为空则直接返回 −1（因为当队列中有元素时，left 总是不为空，以下同理），
    否则删除 left 的头部元素，若删除后 left 的长度比 right 的长度小 1，需要将 right 的头部元素移动到 left 的尾部
popMiddle，若 left 为空则直接返回 −1，否则删除 left 的尾部元素，若删除后 left 的长度比 right 的长度小 1，
    需要将 right 的头部元素移动到 left 的尾部
popBack，若 left 为空则直接返回 −1，否则再看 right 的长度:
    若 right 为空（此时队列中仅存在一个元素），删除 left 的尾部元素
    若 right 不为空，删除 right 的尾部元素，若删除后 left 的长度比 right 的长度大 2，需要将 left 的尾部元素移动到 right 的头部。

*/

/* 
class FrontMiddleBackQueue {
    Deque<Integer> left;
    Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<Integer>();
        right = new ArrayDeque<Integer>();
    }

    public void pushFront(int val) {
        left.offerFirst(val);
        if (left.size() == right.size() + 2) {
            right.offerFirst(left.pollLast());
        }
    }

    public void pushMiddle(int val) {
        if (left.size() == right.size() + 1) {
            right.offerFirst(left.pollLast());
        }
        left.offerLast(val);
    }

    public void pushBack(int val) {
        right.offerLast(val);
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
    }

    public int popFront() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = left.pollFirst();
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
        return val;
    }

    public int popMiddle() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = left.pollLast();
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
        return val;
    }

    public int popBack() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = 0;
        if (right.isEmpty()) {
            val = left.pollLast();
        } else {
            val = right.pollLast();
            if (left.size() == right.size() + 2) {
                right.offerFirst(left.pollLast());
            }
        }
        return val;
    }
}
*/