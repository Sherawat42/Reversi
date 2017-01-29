package com.example.sherawat42.reversie;

import android.content.Context;
import android.widget.Button;

/**
 * Created by sherawat42 on 28/1/17.
 */

public class ReversieButton extends Button {
    public ReversieButton(Context context, int[] location) {
        super(context);
        this.location = location;
    }
    private int player;
    private final int[] location;

    public int[] getLocation(){
        return location;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
