package main

import (
	"fmt"
	"strings"
)

//Symbol       Value
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
func main() {
	fmt.Println(romanToInt("MMMCMXCIX"))
	fmt.Println(romanToInt("XCIX"))
}

func romanToInt(s string) int {
	table := make(map[string]int)
	table["I"] = 1
	table["V"] = 5
	table["X"] = 10
	table["L"] = 50
	table["C"] = 100
	table["D"] = 500
	table["M"] = 1000

	strArr := strings.Split(s, "")
	result := 0
	for i := 0; i < len(strArr); i++ {
		if i <= len(strArr)-2 {
			if table[strArr[i]] < table[strArr[i+1]] {
				result -= table[strArr[i]]
			} else {
				result += table[strArr[i]]
			}
		} else {
			result += table[strArr[i]]
		}
	}
	return result
}
