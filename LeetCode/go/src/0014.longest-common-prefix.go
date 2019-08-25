package main

import "fmt"

func main() {
	fmt.Println(longestCommonPrefix([]string{"flower", "flow", "flight"}))
	fmt.Println(longestCommonPrefix([]string{"flower"}))
}

func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}
	if len(strs) == 1 {
		return strs[0]
	}

	shortStr := ""
	for i := 0; i < len(strs); i++ {
		if i == 0 {
			shortStr = strs[0]
		}
		if len(shortStr) > len(strs[i]) {
			shortStr = strs[i]
		}
	}

	for i, r := range shortStr {
		for j := 0; j < len(strs); j++ {
			if byte(r) != strs[j][i] {
				return strs[j][:i]
			}
		}
	}
	return shortStr
}
