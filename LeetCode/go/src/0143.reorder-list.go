package main

import "fmt"

func main() {
	root := makeListNode2([]int{1, 2, 3, 4})
	reorderList(root)
	printList(root)
}

func reorderList(head *ListNode) {
	//if head == nil || head.Next == nil {
	//	return;
	//}
	//mapList := make(map[int]*ListNode)
	//tempHead := head
	//len := 0
	//for tempHead != nil {
	//	mapList[len] = tempHead
	//	len++
	//	tempHead = tempHead.Next
	//}
	//
	//newList := head
	//tmp := newList
	//for i := 0; i < len; i++ {
	//	if i%2 == 0 {
	//		tmp.Next = mapList[i/2]
	//	} else {
	//		tmp.Next = mapList[len-(i-1)/2-1]
	//	}
	//	tmp = tmp.Next
	//}
	//tmp.Next = nil
	//head = newList.Next

	if head == nil {
		return
	}

	// 获取 list 的长度 size
	cur := head
	size := 0
	for cur != nil {
		cur = cur.Next
		size++
	}

	// size 为奇数， cur 指向 list 的中间节点
	// size 为偶数， cur 指向 list 前一半的最后一个节点
	cur = head
	for i := 0; i < (size-1)/2; i++ {
		cur = cur.Next
	}

	// head -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
	//                   ^
	//                   |
	//                  cur

	// reverse cur 后面的 list
	next := cur.Next
	for next != nil {
		temp := next.Next
		next.Next = cur
		cur = next
		next = temp
	}
	end := cur

	// head -> 1 -> 2 -> 3 <-> 4 <- 5 <- 6 <- end

	// 从两头开始，整合链条
	for head != end {
		hNext := head.Next
		eNext := end.Next
		head.Next = end
		end.Next = hNext
		head = hNext
		end = eNext
	}

	// 封闭 list, 避免出现环状 list
	end.Next = nil
}

type ListNode struct {
	Val  int
	Next *ListNode
}

func makeListNode2(is []int) *ListNode {
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

func printList(root *ListNode) {
	temp := root
	for temp != nil {
		fmt.Print(temp.Val)
		temp = temp.Next
	}
}
