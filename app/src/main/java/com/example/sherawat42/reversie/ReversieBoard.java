package com.example.sherawat42.reversie;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by sherawat42 on 28/1/17.
 */

public final class ReversieBoard extends LinearLayout{
    public ReversieBoard(Context context, int dimensionX,int dimensionY){
        super(context);

        boardRows = new LinearLayout[dimensionX];
        gameButtons = new ReversieButton[dimensionX][dimensionY];
        for(int i=0;i<dimensionX;i++){
            boardRows[i] = new LinearLayout(context);
            gameButtons[i] = new ReversieButton[dimensionY];
        }
    }
    private ReversieButton[][] gameButtons;
    public ReversieButton getButton(int x, int y){
        return gameButtons[x][y];
    }
    private LinearLayout boardRows[];
}
