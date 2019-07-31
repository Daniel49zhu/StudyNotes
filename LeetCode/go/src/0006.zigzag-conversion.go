package main

import "fmt"

func main() {
	fmt.Println(convert("PAYPALISHIRING", 3)) //PAHNAPLSIIGYIR
	fmt.Println(convert("PAYPALISHIRING", 4))
}

func convert(s string, numRows int) string {
	key := 2 * (numRows - 1)
	for i := 0; i < len(s); i += key {
		fmt.Print(i)
	}
	return ""
}
