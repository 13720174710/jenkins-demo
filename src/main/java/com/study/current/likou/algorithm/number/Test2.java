package com.study.current.likou.algorithm.number;

public class Test2 {

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
     * 并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode node = addTwoNumbers(new ListNode(5), new ListNode(5));
        System.out.println(node.toString());
    }

    public static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        //生成ListNode链表对象，链表的值为0，没有指向的节点
        ListNode dummyHead = new ListNode(0);
        ListNode p = node1, q = node2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {

            //两个链表的同一位赋值给变量x和y
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;


            int sum = carry + x + y;
            carry = sum / 10; //这里的carry循环时在上面这个式子用int sum = carry + x + y;


            curr.next = new ListNode(sum % 10);  //如果结果是两位数，各位数留在结果链表里
            curr = curr.next;


            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        //最后一位的进位，如果有进位，把进位放到下一个链表里
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;

    }

    static class ListNode {
        int val;
        ListNode next;   // 下一个链表对象

        ListNode(int x) {
            val = x;
        }  //赋值链表的值

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

}
