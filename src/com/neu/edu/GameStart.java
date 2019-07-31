package com.neu.edu;

import com.neu.edu.main.GameFrame;
import com.neu.edu.util.DataStore;

public class GameStart {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        DataStore.put("gameFrame", gameFrame);
        gameFrame.init();
    }
}
