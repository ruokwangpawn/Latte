package com.pawn.java_study.design_pattern.abstract_factory.mainboard;

/**
 * Created by Pawn on 2017/12/4 10.
 */

public class AmdMainBoard implements MainBoard {

    private int cpuHoles;

    public AmdMainBoard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("AMD主板的CPU插槽孔数是：" + cpuHoles);
    }
}
