package main

import "fmt"

func main() {
	fmt.Println(isHappy(19))
}

func isHappy(n int) bool {
	slow := n
	fast := multi(n)

	for slow != fast {
		slow = multi(slow)
		fast = multi(multi(fast))
	}

	if slow == 1 {
		return true
	} else {
		return false
	}
}

func multi(num int) int {
	res := 0
	for num != 0 {
		res += (num % 10) * (num % 10)
		num /= 10
	}
	return res
}
