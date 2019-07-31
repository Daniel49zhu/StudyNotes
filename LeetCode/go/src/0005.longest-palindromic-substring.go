package main

import "fmt"

func main() {
	var str1 = "aab"
	var str2 = "babacacad"
	var str3 = "oerowoewrowe"
	fmt.Println(longestPalindrome(str1))
	fmt.Println(longestPalindrome(str2))
	fmt.Println(longestPalindrome(str3))
}

func longestPalindrome(s string) string {
	if len(s) < 2 {
		return s
	}
	sLen := len(s)
	locations := make([][]bool, sLen)
	for i := 0; i < sLen; i++ {
		locations[i] = make([]bool, sLen)
	}

	maxLen, start := 0, 0
	for i := 0; i < sLen; i++ {
		j := i
		for j >= 0 {
			if s[j] == s[i] && (i-j < 2 || locations[i-1][j+1]) {
				locations[i][j] = true
				if maxLen < i-j+1 {
					maxLen = i - j + 1
					start = j
				}
			}
			j--
		}
	}
	return s[start : start+maxLen]
}
