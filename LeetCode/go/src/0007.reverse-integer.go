package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(reverse(2987654321))
}

//32-bit signed integer range: [−2^31,  2^31 − 1], assume that your function returns 0 when the reversed integer overflows.
func reverse(x int) int {
	sign := 1
	if x < 0 {
		sign = -1
		x = -1 * x
	}

	res := 0
	for x > 0 {
		temp := x % 10
		res = res*10 + temp
		x /= 10
	}

	res = sign * res

	if res > math.MaxInt32 || res < math.MinInt32 {
		res = 0
	}
	return res
}
