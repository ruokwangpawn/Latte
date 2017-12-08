package com.pawn.java_study.design_pattern.abstract_factory.cpu;

/**
 * Created by Pawn on 2017/12/4 10.
 */

public class InterCpu implements Cpu {

    private int pins = 0;

    public InterCpu(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("Inter CPU的针脚数：" + pins);
    }
}
