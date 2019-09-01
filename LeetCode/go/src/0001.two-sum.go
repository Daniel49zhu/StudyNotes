package main

import "fmt"

func main() {
	//a := []int{2,3,4,5}
	fmt.Println(twoSum([]int{2, 3, 4, 5}, 9))
}

func twoSum(nums []int, target int) []int {
	index := make(map[int]int, len(nums))

	for i, b := range nums {
		if j, ok := index[target-b]; ok {
			return []int{j, i}
		}
		index[b] = i
	}
	return nil
}
