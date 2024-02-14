package codes.tree.p1600;

import java.util.ArrayDeque;
import java.util.Queue;

import codes.string.p2096.TreeNode;

/*
如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：

二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。

输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
输出：true
解释：每一层的节点值分别是：
0 层：[1]
1 层：[10,4]
2 层：[3,7,9]
3 层：[12,8,6,2]
由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。

输入：root = [5,4,2,3,3,7]
输出：false
解释：每一层的节点值分别是：
0 层：[5]
1 层：[4,2]
2 层：[3,3,7]
2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。

输入：root = [5,9,1,3,5,7]
输出：false
解释：1 层上的节点值应为偶数。
示例 4：

输入：root = [1]
输出：true
示例 5：

输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
输出：true
 

提示：

树中节点数在范围 [1, 105] 内
1 <= Node.val <= 106
 */
public class P1600 {
    public boolean isEvenOddTree(TreeNode root) {
        // 使用层序遍历, 注意记录当前遍历层数
        Queue<TreeNode> workQueue = new ArrayDeque<>();
        int layer = 0;       // 当前层的层序号
        TreeNode layerHead = root, layerTail=root, tail=root;      // 层头节点, 层尾节点, 队尾节点
        int cmp_val=root.val;   // 用于比较的值(判断是否严格单调)
        workQueue.add(root);
        while (!workQueue.isEmpty()) {
            TreeNode tn = workQueue.remove();
            // 判断当前节点是否符合题意
            if(layer % 2==0){           // 偶数层
                if(tn.val%2==0)
                    return false;       // 不为奇数不合题意
                if(tn != layerHead && tn.val<=cmp_val)
                    return false;       // 没有严格单增不合题意
                cmp_val = tn.val;
            }else{                      // 奇数层
                if(tn.val%2==1)         // 不为偶数不合题意
                    return false;
                if(tn != layerHead && tn.val>=cmp_val)
                    return false;       // 没有严格单减不合题意
                cmp_val = tn.val;
            }
            // 将孩子节点加入队列
            if (tn.left != null){
                workQueue.add(tn.left);
                tail = tn.left;
            }
            if (tn.right != null){
                workQueue.add(tn.right);
                tail = tn.right;
            }
            // 输出 debug 信息
            // System.out.println("--------------------");
            // System.out.println("Current Node: " + tn.val);
            // System.out.println("Current Layer: " + layer);
            // 若当前层遍历完毕, 转到下一层, 并更新相关状态, 注意若队列为空则退出循环
            if (tn == layerTail){
                layer++;
                layerTail = tail;               // 上一层尾节点的右孩子或者左孩子
                layerHead = workQueue.peek();    // 队列头节点
                if(layerHead == null) break;
                cmp_val = layerHead.val;
            }
        }
        return true;
    }

    /**
     * 依据完全二叉树的数组重构
     * 例如: [1,10,4,3,null,7,9,12,8,null,null,6,null,null,2]
     */
    public static TreeNode createBinTree(Integer[] nds, int rid){
        TreeNode root = new TreeNode(nds[rid]);
        if ((2*rid+1 < nds.length) && nds[2*rid+1] != null){
            root.left = createBinTree(nds, 2*rid+1);
        }
        if ((2*rid+2 < nds.length) && nds[2*rid+2] != null){
            root.right = createBinTree(nds, 2*rid+2);
        }
        return root;
    }

    /**
     * 依据层序遍历数组重构
     * 例如: [1,10,4,3,null,7,9,12,8,6,null,null,2]
     */
    public static TreeNode createBTLayer(Integer[] nds, int rid){
        Queue<TreeNode> workQueue = new ArrayDeque<>();
        TreeNode root = new TreeNode(nds[rid]);
        workQueue.add(root);
        while (!workQueue.isEmpty()) {
            TreeNode tn = workQueue.remove();

            ++rid;
            if (rid >= nds.length) break;
            if(null != nds[rid]){
                tn.left = new TreeNode(nds[rid]);
                workQueue.add(tn.left);
            }

            ++rid;
            if (rid >= nds.length) break;
            if(null != nds[rid]){
                tn.right = new TreeNode(nds[rid]);
                workQueue.add(tn.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // 1,10,4,3,null,7,9,12,8,6,null,null,2
        // 5,4,2,3,3,7
        // 5,9,1,3,5,7
        // 1
        // 11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17
        // 1,10,4,3,5,7,9,null,null,12,8,null,6
        Integer[] nds = {1,10,4,3,null,7,9,12,8,6,null,null,2};
        TreeNode root = createBTLayer(nds, 0);
        System.out.println(new P1600().isEvenOddTree(root));
    }
}

