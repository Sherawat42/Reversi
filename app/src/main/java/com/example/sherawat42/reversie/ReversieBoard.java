package com.example.sherawat42.reversie;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by sherawat42 on 28/1/17.
 */

public final class ReversieBoard {
    public ReversieBoard(int dimensionX,int dimensionY,Context context){
        gameButtons = new ReversieButton[dimensionX][dimensionY];
        LinearLayout boardRows;
        for(int i=0;i<dimensionX;i++){
            gameButtons[i] = new ReversieButton[dimensionY];
        }
    }
    ReversieButton[][] gameButtons;
}
