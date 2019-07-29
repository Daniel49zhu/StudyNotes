package main

import (
	"fmt"
)

func main() {
	fmt.Println(findMedianSortedArrays([]int{1, 3}, []int{2}))
	fmt.Println(findMedianSortedArrays([]int{1, 2}, []int{3, 4}))
}

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	lenNums1, i := len(nums1), 0
	lenNums2, j := len(nums2), 0
	res := make([]int, lenNums1+lenNums2)

	for k := 0; k < lenNums1+lenNums2; k++ {
		// 从nums1中取出元素的情况
		// nums2已经到头
		if j == lenNums2 || (i < lenNums1 && j < lenNums2 && nums1[i] < nums2[j]) {
			res[k] = nums1[i]
			i++
			continue
		}

		if i == lenNums1 || (i < lenNums1 && j < lenNums2 && nums1[i] >= nums2[j]) {
			res[k] = nums2[j]
			j++
		}
	}
	l := len(res)

	if l == 0 {
		panic("切片的长度为0，无法求解中位数。")
	}

	if l%2 == 0 {
		return float64(res[l/2]+res[l/2-1]) / 2.0
	}

	return float64(res[l/2])
}
