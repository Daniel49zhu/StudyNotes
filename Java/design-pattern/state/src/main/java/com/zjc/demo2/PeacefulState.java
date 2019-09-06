package com.zjc.demo2;

public class PeacefulState implements State {

    private Mammoth mammoth;


    public PeacefulState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        System.out.printf("%s is calm and peaceful.\n", mammoth);
    }

    @Override
    public void observe() {
        System.out.printf("%s calms down.\n", mammoth);
    }
}
