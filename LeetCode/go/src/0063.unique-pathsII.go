package main

import "fmt"

func main() {

	fmt.Println(uniquePathsWithObstacles([][]int{
		{0, 0, 0},
		{0, 1, 0},
		{0, 0, 0},
	}))

	fmt.Println(uniquePathsWithObstacles([][]int{
		{0, 0, 0, 0, 0, 0},
		{0, 1, 0, 0, 1, 0},
		{0, 0, 0, 0, 0, 0},
	}))

	fmt.Println(uniquePathsWithObstacles([][]int{
		{0},
	}))

	fmt.Println(uniquePathsWithObstacles([][]int{
		{1},
	}))
	fmt.Println(uniquePathsWithObstacles([][]int{
		{1, 0},
	}))
	fmt.Println(uniquePathsWithObstacles([][]int{
		{0, 1},
	}))
}

func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	var m = len(obstacleGrid)
	var n = len(obstacleGrid[0])

	// 构造结果集
	result := make([][]int, m)
	for i := 0; i < m; i++ {
		result[i] = make([]int, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 { // 在第一行上
				if obstacleGrid[i][j] == 1 {
					result[i][j] = 0
				} else if j > 0 && result[i][j-1] == 0 {
					result[i][j] = 0
				} else {
					result[i][j] = 1
				}
			} else if j == 0 { // 在第一列
				if obstacleGrid[i][j] == 1 {
					result[i][j] = 0
				} else if i > 0 && result[i-1][j] == 0 {
					result[i][j] = 0
				} else {
					result[i][j] = 1
				}
			} else if obstacleGrid[i][j] == 1 {
				result[i][j] = 0
			} else {
				result[i][j] = result[i-1][j] + result[i][j-1]
			}
		}
	}

	return result[m-1][n-1]
}
