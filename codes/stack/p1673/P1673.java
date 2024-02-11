package codes.stack.p1673;
// https://blog.csdn.net/zy_dreamer/article/details/131036101

import java.util.Arrays;

public class P1673 {
    public static void main(String[] args) {
        int a[] = {2,4,3,3,5,4,9,6};
        int[] res = new Solution().mostCompetitive(a, 4);
        System.out.println(Arrays.toString(res));
    }
}

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length, top = 0;    
        int[] res = new int[n+2];   // 工作栈(单调栈)
        for(int i=0; i<n; i++){
            while(true){
                if((top+n-i) <= k){    // 需要保证可用元素不少于k个
                    res[top++] = nums[i];
                    break;
                }
                if ((top == 0) || (nums[i] >= res[top-1])){
                    res[top++] = nums[i];
                    break;
                }else{ 
                    --top;
                }
            }
        }
        return Arrays.copyOfRange(res, 0, k);
    }
}

/*
给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，
那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。

例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。

示例：
输入：nums = [2,4,3,3,5,4,9,6], k = 4
输出：[2,3,3,4]
输入：nums = [4,4,3,3,5,2,9,6], k = 4
输出：[3,3,2,6]
输入：nums = [2,4,3,3,5,4,9,6], k = 4
输出：[2,3,3,4]


提示：
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
1 <= k <= nums.length
 */


/*
// 2,4,3,3,5,4,9,6
// 5,4,6,3,2,3,9,4
5
4 6
3
2
2 3
2 3 9
2 3 4
*/