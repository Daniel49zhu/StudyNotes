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
	originMap := make(map[int][]int, len(s))
	for i := range s {
		originMap[int(s[i])] = append(originMap[int(s[i])], i)
	}
	maxLen := 0
	left, right := 0, 0
	for k := range originMap {
		if len(originMap[k]) > 1 {
			for m := 0; m < len(originMap[k]); m++ {
				for n := m + 1; n < len(originMap[k]); n++ {
					tempM := originMap[k][m]
					tempN := originMap[k][n]
					if isPalindrome(&s, tempM, tempN) && maxLen < (tempN-tempM+1) {
						maxLen = tempN - tempM + 1
						left = tempM
						right = tempN
					}
				}
			}
		}
	}
	return s[left : right+1]
}

func isPalindrome(s *string, i, j int) bool {
	for i < j {
		if (*s)[i] != (*s)[j] {
			return false
		}
		i++
		j--
	}
	return true
}
