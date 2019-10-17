package main

import "fmt"

func main() {
	fmt.Println(rangeBitwiseAnd(5, 2147483647))
}

func rangeBitwiseAnd(m int, n int) int {
	i := uint(0)
	for m != n {
		m >>= 1
		n >>= 1
		i++
	}
	return m << i
}
