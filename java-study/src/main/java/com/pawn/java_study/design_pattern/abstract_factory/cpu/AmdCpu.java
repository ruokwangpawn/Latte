package com.pawn.java_study.design_pattern.abstract_factory.cpu;

/**
 * Created by Pawn on 2017/12/4 10.
 */

public class AmdCpu implements Cpu {

    private int pins;

    public AmdCpu(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("AMD CPU的针脚数：" + pins);
    }
}
