package codes.array.p2166;

import java.util.ArrayList;

// https://books.halfrost.com/leetcode/ChapterFour/2100~2199/2166.Design-Bitset/
// https://leetcode.com/problems/design-bitset/
/*
位集 Bitset 是一种能以紧凑形式存储位的数据结构。

请你实现 Bitset 类。

Bitset(int size) 用 size 个位初始化 Bitset ，所有位都是 0 。
void fix(int idx) 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
void unfix(int idx) 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
void flip() 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
boolean all() 检查 Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
boolean one() 检查 Bitset 中 是否 至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
int count() 返回 Bitset 中值为 1 的位的 总数 。
String toString() 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。

提示：

1 <= size <= 10^5
0 <= idx <= size - 1
至多调用 fix、unfix、flip、all、one、count 和 toString 方法 总共 10^5 次
至少调用 all、one、count 或 toString 方法一次
至多调用 toString 方法 5 次
*/
class Bitset {
    // 使用一个镜像的数组实时记录翻转数组
    private int[] nums;
    private int[] mirror;
    private int cnt = 0;

    public Bitset(int size) {
        nums = new int[size];
        mirror = new int[size];
        for(int i=0; i<size; i++)
            mirror[i] = 1;
    }

    public void fix(int idx) {
        if (nums[idx] == 0){
            ++cnt;
            nums[idx] = 1;
            mirror[idx] = 0;
        }
    }
    
    public void unfix(int idx) {
        if (nums[idx] == 1){
            --cnt;
            nums[idx] = 0;
            mirror[idx] = 1;
        }
    }
    
    public void flip() {
        int[] tmp;
        tmp = mirror;
        mirror = nums;
        nums = tmp;
        cnt = nums.length - cnt;
    }
    
    public boolean all() {
        return cnt == nums.length;
    }
    
    public boolean one() {
        return cnt > 0;
    }
    
    public int count() {
        return cnt;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int bit: nums)
            builder.append(bit);
        return builder.toString();
    }
    public static void main(String[] args) {
        System.out.println(0^0);
    }
}

/*
fix/unfix/flip是使用频次最多的操作，必然要相互关联起来，使得单次flip的开销为常数。
用数组模拟二进制位的一系列操作。flip 操作并不需要每次去翻转，偶数次翻转等于没有翻转，
奇数次翻转记下标记，同时更新 1 的个数。这次懒操作在调用 fix 和 unfix 时，更新到原来数组中。
*/
/*
class Bitset {
    private int[] nums;
    private int cnt = 0;
    // 翻转次数的奇偶性, 0表示翻转了偶数次, 1表示翻转了奇数次, 这意味着
    // 如果 nums[i]=0, 而reversed=1, 则此时 nums[i]=1
    // 如果 nums[i]=1, 而reversed=1, 则此时 nums[i]=0
    // 如果 nums[i]=0, 而reversed=0, 则此时 nums[i]=0
    // 如果 nums[i]=1, 而reversed=0, 则此时 nums[i]=1
    private int reversed = 0;   
    

    public Bitset(int size) {
        nums = new int[size];
    }

    public void fix(int idx) {
        int fact = reversed ^ nums[idx];
        if (fact == 0){
            ++cnt;
            nums[idx] ^= 1;
        }
    }
    
    public void unfix(int idx) {
        int fact = reversed ^ nums[idx];
        if (fact == 1){
            --cnt;
            nums[idx] ^= 1;
        }
    }
    
    public void flip() {
        reversed ^= 1;
        cnt = nums.length - cnt;
    }
    
    public boolean all() {
        return cnt == nums.length;
    }
    
    public boolean one() {
        return cnt > 0;
    }
    
    public int count() {
        return cnt;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<nums.length; i++){
            builder.append(reversed ^ nums[i]);
        }
        return builder.toString();
    }
}
*/