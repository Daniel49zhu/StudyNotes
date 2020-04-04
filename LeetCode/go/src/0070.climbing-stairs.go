package main

import "fmt"

func main() {
	fmt.Println(climbStairs(2))
	fmt.Println(climbStairs(3))
	fmt.Println(climbStairs(10))
}

func climbStairs(n int) int {
	result := make([]int, n)
	for i := 0; i < n; i++ {
		if i == 0 {
			result[i] = 1
		} else if i == 1 {
			result[i] = 2
		} else {
			result[i] = result[i-1] + result[i-2]
		}
	}
	return result[n-1]
}
