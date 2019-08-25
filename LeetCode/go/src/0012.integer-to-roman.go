package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(intToRoman(4))
	fmt.Println(intToRoman(9))
	fmt.Println(intToRoman(99))
	fmt.Println(intToRoman(3999))
}

func intToRoman(num int) string {
	table := [][]string{
		{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
		{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
		{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
		{"", "M", "MM", "MMM"},
	}

	result := make([]string, 4)
	result[3] = table[0][num%10]
	result[2] = table[1][num/10%10]
	result[1] = table[2][num/100%10]
	result[0] = table[3][num/1000%10]
	return strings.Join(result, "")
}
