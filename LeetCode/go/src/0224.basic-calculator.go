package main

import "fmt"

func main() {
	fmt.Println(calculate("(1+(14+5+2)-3)+(6+8)"))
	fmt.Println(calculate(" 2-1 + 2 "))
}

func calculate(s string) int {
	res := 0
	stack := make([]int, 0, len(s))
	sign := 1
	num := 0

	for i := 0; i < len(s); i++ {
		switch s[i] {
		case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0':
			num = 0
			for ; i < len(s) && s[i] <= '9' && s[i] >= '0'; i++ {
				num = 10*num + int(s[i]-'0')
			}
			res = sign*num + res
			i--
			break
		case '+':
			sign = 1
			break
		case '-':
			sign = -1
			break
		case '(':
			stack = append(stack, res, sign)
			res = 0
			sign = 1
			break
		case ')':
			sign = stack[len(stack)-1]
			temp := stack[len(stack)-2]
			res = temp + sign*res
			stack = stack[:len(stack)-2]
			break
		}
	}

	return res
}
