package main

import (
	"fmt"
	"time"
)

func main() {
	done := make(chan bool)
	go worker(done)

	<-done // 去掉这一行程序会提前退出
}

func worker(done chan bool) {
	fmt.Println("working...")
	time.Sleep(time.Second)
	fmt.Println("done")

	done <- true
}
