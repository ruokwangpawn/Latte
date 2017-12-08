package com.pawn.java_study.design_pattern.abstract_factory.factory.second;

import com.pawn.java_study.design_pattern.abstract_factory.cpu.AmdCpu;
import com.pawn.java_study.design_pattern.abstract_factory.cpu.Cpu;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.AmdMainBoard;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.MainBoard;

/**
 * Created by Pawn on 2017/12/4 14.
 */

public class AmdFactory implements AbstractFactory {
    @Override
    public Cpu createCpu() {
        return new AmdCpu(938);
    }

    @Override
    public MainBoard createMainboard() {
        return new AmdMainBoard(938);
    }
}
