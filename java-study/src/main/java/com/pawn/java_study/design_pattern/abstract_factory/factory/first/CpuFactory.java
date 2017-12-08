package com.pawn.java_study.design_pattern.abstract_factory.factory.first;

import com.pawn.java_study.design_pattern.abstract_factory.cpu.AmdCpu;
import com.pawn.java_study.design_pattern.abstract_factory.cpu.Cpu;
import com.pawn.java_study.design_pattern.abstract_factory.cpu.InterCpu;

/**
 * Created by Pawn on 2017/12/4 10.
 */

public class CpuFactory {

    public static Cpu createCpu(int type) {
        Cpu cpu = null;
        if (type == 1) {
            cpu = new InterCpu(755);
        } else if (type == 2) {
            cpu = new AmdCpu(938);
        }
        return cpu;
    }
}
