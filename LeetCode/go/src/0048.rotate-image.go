package main

import "fmt"

func main() {
	matrix1 := [][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
	matrix2 := [][]int{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}}

	rotate(matrix1)
	rotate(matrix2)

	fmt.Println(matrix1)
	fmt.Println(matrix2)
}

func rotate(matrix [][]int) {
	len := len(matrix)

	finalArr := make([][]int, len)
	for i := 0; i < len; i++ {
		finalArr[i] = make([]int, len)
	}

	for i := 0; i < len; i++ {
		for j := 0; j < len; j++ {
			finalArr[i][j] = matrix[i][j]
		}
	}

	for i := 0; i < len; i++ {
		for j := 0; j < len; j++ {
			matrix[j][len-i-1] = finalArr[i][j]
		}
	}

}

// 可以做一次反转 再进行一次转置即可旋转90°
