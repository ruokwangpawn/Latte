package com.pawn.java_study.design_pattern.abstract_factory.mainboard;

/**
 * Created by Pawn on 2017/12/4 10.
 */

public class InterMainBoard implements MainBoard {

    private int cpuHoles = 0;

    public InterMainBoard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("Intel主板的CPu插槽孔数是：" + cpuHoles);
    }
}
