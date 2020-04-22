package main

import (
	"fmt"
)

func main() {
	root := TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2}
	root.Right = &TreeNode{Val: 2}
	root.Left.Left = &TreeNode{Val: 3}
	root.Left.Right = &TreeNode{Val: 3}
	root.Left.Left.Left = &TreeNode{Val: 4}
	root.Left.Left.Right = &TreeNode{Val: 4}
	fmt.Println(minDepth(&root))
}

func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	if root.Left != nil && root.Right != nil {
		return min(minDepth(root.Left), minDepth(root.Right)) + 1
	} else {
		return max(minDepth(root.Left), minDepth(root.Right)) + 1
	}
}

func min(a, b int) int {
	if a > b {
		return b
	} else {
		return a
	}
}
