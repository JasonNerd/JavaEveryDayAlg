package codes.stack.p1673;

import java.util.ArrayList;
import java.util.List;

public class AscendStack {
    private List<Integer> s;
    private int size;   // 栈内元素个数
    private int k=-1;   // 全部数字入栈后该栈需要保持的最小大小
    private int n;      // 还剩多少数字未入栈

    public AscendStack(){
        s = new ArrayList<>();
        size = 0;
    }

    public AscendStack(int k, int n){
        this();
        this.k = k;
        this.n = n;
    }

    public void push(int e){
        while (true){
            int t = top();
            if (e >= t){
                s.add(e);
                break;
            }
            if (k<0 || (k>0 && (n+size)>k))
                pop();
            else{
                s.add(e);
                break;
            }
        }
        ++size;
        --n;
    }
    public int pop(){
        if (0 == size) return -1;
        return s.remove(--size);
    }
    public int top(){
        if (0 == size) return -1;
        return s.get(size-1);
    }
    public int size(){
        return size;
    }
    public void print(){
        System.out.println("from bottom to top(left to right):");
        System.out.println(s);
    }
}
