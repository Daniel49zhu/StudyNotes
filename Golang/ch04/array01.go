package main

import "fmt"

func main() {
	var a [3]int
	fmt.Println(a[0])
	fmt.Println(a[len(a)-1])

	for i, v := range a {
		fmt.Printf("%d %d\n", i, v)
	}
	// output:
	//0 0
	//1 0
	//2 0
	for _, v := range a {
		fmt.Printf("%d \n", v)
	}
	// output:
	//0
	//0
	//0

	var q [3]int = [3]int{1, 2}
	fmt.Println(q[2]) // 0

	p := [...]int{1, 2, 3}
	fmt.Printf("%T\n", p) // "[3]int"
}
