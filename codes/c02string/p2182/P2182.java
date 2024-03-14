package codes.c02string.p2182;
// https://leetcode.cn/problems/construct-string-with-repeat-limit/description/
// https://books.halfrost.com/leetcode/ChapterFour/2100~2199/2182.Construct-String-With-Repeat-Limit/
/*
给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，
使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
返回 字典序最大的 repeatLimitedString 。
如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，
则认为字符串 a 比字符串 b 字典序更大 。如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。

示例：
输入：s = "cczazcc", repeatLimit = 3
输出："zzcccac"
输入：s = "aababab", repeatLimit = 2
输出："bbabaa"

提示：
1 <= repeatLimit <= s.length <= 10^5
s 由小写英文字母组成
*/

public class P2182 {
    final int LETTER_N = 26;
    int[] tokens = new int[LETTER_N];

    public String repeatLimitedString(String s, int repeatLimit) {
        StringBuilder builder = new StringBuilder();
        int n = s.length();
        for (int i=0; i<n; i++)
            tokens[s.charAt(i)-'a']++;
        int p = LETTER_N, q=next(p);
        while (q != -1) {
            if (tokens[q] > repeatLimit){
                append(builder, (char)('a'+q), repeatLimit);    // 附上当前最大的字母
                tokens[q] -= repeatLimit;                       // 并使其达到限额
                p = next(q);
                if (p == -1) break;
                append(builder, (char)('a'+p), 1);          // 附上1个当前次大的字母
                tokens[p] -= 1;
                p = q+1;    // q 位置的字母还未用完, 将 p 指向 q 的下一位
            }else{
                append(builder, (char)('a'+q), tokens[q]);      // 附上当前最大的字母
                tokens[q] = 0;
                p = q;      // q 位置的字母还已经用完, 将 p 指向 q
            }
            q = next(p);
        }
        return builder.toString();
    }

    public int next(int last){
        for(int i=last-1; i>=0; i--)
            if (tokens[i] > 0)
                return i;
        return -1;
    }

    public void append(StringBuilder builder, char ch, int cnt){
        for(int i=0; i<cnt; i++)
            builder.append(ch);
    }

    public static void main(String[] args) {
        String sa = "cczazcc", sb = "aababab", sc = "abcaabbcbacbabbba";
        P2182 p2182 = new P2182();
        System.out.println(p2182.repeatLimitedString(sa, 3));
        System.out.println(p2182.repeatLimitedString(sb, 2));
        System.out.println(p2182.repeatLimitedString(sc, 3));
    }
}
/*
利用贪心的思想，由于题意要求返回字典序最大的字符串，所以先从字典序最大的字母开始选起。
然后选择当前字典序最大的字母个数和 limit 的最小值。如果当前字典序最大的字母比较多，
多于 limit，不能一直选择它。选完 limit 个以后，需要选一个字典序次大的字母，
选完这个字母以后再次选择字典序最大的字母。因为 limit 限制字母不能连续多于 limit 个。
如此循环，直到所有的字母都选完。这样的策略排列出来的字母串为最大字典序。
*/