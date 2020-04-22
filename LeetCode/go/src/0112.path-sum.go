package main

import (
	"fmt"
	"kit"
)

type TreeNode = kit.TreeNode

func main() {
	root := TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2}
	root.Right = &TreeNode{Val: 2}
	root.Left.Left = &TreeNode{Val: 3}
	root.Left.Right = &TreeNode{Val: 3}
	root.Left.Left.Left = &TreeNode{Val: 4}
	root.Left.Left.Right = &TreeNode{Val: 4}
	fmt.Println(hasPathSum(&root, 8))
}

func hasPathSum(root *TreeNode, sum int) bool {
	if root == nil {
		return false
	}
	sumArr := []int{}
	getSum(root, &sumArr)

	for _, num := range sumArr {
		if sum == num {
			return true
		}
	}

	return false
}

func getSum(node *TreeNode, arr *[]int) {
	if node.Left == nil && node.Right == nil {
		*arr = append(*arr, node.Val)
	} else if node.Left == nil && node.Right != nil {
		node.Right.Val += node.Val
		getSum(node.Right, arr)
	} else if node.Left != nil && node.Right == nil {
		node.Left.Val += node.Val
		getSum(node.Left, arr)
	} else {
		node.Right.Val += node.Val
		node.Left.Val += node.Val
		getSum(node.Left, arr)
		getSum(node.Right, arr)
	}
}
