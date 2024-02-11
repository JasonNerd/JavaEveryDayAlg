package codes.stack.p1653;

import java.util.ArrayDeque;
import java.util.Deque;

public class P1653 {
    public int minimumDeletions(String s){
        Deque<Character> chs = new ArrayDeque<>();    // work stack
        int n = s.length(), res=0;
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if (chs.isEmpty() || ch >= chs.getLast())
                chs.add(ch);
            else{
                chs.removeLast();
                ++res;
            }

        }
        return res;
    }

    public int minimumDeletions2(String s){
        int n = s.length();  // 使用dp, dp[i]表示s[0...i]中符合 a...b 的最大长度
        int a = 0, b = 0;       // a 表示以a结尾时的最大符合长度, b 表示以b结尾时的最大符合长度
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if (ch == 'a') a=a+1;   // 当前字符为 a 时, 那么上一个符合序列结尾也为a, 因此为上一个a的长度加1
            else b = Math.max(a, b)+1;  // 当前字符为 a 时, 那么上一个符合序列结尾为a或b, 因此为上一个a或b的长度加1
        }
        return n-Math.max(a, b);
    }

    public static void main(String[] args) {
        System.out.println(new P1653().minimumDeletions("aababbab"));
    }
}
/*
给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
你可以删除 s 中任意数目的字符，使得 s 平衡 。
当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
请你返回使 s 平衡 的 最少 删除次数。

示例 1：
输入：s = "aababbab"
输出：2
解释：你可以选择以下任意一种方案：
下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。

示例 2：
输入：s = "bbaaaaabb"
输出：2
解释：唯一的最优解是删除最前面两个字符。

提示：
1 <= s.length <= 10^5
s[i] 要么是 'a' 要么是 'b'​ 。​
 */

// bbaaaaabb

// aababbab
// a: a=1,b=0
// aa: a=2,b=0
// aab: a=2,b=3
// aaba: a=3,b=3
// aabab: a=3,b=4
// aababa: a=4,b=4
// aababab: a=4,b=5
// 3
/*
[a...a] a/b
[a...b]

dp[i+1] = min(dp[i] + 1, bCount), s[i] == 'a'
dp[i+1] = dp[i], s[i] == 'b'
*/