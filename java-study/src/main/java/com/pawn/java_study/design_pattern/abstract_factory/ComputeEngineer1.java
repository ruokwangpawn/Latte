package com.pawn.java_study.design_pattern.abstract_factory;

import com.pawn.java_study.design_pattern.abstract_factory.cpu.Cpu;
import com.pawn.java_study.design_pattern.abstract_factory.factory.first.CpuFactory;
import com.pawn.java_study.design_pattern.abstract_factory.factory.first.MainboardFactory;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.MainBoard;

/**
 * Created by Pawn on 2017/12/4 11.
 */

public class ComputeEngineer1 {

    private Cpu cpu = null;
    private MainBoard mainBoard = null;

    public void makeComputer(int cpuType, int mainboardType) {
        prepareHardwares(cpuType, mainboardType);
    }

    private void prepareHardwares(int cpuType, int mainboardType) {
        this.cpu = CpuFactory.createCpu(cpuType);
        this.mainBoard = MainboardFactory.createMainboard(mainboardType);

        this.cpu.calculate();
        this.mainBoard.installCPU();
    }
}
