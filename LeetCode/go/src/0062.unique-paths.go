package main

import "fmt"

func main() {
	fmt.Println(uniquePaths(3, 2))
	fmt.Println(uniquePaths(7, 3))
}

func uniquePaths(m int, n int) int {
	result := make([][]int, m)
	for i := 0; i < m; i++ {
		result[i] = make([]int, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 || j == 0 {
				result[i][j] = 1
			} else {
				result[i][j] = result[i-1][j] + result[i][j-1]
			}
		}
	}
	return result[m-1][n-1]
}
