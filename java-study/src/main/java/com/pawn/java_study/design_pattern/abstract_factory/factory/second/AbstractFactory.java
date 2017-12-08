package com.pawn.java_study.design_pattern.abstract_factory.factory.second;

import com.pawn.java_study.design_pattern.abstract_factory.cpu.Cpu;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.MainBoard;

/**
 * Created by Pawn on 2017/12/4 14.
 */

public interface AbstractFactory {

    Cpu createCpu();

    MainBoard createMainboard();
}
