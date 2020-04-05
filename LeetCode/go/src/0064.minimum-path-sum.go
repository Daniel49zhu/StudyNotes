package main

import (
	"fmt"
)

func main() {
	fmt.Println(minPathSum([][]int{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}))
}

func minPathSum(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	// 制造结果集
	result := make([][]int, m)
	for i := 0; i < m; i++ {
		result[i] = make([]int, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 && j == 0 {
				result[i][j] = grid[i][j]
			} else if i == 0 {
				result[i][j] = grid[i][j] + result[i][j-1]
			} else if j == 0 {
				result[i][j] = grid[i][j] + result[i-1][j]
			} else {
				result[i][j] = grid[i][j] + minNum(result[i-1][j], result[i][j-1])
			}
		}
	}
	return result[m-1][n-1]
}

func minNum(a, b int) int {
	if a <= b {
		return a
	} else {
		return b
	}
}
