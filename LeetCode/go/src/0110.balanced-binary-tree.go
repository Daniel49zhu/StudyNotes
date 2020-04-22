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
	fmt.Println(isBalanced(&root))
}

func isBalanced(root *TreeNode) bool {
	if root == nil {
		return true
	}
	leftH := height(root.Left) + 1
	rightH := height(root.Right) + 1

	if leftH-rightH < -1 || leftH-rightH > 1 {
		return false
	}
	return isBalanced(root.Left) && isBalanced(root.Right)
}

func height(node *TreeNode) int {
	if node == nil {
		return 0
	}

	return max(height(node.Left), height(node.Right)) + 1
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}
