package codes.linkedlist.p2181;
import codes.twopinters.p1721.ListNode;
import static codes.twopinters.p1721.p1721.create_list;
import static codes.twopinters.p1721.p1721.print_list;

// https://leetcode.cn/problems/merge-nodes-in-between-zeros
// https://books.halfrost.com/leetcode/ChapterFour/2100~2199/2181.Merge-Nodes-in-Between-Zeros/
/*
给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。
链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。
然后将所有 0 移除，修改后的链表不应该含有任何 0 。
返回修改后链表的头节点 head 。

示例 1：
输入：head = [0,3,1,0,4,5,2,0]
输出：[4,11]

示例 2：
输入：head = [0,1,0,3,0,2,2,0]
输出：[1,3,4]

提示：
列表中的节点数目在范围 [3, 2 * 10^5] 内
0 <= Node.val <= 1000
不存在连续两个 Node.val == 0 的节点
链表的 开端 和 末尾 节点都满足 Node.val == 0
*/
public class P2181 {
    public ListNode mergeNodes(ListNode head) {
        ListNode res=new ListNode(), p=head.next, r=res;
        int tmp = 0;
        while (p != null) {
            if (p.val == 0){
                ListNode node = new ListNode(tmp);
                r.next = node;
                r = r.next;
                tmp = 0;
            }else{
                tmp += p.val;
            }
            p = p.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        int a[] = {0,3,1,0,4,5,2,0};
        ListNode head = create_list(a);
        print_list(head);
        System.out.println();
        print_list(new P2181().mergeNodes(head));
    }
}
