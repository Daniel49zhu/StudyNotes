package com.zjc.demo1;

public class HasQuarterState implements State {
    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("钱多烧得晃是不？你特么不给给钱了吗？！！");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("好吧，钱退给你");
        //退币后修改为无币状态
        this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("波动了开关");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if(gumballMachine.getCount()>0){
            this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
        }else{
            System.out.println("妈蛋，糖果卖完了！！！");
            this.gumballMachine.setState(this.gumballMachine.getSoldOutState());
        }
    }
}
