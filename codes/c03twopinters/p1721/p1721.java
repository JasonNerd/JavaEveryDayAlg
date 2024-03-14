package codes.c03twopinters.p1721;

// https://books.halfrost.com/leetcode/ChapterFour/1700~1799/1721.Swapping-Nodes-in-a-Linked-List/
// https://leetcode.cn/problems/swapping-nodes-in-a-linked-list/description

/*
给你链表的头节点 head 和一个整数 k 。交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。

输入：head = [1,2,3,4,5], k = 2
输出：[1,4,3,2,5]

输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
输出：[7,9,6,6,8,7,3,0,9,5]

输入：head = [1], k = 1
输出：[1]

输入：head = [1,2], k = 1
输出：[2,1]

输入：head = [1,2,3], k = 2
输出：[1,2,3]

提示：
链表中节点的数目是 n
1 <= k <= n <= 10^5
0 <= Node.val <= 100
 */
public class p1721 {
    public static void print_list(ListNode head){
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null)
                System.out.print("->");
            head = head.next;
        }
    }
    public static ListNode create_list(int a[]){
        ListNode head = new ListNode(a[0]);
        ListNode p = head;
        for(int i=1; i<a.length; i++){
            p.next = new ListNode(a[i]);
            p = p.next;
        }
        return head;
    }
    public ListNode swapNodes(ListNode head, int k) {
        ListNode p=head, q, r;
        for (int i=1; i<k; i++)
            p = p.next;
        r = p;  // 将第k个节点暂存
        p = p.next;
        q = head;
        while (p != null) {
            p = p.next;
            q = q.next;
        }
        // 此时 q 为倒数第k个值, 执行节点交换(实际仅为val的交换)
        int tmp = r.val;
        r.val = q.val;
        q.val = tmp;
        return head;
    }

    public static void main(String[] args) {
        // head = [1,2,3,4,5], k = 2
        // head = [7,9,6,6,7,8,3,0,9,5], k = 5
        // head = [1], k = 1
        // head = [1,2], k = 1
        // head = [1,2,3], k = 2
        int[] head = {1,2,3};
        int k = 2;
        print_list(new p1721().swapNodes(create_list(head), k));
    }
}

/*
 * 同向双指针, 前指针指向第k(+1)个, 后指针也开始指向头节点, 随后同步前进(两个指针始终隔着k个节点)
 */