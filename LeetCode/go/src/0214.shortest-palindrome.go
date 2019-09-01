package main

import "fmt"

func main() {
	fmt.Println(shortestPalindrome("aacecaaa"))
	fmt.Println(shortestPalindrome("abcd"))
}

func shortestPalindrome(s string) string {
	if len(s) <= 1 {
		return s
	}

	i := getIndex(s + "#" + reverseString(s))

	return reverseString(s[i:]) + s
}

func reverseString(s string) string {
	size := len(s)
	bytes := make([]byte, size)
	for i := 0; i < size; i++ {
		bytes[i] = s[size-i-1]
	}
	return string(bytes)
}

func getIndex(s string) int {
	size := len(s)

	table := make([]int, size)
	klen, i := 0, 1

	for i < size {
		if s[i] == s[klen] {
			klen++
			table[i] = klen
			i++
		} else {
			if klen == 0 {
				table[i] = 0
				i++
			} else {
				klen = table[klen-1]
			}
		}
	}

	return table[len(s)-1]
}
