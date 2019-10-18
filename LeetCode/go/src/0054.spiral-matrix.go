package main

import "fmt"

func main() {
	//arr := [][]int{{1, 2, 3}, {4,5, 6}, {7, 8,9} ,{10, 11, 12}}
	//arr2 := [][]int{{1},{2}}
	//arr3 := [][]int{{1, 2, 3,4}, {5, 6,7,8}, {9,10,11,12},{13,14,15,16}}
	//arr := [][]int{{1, 2},{ 3,4}}
	//fmt.Println(spiralOrder(arr))
	//fmt.Println(spiralOrder(arr))
	//fmt.Println(spiralOrder(arr2))
	//fmt.Println(spiralOrder(arr3))
	arr4 := [][]int{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}}
	fmt.Println(spiralOrder(arr4))
}

func spiralOrder(matrix [][]int) []int {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return nil
	}
	result := make([]int, 0)
	return spiralOrderRecu(matrix, 0, len(matrix), len(matrix[0]), result)
}

// row:3 col:4
func spiralOrderRecu(matrix [][]int, start, row, col int, result []int) []int {
	if len(result) >= len(matrix)*len(matrix[0]) {
		return result
	}
	dx, dy := start, start
	inside := 0
	if row-2 < 0 || col-2 < 0 {
		inside = 0
	} else {
		inside = (row - 2) * (col - 2)
	}

	for i := 0; i < row*col-inside; i++ {
		if row == 1 || col == 1 {
			if row == 1 {
				result = append(result, matrix[dx][dy])
				dy++
			} else if col == 1 {
				result = append(result, matrix[dx][dy])
				dx++
			}
		} else {
			if dx == start && dy < col-1+start {
				result = append(result, matrix[dx][dy])
				dy++
			} else if dy == col-1+start && dx < row-1+start {
				result = append(result, matrix[dx][dy])
				dx++
			} else if dx == row-1+start && dy > start {
				result = append(result, matrix[dx][dy])
				dy--
			} else if dy == start && dx > start {
				result = append(result, matrix[dx][dy])
				dx--
			}
		}
	}
	start++
	return spiralOrderRecu(matrix, start, row-2, col-2, result)
}
