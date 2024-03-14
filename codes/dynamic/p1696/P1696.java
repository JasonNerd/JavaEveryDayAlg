package codes.dynamic.p1696;

import java.util.ArrayDeque;
import java.util.Deque;
/*
超时原因。每次都要在 [max(0, i - k ), i) 区间内扫描找到最大值，下一轮的区间是 [max(0, i - k + 1), i + 1)
前后这两轮扫描的区间存在大量重合部分 [max(0, i - k + 1), i)，正是这部分反反复复的扫描导致算法低效。
如何高效的在一个区间内找到最大值是本题的关键。利用单调队列可以完成此题。
单调队列里面存一个区间内最大值的下标。这里单调队列有 2 个性质。
性质一，队列的队首永远都是最大值，队列从大到小降序排列。如果来了一个比队首更大的值的下标，需要将单调队列清空，
只存这个新的最大值的下标。性质二，队列的长度为 k。从队尾插入新值，并把队头的最大值“挤”出队首。
 */
public class P1696 {
    public int maxResult(int[] nums, int k) {
        // dp[i] = nums[i] + max(dp[j])(i-1>=j>=i-k)
        // 单增队列(队首最大), 实际存储下标(保证窗口大小不大于k)
        Deque<Integer> window = new ArrayDeque<>();
        window.offer(0);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i=1; i<nums.length; i++){
            dp[i] = nums[i] + dp[window.peek()];
            while (window.size()>0 && (i-window.peek())>=k)
                window.pollFirst();
            while (window.size()>0 && (dp[i] >= dp[window.getLast()])) {
                window.pollLast();
            }
            window.offerLast(i);
        }
        return dp[dp.length-1];
    }
    public static void main(String[] args) {
        int[] nums = {1,-1,-2,4,-7,3};
        int k = 2;
        System.out.println(new P1696().maxResult(nums, k));
    }
}
/*
给你一个下标从 0 开始的整数数组 nums 和一个整数 k .
一开始你在下标 0 处. 每一步, 你最多可以往前跳 k 步, 但你不能跳出数组的边界.
也就是说, 你可以从下标 i 跳到 [i + 1, min(n - 1, i + k)] 包含 两个端点的任意位置.
你的目标是到达数组最后一个位置(下标为 n - 1), 你的得分为经过的所有数字之和.
请你返回你能得到的 最大得分.

示例 1:
输入：nums = [1,-1,-2,4,-7,3], k = 2
输出：7
解释：你可以选择子序列 [1,-1,4,3], 和为 7.

示例 2:
输入：nums = [10,-5,-2,4,0,3], k = 3
输出：17
解释：你可以选择子序列 [10,4,3], 和为 17.

示例 3:
输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
输出：0

提示:
1 <= nums.length, k <= 10^5
-10^4 <= nums[i] <= 10^4
*/

/*
如下方法
public int maxResult(int[] nums, int k) {
    int ni=0, res=nums[0], mxv, mxi;
    while (true) {
        mxi = ni+1;
        mxv = nums[ni+1];
        for(int i=mxi; i<nums.length && i<=ni+k; i++)
            if (mxv <= nums[i]){
                mxv = nums[i];
                mxi = i;
            }
        ni = mxi;
        res += nums[ni];
        if (ni == nums.length-1)
            break;
    }
    return res;
}
*/

/*
public int maxResult(int[] nums, int k) {
    // dp[i] = nums[i] + max(dp[j])(i-1>=j>=i-k)
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    for (int i=1; i<nums.length; i++){
        int j = i-1, dpm=dp[j];
        while (true) {
            if (dpm <= dp[j])
                dpm = dp[j];
            j--;
            if(j<0 || j<i-k)
                break;
        }
        dp[i] = dpm+nums[i];
    }
    return dp[nums.length-1];
}
*/