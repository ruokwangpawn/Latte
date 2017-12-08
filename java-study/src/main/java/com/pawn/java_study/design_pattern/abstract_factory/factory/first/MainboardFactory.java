package com.pawn.java_study.design_pattern.abstract_factory.factory.first;

import com.pawn.java_study.design_pattern.abstract_factory.mainboard.AmdMainBoard;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.InterMainBoard;
import com.pawn.java_study.design_pattern.abstract_factory.mainboard.MainBoard;

/**
 * Created by Pawn on 2017/12/4 11.
 */

public class MainboardFactory {

    public static MainBoard createMainboard(int type) {
        MainBoard mainBoard = null;
        if (type == 1) {
            mainBoard = new InterMainBoard(755);
        } else if (type == 2) {
            mainBoard = new AmdMainBoard(938);
        }
        return mainBoard;
    }
}
