package codes.backtrace.p1079;

import java.util.Arrays;

// 全排列算法, 给出n, 编程打印1~n的排列

public class Permutation {
    private static final int n = 4;
    // res 为存放结果的数组(也即每次排列完后的结果)
    private static int[] res = new int[n];
    // rec 为记录, res[i] 表示数字 i+1 是否已记录到 res 中
    private static boolean[] rec = new boolean[n];

    // i 表示当前填入的是 res 中第i个位置
    private static void perm_list(int i){
        if(i == n)
            System.out.println(Arrays.toString(res));
        else{
            // 循环表示: 对于第i个位置, 可以选择 1~n 中的任意一个数字
            for (int j=1; j<=n; j++){
                if (!rec[j-1]){     // 仅当 j 未出现在排列中时
                    res[i] = j;     // 才选择 j 作为排列的第 i 个数
                    rec[j-1] = true;
                    perm_list(i+1);     // 递归的填充第 i+1 个位置
                    rec[j-1] = false;   // 回到原状态
                }
            }
        }

    }
    public static void main(String[] args) {
        perm_list(0);
    }
}
