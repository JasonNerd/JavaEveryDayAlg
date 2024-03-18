package codes.c10bfs.p1306;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        q.offer(start);
        while (!q.isEmpty()) {
            int m = q.poll();
            int ml = m - arr[m], mr = m + arr[m];
            if (ok(ml, vis)){
                if (arr[ml] == 0) return true;
                q.offer(ml);
                vis[ml] = true;
            }
            if (ok(mr, vis)){
                if (arr[mr] == 0) return true;
                q.offer(mr);
                vis[mr] = true;
            }
        }
        return false;
    }

    public boolean ok(int x, boolean[] vis){
        if (x>=0 && x<vis.length && !vis[x]){
            return true;
        }else return false;
    }

    public static void main(String[] args) {
        int[] arr = {0};
        int start = 0;
        System.out.println(new Solution().canReach(arr, start));
    }
}


/*
这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。
当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
请你判断自己是否能够跳到对应元素值为 0 的任一下标处。
注意，不管是什么情况下，你都无法跳到数组之外。

示例 1：
输入：arr = [4,2,3,0,3,1,2], start = 5
输出：true
解释：
到达值为 0 的下标 3 有以下可能方案： 
下标 5 -> 下标 4 -> 下标 1 -> 下标 3 
下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3 

示例 2：
输入：arr = [4,2,3,0,3,1,2], start = 0
输出：true 
解释：
到达值为 0 的下标 3 有以下可能方案： 
下标 0 -> 下标 4 -> 下标 1 -> 下标 3
示例 3：

输入：arr = [3,0,2,1,2], start = 2
输出：false
解释：无法到达值为 0 的下标 1 处。 
 

提示：
1 <= arr.length <= 5 * 10^4
0 <= arr[i] < arr.length
0 <= start < arr.length
 */