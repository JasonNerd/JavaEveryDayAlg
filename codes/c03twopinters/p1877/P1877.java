package codes.c03twopinters.p1877;
// https://books.halfrost.com/leetcode/ChapterFour/1800~1899/1877.Minimize-Maximum-Pair-Sum-in-Array/
// https://leetcode.cn/problems/minimize-maximum-pair-sum-in-array/description
/*
1877. 数组中最大数对和的最小值

一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
    nums 中每个元素 恰好 在 一个 数对中，且
    最大数对和 的值 最小 。
请你在最优数对划分的方案下，返回最小的 最大数对和 。

示例 1：

输入：nums = [3,5,2,3]
输出：7
解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。
最大数对和为 max(3+3, 5+2) = max(6, 7) = 7 。
示例 2：

输入：nums = [3,5,4,2,4,6]
输出：8
解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。
最大数对和为 max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8 。

提示：
n == nums.length
2 <= n <= 10^5
n 是 偶数 。
1 <= nums[i] <= 10^5
*/

import java.util.Arrays;

public class P1877 {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        for (int i=0; i<n/2; i++){
            if ((nums[i]+nums[n-1-i]) > res){
                res = nums[i]+nums[n-1-i];
            }

        }
        return res;
    }
    public static void main(String[] args) {
        int[] a = new int[]{
            69,91,47,74,75,94,22,100,43,50,
            82,47,40,51,90,27,98,85,47,14,
            55,82,52,9,65,90,86,45,52,52,
            95,40,85,3,46,77,16,59,32,22,
            41,87,89,78,59,78,34,26,71,9,
            82,68,80,74,100,6,10,53,84,80,
            7,87,3,82,26,26,14,37,26,58,
            96,73,41,2,79,43,56,74,30,71,
            6,100,72,93,83,40,28,79,24,58};
        System.out.println(new P1877().minPairSum(a));
    }
}
