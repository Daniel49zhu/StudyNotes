package com.zjc;
 
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 线程多对多交替打印
 * @ClassName: ManyToManyPrint  
 * @Description:
 * @author freeflying
 * @date 2018年8月20日
 */
public class ManyToManyPrint {
	public static void main(String[] args) {
		ManyToManyPrintEx printEx=new ManyToManyPrintEx();
		ManyToManyPrintThreadA[] threadA = new ManyToManyPrintThreadA[10];
		ManyToManyPrintThreadB[] threadB=new ManyToManyPrintThreadB[10];
		for (int i = 0; i < 10; i++) {
			threadA[i]=new ManyToManyPrintThreadA(printEx);
			threadB[i]=new ManyToManyPrintThreadB(printEx);
			threadA[i].start();
			threadB[i].start();
		}
	}
}
class ManyToManyPrintEx{
	private ReentrantLock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	private boolean hasValue=false;
	public void set() {
		try {
			lock.lock();
			while(hasValue==true) {
				System.out.println("*");
				condition.await();
			}
			hasValue=true;
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	public void get() {
		try {
			lock.lock();
			while(hasValue==false) {
				System.out.println("-");
				condition.await();
			}
			hasValue=false;
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
}
class ManyToManyPrintThreadA extends Thread{
	private ManyToManyPrintEx printEx;
 
	public ManyToManyPrintThreadA(ManyToManyPrintEx printEx) {
		super();
		this.printEx = printEx;
	}
	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			printEx.set();
		}
	}
}
class ManyToManyPrintThreadB extends Thread{
	private ManyToManyPrintEx printEx;
 
	public ManyToManyPrintThreadB(ManyToManyPrintEx printEx) {
		super();
		this.printEx = printEx;
	}
	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			printEx.get();
		}
	}
}