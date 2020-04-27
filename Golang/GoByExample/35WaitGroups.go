package main

import (
	"fmt"
	"sync"
	"time"
)

func worker3(id int, wg *sync.WaitGroup) {
	defer wg.Done()

	fmt.Printf("Worker %d strating\n", id)

	time.Sleep(time.Second)
	fmt.Printf("Worker %d done\n", id)
}

func main() {
	var wg sync.WaitGroup

	for i := 1; i <= 5; i++ {
		wg.Add(1)
		go worker3(i, &wg)
	}

	wg.Wait()
}
