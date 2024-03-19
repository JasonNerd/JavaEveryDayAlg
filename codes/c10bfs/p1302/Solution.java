package codes.c10bfs.p1302;

import java.util.ArrayDeque;
import java.util.Queue;

import codes.c02string.p2096.TreeNode;
// https://zhuanlan.zhihu.com/p/136183284
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int cum = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            cum = 0;    // 当前层节点值的和
            // 每次都是对某一层的节点统一处理(而不是单个单个的处理)
            for (int i=0; i<n; i++){
                TreeNode node = q.poll();
                cum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

        }
        return cum;
    }
}
/*
输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
输出：15
示例 2：

输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
输出：19
 

提示：

树中节点数目在范围 [1, 104] 之间。
1 <= Node.val <= 100
 */