package main

import (
	"fmt"
)

// k 索引差
// t 数值差
//  | i - j | <= k && | nums[i] - nums[j] | <= t
func main() {
	fmt.Println(containsNearbyAlmostDuplicate([]int{1, 2, 3, 1}, 3, 0))       //true
	fmt.Println(containsNearbyAlmostDuplicate([]int{1, 0, 1, 1}, 1, 2))       // true
	fmt.Println(containsNearbyAlmostDuplicate([]int{1, 5, 9, 1, 5, 9}, 2, 3)) // false
}

func containsNearbyAlmostDuplicate(nums []int, k int, t int) bool {
	size := len(nums)
	if k == 0 || t < 0 || size <= 1 {
		return false
	}
	for i := 0; i < len(nums); i++ {
		for ; k != 0; k-- {
			if abs(nums[i]-nums[i+k]) <= t {
				return true
			}
		}
	}
	return false
}

func abs(a int) int {
	if a < 0 {
		return -1 * a
	} else {
		return a
	}
}
