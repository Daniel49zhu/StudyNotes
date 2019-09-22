package main

import "fmt"

func main() {
	l1 := makeListNode([]int{2, 4, 3})
	l2 := makeListNode([]int{5, 6, 8, 1})
	l3 := addTwoNumbers(l1, l2)
	fmt.Println(l3)
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	originList := ListNode{}
	cur := &originList
	carry := 0

	for l1 != nil || l2 != nil || carry > 0 {
		sum := carry
		if l1 != nil {
			sum += l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			sum += l2.Val
			l2 = l2.Next
		}
		carry = sum / 10

		cur.Next = &ListNode{Val: sum % 10}
		cur = cur.Next
	}
	return originList.Next
}

func makeListNode(is []int) *ListNode {
	if len(is) == 0 {
		return nil
	}

	res := &ListNode{
		Val: is[0],
	}
	temp := res

	for i := 1; i < len(is); i++ {
		temp.Next = &ListNode{Val: is[i]}
		temp = temp.Next
	}

	return res
}
