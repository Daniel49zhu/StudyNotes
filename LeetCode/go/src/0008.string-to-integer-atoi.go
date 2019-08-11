package main

import (
	"fmt"
	"math"
	"strings"
)

func main() {
	fmt.Println(myAtoi("12314"))
	fmt.Println(myAtoi(" +12314"))
	fmt.Println(myAtoi("-12314"))
	fmt.Println(myAtoi("12314 hello world"))
	fmt.Println(myAtoi("12314 hello world56"))
	fmt.Println(myAtoi("hello world 12314"))
	fmt.Println(myAtoi("123142534513414324254"))
	fmt.Println(myAtoi(""))
	fmt.Println(myAtoi("3.14159"))
	fmt.Println(myAtoi("+-2"))
	fmt.Println(myAtoi("+5+"))
	fmt.Println(myAtoi("9223372036854775808"))
}

func myAtoi(str string) int {
	if len(str) == 0 {
		return 0
	}
	str = strings.TrimSpace(str)
	res := 0
	positive := true

flag:
	for k, v := range str {
		switch v {
		case '+':
			if k == 0 {
				break
			} else {
				return res
			}
		case '-':
			if k == 0 {
				positive = false
				break
			} else {
				return res
			}
		case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':

			if !positive {
				res = 10*res - (int(v) - '0')
			} else {
				res = 10*res + (int(v) - '0')
			}

			if res > math.MaxInt32 {
				return math.MaxInt32
			} else if res < math.MinInt32 {
				return math.MinInt32
			}
			break
		default:
			if k == 0 {
				return 0
			} else {
				break flag
			}
		}
	}

	return res

}
