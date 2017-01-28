package com.example.sherawat42.reversie;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by sherawat42 on 28/1/17.
 */

public final class ReversieBoard extends LinearLayout{
    public ReversieBoard(Context context, int dimensionX, int dimensionY){
        super(context);
        this.setOrientation(VERTICAL);
        rows = new LinearLayout[dimensionX];
        buttons = new ReversieButton[dimensionX][dimensionY];
        LayoutParams paramsRow = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
//        paramsRow.setMargins(2,0,0,2);
        LayoutParams paramsButton = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
//        paramsButton.setMargins(2,2,2,2);
        for(int i=0; i<dimensionX;i++){
            rows[i] = new LinearLayout(context);
            rows[i].setLayoutParams(paramsRow);
            rows[i].setOrientation(HORIZONTAL);
            for(int j=0;j<dimensionY;j++){
                buttons[i][j] = new ReversieButton(context);
                buttons[i][j].setPlayer(0);
                buttons[i][j].setText("");
                buttons[i][j].setLayoutParams(paramsButton);
                rows[i].addView(buttons[i][j]);
            }
            this.addView(rows[i]);  // TRY writtig this line at the starting of the above for loop
        }

    }
    private ReversieButton[][] buttons;
    private LinearLayout[] rows;

    public ReversieButton getButtons(int x, int y) {
        return buttons[x][y];
    }
}
