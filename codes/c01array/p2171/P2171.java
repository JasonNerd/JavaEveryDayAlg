package codes.c01array.p2171;

import java.util.Arrays;

// Removing Minimum Number of Magic Beans
// Medium
// https://leetcode.com/problems/removing-minimum-number-of-magic-beans/
// https://books.halfrost.com/leetcode/ChapterFour/2100~2199/2171.Removing-Minimum-Number-of-Magic-Beans/
/**
You are given an array of positive integers beans, where each integer represents the number of 
magic beans found in a particular magic bag. Remove any number of beans (possibly none) from 
each bag such that the number of beans in each remaining non-empty bag (still containing at least one bean) is equal. Once a bean has been removed from a bag, you are not allowed to return it to any of the bags.
Return the minimum number of magic beans that you have to remove.
1 <= beans.length <= 10^5
1 <= beans[i] <= 10^5
*/
/**
Input: beans = [4,1,6,5]
Output: 4
// [4,0,4,4]
Input: beans = [2,10,3,2]
Output: 7 
// [0,10,0,0]
*/

public class P2171 {
    public long minimumRemoval(int[] beans) {
        long arrLen = beans.length;
        long removal = Long.MAX_VALUE;
        for (int i=0; i<arrLen; i++){
            long tmpRemoval = 0;
            for (int j=0; j<arrLen; j++){
                if (beans[j]>beans[i])
                    tmpRemoval += (beans[j] - beans[i]);
                else if (beans[j] < beans[i])
                    tmpRemoval += beans[j];
            }
            if (removal > tmpRemoval)
                removal = tmpRemoval;
        }
        return removal;
    } 

    public long minimumRemoval2(int[] beans) {
        int n = beans.length;
        Arrays.sort(beans);
        long total = 0; // 豆子总数
        for (int i = 0; i < n; i++) {
            total += beans[i];
        }
        long res = total; // 最少需要移除的豆子数
        for (int i = 0; i < n; i++) {
            res = Math.min(res, total - (long) beans[i] * (n - i));
        }
        return res;
    } 
    
    public static void main(String[] args) {
        int[] a = new int[]{4, 1, 6, 5};
        System.out.println(new P2171().minimumRemoval2(a));
    }
}

/**
本人思路, 以 [4, 1, 6, 5] 为例, 可能的情况是:
[1, 1, 1, 1], [0, 4, 4, 4], [0, 0, 5, 5], [0, 0, 0, 6]
也即剩下不为0的数必然是数组中的某一个数. 外层指针遍历数组元素 a[i], 内层再遍历一次数组, 
对于大于 a[i] 的做减法, 等于a[i]的保持不变, 小于a[i]的减到0.

考虑一个优化的方式是先把数组进行排序, 这样可以去掉第二层循环. 此时需要注意数组中可能有重复元素.
sum = 59, len = 11, removal = sum - (len-i)*a[i]
[0, 1, 2, 3, 4, 5, 6, 7, 9, 9, 10]
[1, 1, 2, 3, 6, 6, 7, 8, 8, 8, 9]
i = 0, j = 2, a[i]=1, removal = 59-11*1
i = 2, j = 3, a[i]=2, removal = 59-9*2
i = 3, j = 4, a[i]=3, removal = 59-8*3
i = 4, j = 6, a[i]=6, removal = 59-7*6
i = 6, j = 7, a[i]=7, removal = 59-5*6
i = 7, j = 10, a[i]=8, removal = 59-5*6
...
将数组排序，得到一个曲边三角形。

根据题意，我们从曲边上选取一点，保留一个矩形，去掉其他部分。欲使去除部分最小，等价于保留部分最大。
注意, 
rr = (arrLen-i)*(long)beans[i];
由于数组的数字都是int型, 运算中可能溢出, 需要转为 Long 才能通过.
查看了题解, 思路是没错的, 代码是不过的.
主要原因是整型溢出, 特别是计算 total, 不要使用 Arrays.stream(beans).sum();
以及做乘法时注意转型.
*/

