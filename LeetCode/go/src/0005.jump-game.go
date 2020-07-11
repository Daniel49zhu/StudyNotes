package main

import (
	"fmt"
)

func main() {
	fmt.Println(canJump([]int{2, 3, 1, 1, 4}))
	fmt.Println(canJump([]int{3, 2, 1, 0, 4}))
	fmt.Println(canJump([]int{0, 1}))
	fmt.Println(canJump([]int{2, 0}))
}

func canJump(nums []int) bool {
	maxIndex := len(nums) - 1
	maxJump := nums[0]
	for i := 0; i <= maxJump; i++ {
		maxJump = getMax(maxJump, i+nums[i])
		if maxJump >= maxIndex {
			return true
		}
	}
	return false
}

func getMax(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}
