package com.pawn.java_study.design_pattern.abstract_factory;

import com.pawn.java_study.design_pattern.abstract_factory.factory.second.InterFactory;

/**
 * Created by Pawn on 2017/12/4 11.
 */

public class Client {

    public static void main(String[] args) {

        // first
//        ComputeEngineer1 engineer = new ComputeEngineer1();
//        engineer.makeComputer(1, 1);

        // second
        ComputeEngineer2 engineer = new ComputeEngineer2();
        engineer.makeComputer(new InterFactory());

    }
}
