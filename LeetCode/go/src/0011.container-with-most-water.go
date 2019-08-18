package main

import (
	"fmt"
)

func main() {
	fmt.Println(maxArea([]int{1, 2, 3, 4}))
	fmt.Println(maxArea([]int{1, 8, 6, 2, 5, 4, 8, 3, 7}))
}

func maxArea(height []int) int {
	maxArea, i, j := 0, 0, len(height)-1
	for j-i > 0 {
		tempHeight, tempArea := 0, 0
		if height[i] > height[j] {
			tempHeight = height[j]
			tempArea = (j - i) * tempHeight
			j--
		} else {
			tempHeight = height[i]
			tempArea = (j - i) * tempHeight
			i++
		}
		if tempArea > maxArea {
			maxArea = tempArea
		}
	}
	return maxArea
}
