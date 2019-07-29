package main

import (
	"fmt"
)

func main() {
	fmt.Println(lengthOfLongestSubstring("abcabcbb"))
	fmt.Println(lengthOfLongestSubstring("bbbbb"))
	fmt.Println(lengthOfLongestSubstring("pwwkew"))
	fmt.Println(lengthOfLongestSubstring("aab"))
}

func lengthOfLongestSubstring(s string) int {
	location := [1 << 8]int{}
	for i := range location {
		location[i] = -1
	}
	maxLen, left := 0, 0

	for i := 0; i < len(s); i++ {

		if location[byte(s[i])] >= left {
			left = location[byte(s[i])] + 1
		} else if i+1-left > maxLen {
			maxLen = i + 1 - left
		}

		location[byte(s[i])] = i
	}
	return maxLen
}
