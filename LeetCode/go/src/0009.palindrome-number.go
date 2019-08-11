package main

import (
	"fmt"
)

func main() {
	fmt.Println(isPalindrome(121))
	fmt.Println(isPalindrome(0))
	fmt.Println(isPalindrome(-121))
	fmt.Println(isPalindrome(+121))
	fmt.Println(isPalindrome(12))
}

func isPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	if x < 10 {
		return true
	}
	if x%10 == 0 {
		return false
	}
	arr := []int{}
	for x > 0 {
		arr = append(arr, x%10)
		x = x / 10
	}

	for i := 0; len(arr)-i-i-1 > 0; i++ {
		if arr[i]-arr[len(arr)-i-1] == 0 {
			continue
		} else {
			return false
		}
	}
	return true
}
