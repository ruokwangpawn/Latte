package com.pawn.java_study.design_pattern.abstract_factory;

import com.pawn.java_study.design_pattern.abstract_factory.cpu.Cpu;
import com.pawn.java_study.design_pattern.abstract_factory.factory.second.AbstractFactory;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.MainBoard;

/**
 * Created by Pawn on 2017/12/4 14.
 */

public class ComputeEngineer2 {

    private Cpu cpu = null;
    private MainBoard mainBoard = null;

    public void makeComputer(AbstractFactory factory) {

        prepareHardwares(factory);
    }

    private void prepareHardwares(AbstractFactory factory) {
        this.cpu = factory.createCpu();
        this.mainBoard = factory.createMainboard();

        this.cpu.calculate();
        this.mainBoard.installCPU();
    }
}
