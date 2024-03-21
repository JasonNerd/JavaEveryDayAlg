package codes.c11binarysearch.p1658;
// https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/description/
// https://books.halfrost.com/leetcode/ChapterFour/1600~1699/1658.Minimum-Operations-to-Reduce-X-to-Zero/

public class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0, n = nums.length;
        for (int i=0; i<n; i++)
            total += nums[i];
        if (total == x) return 0;
        if (total < x) return -1;
        int l=0, r=0, res=-1, target=total-x, sum=0;
        while (r < n){
            if (sum < target)
                sum += nums[r++];
            while (sum >= target) {
                if (sum == target){
                    res = res > (r-l) ? res : (r-l);
                }
                sum -= nums[l++];
            }
        }
        return res>0?n - res:res;
    }
    
    public static void main(String[] args) {
        int[] nums = {5,2,3,1,1};
        int x = 5;
        System.out.println(new Solution().minOperations(nums, x));
    }
}
/*
1658. 将 x 减到 0 的最小操作数
给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。
请注意，需要 修改 数组以供接下来的操作使用。如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。

示例 1：
输入：nums = [1,1,4,2,3], x = 5
输出：2
解释：最佳解决方案是移除后两个元素，将 x 减到 0 。

示例 2：
输入：nums = [5,6,7,8,9], x = 4
输出：-1

示例 3：
输入：nums = [3,2,20,1,1,3], x = 10
输出：5
解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 

提示：
1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
*/
/*
先把问题转化一下, 问题是要从数组两头去掉尽可能少的数字, 使得被去掉的数字之和恰好为 x.
等价于考虑这样一个窗口, 窗口内的数字之和为 sum(nums)-x, 同时窗口最大.

如何维护窗口呢, 宽度从0开始, 一直扩大直到超出范围, 随后缩小直到在范围内(根据条件判断是要缩小还是扩张)
*/