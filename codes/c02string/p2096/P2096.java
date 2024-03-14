package codes.c02string.p2096;

/*
给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。
每个节点的值为 1 到 n 中的一个整数，且互不相同。
给你一个整数 startValue ，表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。
请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向：

'L' 表示从一个节点前往它的 左孩子 节点。
'R' 表示从一个节点前往它的 右孩子 节点。
'U' 表示从一个节点前往它的 父 节点。
请你返回从 s 到 t 最短路径 每一步的方向。

输入：root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
输出："UURL"
解释：最短路径为：3 → 1 → 5 → 2 → 6 。

树中节点数目为 n 。
2 <= n <= 105
1 <= Node.val <= n
树中所有节点的值 互不相同 。
1 <= startValue, destValue <= n
startValue != destValue
*/

public class P2096 {
    boolean stop = false;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder builderDest = new StringBuilder();    // from root to dest
        StringBuilder builderStar = new StringBuilder();    // from root to start
        if (startValue == root.val){
            tranverse(root, builderDest, destValue);      // from root(start) to dest
            return builderDest.toString();
        }else if (destValue == root.val){
            tranverse(root, builderStar, startValue);      // from root(dest) to start
            reverse(builderStar);
            return builderStar.toString();
        }else{
            tranverse(root, builderDest, destValue);      // from root to dest
            stop = false;
            tranverse(root, builderStar, startValue);      // from root to start\
            /* 删除公共前缀, 因为两个节点的共同祖先不一定是根节点 */
            int m = builderDest.length(), n = builderStar.length(), p = 0;
            for (int i=0; i<m && i<n; i++)
                if (builderDest.charAt(i) == builderStar.charAt(i))
                    ++p;
                else break;
            if (p>0){
                builderStar.delete(0, p);
                builderDest.delete(0, p);
            }
            reverse(builderStar);
            builderStar.append(builderDest);
            return builderStar.toString();
        }
    }

    public void reverse(StringBuilder builder){
        String tmp = builder.toString();
        builder.setLength(0);
        for (int i=0; i<tmp.length(); i++)
            builder.append('U');
    }

    public void tranverse(TreeNode root, StringBuilder builder, int target){
        // 从根节点向下访问, 直到寻找到目标节点, builder 记录访问路径
        if (root.val == target){
            stop = true;
            return ;
        }
        if (root.left != null && !stop){
            builder.append('L');
            tranverse(root.left, builder, target);
            if (stop)
                return ;
            builder.setLength(builder.length()-1);
        }
        if (root.right != null && !stop){
            builder.append('R');
            tranverse(root.right, builder, target);
            if (stop)
                return ;
            builder.setLength(builder.length()-1);
        }
    }
    
    public TreeNode example(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(4);
        return root;
    }
    public static void main(String[] args) {
        P2096 p2096 = new P2096();
        TreeNode root = p2096.example();
        System.out.println(p2096.getDirections(root, 3, 6));
    }
}

/*
二叉树中一个节点到另一个节点的最短路径一定可以分为两个部分（可能为空）：
从起点节点向上到两个节点的最近公共祖先，再从最近公共祖先向下到达终点节点。
首先需要找到起点 s 与公共祖先的节点之间的 path1，公共祖先节点与终点 t 的 path2。
再删掉 2 个 path 的公共前缀。如果起点 s 和终点 t 在不同的分支上，不存在公共前缀。
如果他们在相同的分支上，那么最终答案要去掉这个公共前缀。
删除掉公共前缀以后，需要再整理一下最终答案的输出格式。
由于题目要求，起点到公共祖先节点需要输出 U，所以把这段 path1 全部改成 U，然后再拼接上 path2 字符串，
即可得到的字符串即为待求 ss 到 tt 每一步的最短路径。
*/