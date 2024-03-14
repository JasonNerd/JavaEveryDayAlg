package codes.c01array.p2170;
// 2170. Minimum Operations to Make the Array Alternating
/*
题目大意 #
给你一个下标从 0 开始的数组 nums ，该数组由 n 个正整数组成。

如果满足下述条件，则数组 nums 是一个 交替数组 ：

nums[i - 2] == nums[i] ，其中 2 <= i <= n - 1 。
nums[i - 1] != nums[i] ，其中 1 <= i <= n - 1 。
在一步 操作 中，你可以选择下标 i 并将 nums[i] 更改 为 任一 正整数。返回使数组变成交替数组的 最少操作数 。
*/

import java.util.Iterator;
import java.util.HashMap;

/*
输入：nums = [3,1,3,2,4,3]
输出：3
解释：
使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,1,3,1] 。
在这种情况下，操作数为 3 。
可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。
提示：
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
*/

public class P2170 {
    public int minimumOperations(int[] nums) {
        int[] even = getFirstSec(0, nums);
        int[] odd = getFirstSec(1, nums);
        if (even[0] != odd[0])
            return nums.length - (even[1]+odd[1]);
        else
            return nums.length - Math.max(even[1]+odd[3], even[3]+odd[1]);
    }

    public static int[] getFirstSec(int start, int[] nums){
        int n = nums.length;
        HashMap<Integer, Integer> counter = new HashMap<>();
        for(int i=start; i<n; i+=2)
            counter.put(nums[i], 1+counter.getOrDefault(nums[i], 0));
        Iterator<Integer> iterator = counter.keySet().iterator();
        int key1=0, val1=0, key2=0, val2=0;
        while (iterator.hasNext()) {
            int k = iterator.next();
            int v = counter.get(k);
            if (v > val1){
                key2 = key1;
                val2 = val1;
                key1 = k;
                val1 = v;
            }else if (v > val2){
                key2 = k;
                val2 = v;
            }
        }
        return new int[]{key1, val1, key2, val2};
    }

    public static void main(String[] args) {
        int[] a = new int[]{69,91,47,74,75,94,22,100,43,50,82,47,40,51,90,27,98,85,47,14,55,82,52,9,65,90,86,45,52,52,95,40,85,3,46,77,16,59,32,22,41,87,89,78,59,78,34,26,71,9,82,68,80,74,100,6,10,53,84,80,7,87,3,82,26,26,14,37,26,58,96,73,41,2,79,43,56,74,30,71,6,100,72,93,83,40,28,79,24};
        System.out.println(new P2170().minimumOperations(a));
    }
}

/*
 * 计算阵列中奇数位置的每个元素的频率。对处于偶数位置的图元执行相同操作。
 * 为了最大限度地减少运算数量，我们需要最大限度地增加原始数组中保留的元素数量。
 * 我们可以从奇数索引和偶数索引中选择哪些元素的可能组合，以使不变元素的数量最大化？
 */