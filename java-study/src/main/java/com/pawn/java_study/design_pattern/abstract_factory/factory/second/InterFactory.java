package com.pawn.java_study.design_pattern.abstract_factory.factory.second;

import com.pawn.java_study.design_pattern.abstract_factory.cpu.Cpu;
import com.pawn.java_study.design_pattern.abstract_factory.cpu.InterCpu;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.InterMainBoard;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.MainBoard;

/**
 * Created by Pawn on 2017/12/4 14.
 */

public class InterFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        return new InterCpu(755);
    }

    @Override
    public MainBoard createMainboard() {
        return new InterMainBoard(755);
    }
}
