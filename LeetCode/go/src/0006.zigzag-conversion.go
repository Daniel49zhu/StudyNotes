package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(convert("PAYPALISHIRING", 3)) //PAHNAPLSIIGYIR
	fmt.Println(convert("PAYPALISHIRING", 4))
	fmt.Println(convert("AB", 1))
}

func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	location := make(map[int][]string, numRows)
	for i := 0; i < numRows; i++ {
		location[i] = make([]string, 0)
	}
	step := numRows - 1
	sLen := len(s)

	for i := 0; i < sLen; i += step {
		for j := i; j <= i+step-1 && j < sLen; j++ {
			div := j / step
			mod := j % step
			if div%2 == 0 {
				location[mod] = append(location[mod], s[j:j+1])
			} else {
				location[step-mod] = append(location[step-mod], s[j:j+1])
			}
		}
	}
	str := ""
	for i := 0; i < numRows; i++ {
		str += strings.Join(location[i], "")
	}
	return str
}
