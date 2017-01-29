package com.example.sherawat42.reversie;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout mainLayout;

    int NO_PLAYER=0;
    int PLAYER_1=0;
    int PLAYER_2=0;

    private int dimensionX=8;
    private int dimensionY=8;
    private int p1Score=0;
    private int p2Score=0;
    TextView p1ScoreTV;
    TextView p2ScoreTV;
    private static int[][] DIRECTIONS = new int[8][2];
    boolean player1Turn = true;
    ReversieBoard gameBoard;
    final static ReversieButton nullReversieButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.activity_main);
        mainLayout.setBackgroundColor(Color.CYAN);
        setDirections();
        setBoard();
    }

    private void setDirections() {

        DIRECTIONS[0][0] = -1;
        DIRECTIONS[1][0] = -1;
        DIRECTIONS[2][0] = -1;
        DIRECTIONS[3][0] = 0;
        DIRECTIONS[4][0] = 1;
        DIRECTIONS[5][0] = 1;
        DIRECTIONS[6][0] = 1;
        DIRECTIONS[7][0] = 0;

        DIRECTIONS[0][1] = -1;
        DIRECTIONS[1][1] = 0;
        DIRECTIONS[2][1] = 1;
        DIRECTIONS[3][1] = 1;
        DIRECTIONS[4][1] = 1;
        DIRECTIONS[5][1] = 0;
        DIRECTIONS[6][1] = -1;
        DIRECTIONS[7][1] = -1;


    }

    private void setBoard() {
        mainLayout.removeAllViews();

        LinearLayout.LayoutParams params;

        LinearLayout scoreBoard = new LinearLayout(this);
        scoreBoard.setOrientation(LinearLayout.HORIZONTAL);
        TextView textP1 = new TextView(this);
        textP1.setText("PLAYER1: ");
        TextView textP2 = new TextView(this);
        TextView space = new TextView(this);
        space.setText("      ");
        textP2.setText("PLAYER2: ");
        p1ScoreTV = new TextView(this);
        p1ScoreTV.setText("0");
        p2ScoreTV = new TextView(this);
        p2ScoreTV.setText("0");
        scoreBoard.addView(textP1);
        scoreBoard.addView(p1ScoreTV);
        scoreBoard.addView(space);
        scoreBoard.addView(textP2);
        scoreBoard.addView(p2ScoreTV);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        scoreBoard.setLayoutParams(params);
        mainLayout.addView(scoreBoard);

        gameBoard = new ReversieBoard(this, dimensionX, dimensionY);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3);
        gameBoard.setLayoutParams(params);
        mainLayout.addView(gameBoard);

        for(int i=0;i<dimensionX;i++){
            for(int j=0;j<dimensionY;j++){
                gameBoard.getButton(i,j).setOnClickListener(this);
            }
        }
        gameBoard.getButton(3,3).setPlayer(PLAYER_1);
        gameBoard.getButton(3,3).setText("X");
        gameBoard.getButton(4,4).setPlayer(PLAYER_1);
        gameBoard.getButton(4,4).setText("X");

        gameBoard.getButton(4,3).setPlayer(PLAYER_2);
        gameBoard.getButton(4,3).setText("0");
        gameBoard.getButton(3,4).setPlayer(PLAYER_2);
        gameBoard.getButton(3,4).setText("0");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(false){

        }else if(false){

        }else{
            ReversieButton clickedButton = (ReversieButton)v;
//            System.out.println("here");
            if(clickedButton.getPlayer() != NO_PLAYER){
                Toast.makeText(this, "choose unmarked cell :P", Toast.LENGTH_SHORT).show();
                return;
            }
            clickedButton.setBackgroundColor(Color.WHITE);
            int[][] targets = getTargets(clickedButton);
            if(targets == null){
                Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show();
            }else{
                makeMove(clickedButton,targets);
            }
        }
        player1Turn = !player1Turn;
    }

    private void makeMove(ReversieButton clickedButton, int[][] targets) {
        int directionIndex = targets[0][2];
        int currentLocation[];
        for(int i=0; i<targets.length;i++){
            currentLocation = clickedButton.getLocation();
            do {
                if(player1Turn){
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setPlayer(PLAYER_1);
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setText("X");
                }else{
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setPlayer(PLAYER_2);
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setText("0");
                }
                currentLocation[0]+=DIRECTIONS[i][0];
                currentLocation[1]+=DIRECTIONS[i][1];

            }while(currentLocation[0]==targets[i][0] && currentLocation[1]==targets[i][1]);
        }
    }

    private int[][] getTargets(ReversieButton clickedButton) {
        //First check the clicked button
        int[][] targetButtons = new int[8][3];
        int targetButtonsFillIndex = 0;
        for(int i=0;i<8;i++){
            int[] target = getPossibility(i,clickedButton);
            if(target == null){
                continue;
            }else{
                targetButtons[targetButtonsFillIndex++] = target;
            }
        }
        return targetButtons;
    }

    private int[] getPossibility(int directionIndex, ReversieButton clickedButton){
        int[] possibility = new int[3];
        int[] location = clickedButton.getLocation();
        if(player1Turn){
            if(inBoardBounds(location, directionIndex)){
                if(!checkButton(gameBoard.getButton(location[0]+DIRECTIONS[directionIndex][0],location[1]+DIRECTIONS[directionIndex][1]),PLAYER_2)){
                    return null;
                }
            }else{
                return null;
            }
        }else{
            if(inBoardBounds(location,directionIndex)){
                if(!checkButton(gameBoard.getButton(location[0]+DIRECTIONS[directionIndex][0],location[1]+DIRECTIONS[directionIndex][1]),PLAYER_1)){
                    return null;
                }
            }else{
                return null;
            }
        }
        location[0]=location[0]+DIRECTIONS[directionIndex][0];
        location[1]=location[1]+DIRECTIONS[directionIndex][1];
        while(true){
            if(player1Turn){
                if(checkButton(gameBoard.getButton(location[0],location[1]),PLAYER_1)){
                    possibility[0] = location[0];
                    possibility[1] = location[1];
                    possibility[2] = directionIndex;
                    return possibility;
                }
            }else{
                if(checkButton(gameBoard.getButton(location[0],location[1]),PLAYER_2)){
                    possibility[0] = location[0];
                    possibility[1] = location[1];
                    possibility[2] = directionIndex;
                    return possibility;
                }
            }

            if(!inBoardBounds(location, directionIndex)){
                return null;
            }
            location[0]=location[0]+DIRECTIONS[directionIndex][0];
            location[1]=location[1]+DIRECTIONS[directionIndex][1];
        }
    }
    private boolean checkButton(ReversieButton buttonToBeChecked, int player){



//        float tempX = index/dimensionX;
//        int x;
//        if((int)tempX != 0){
//            x = ((int)tempX);
//        }else{
//            x = (int)tempX;
//        }
//        int y = (index-1)%dimensionY;





//                                                            CHECK THE CODE BELOW
        if(player == NO_PLAYER){
            if(buttonToBeChecked.getPlayer() == NO_PLAYER){
               return true;
            }else{
                return false;
            }
        }else{
            if(player == PLAYER_1){
                if(buttonToBeChecked.getPlayer() == PLAYER_1){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(buttonToBeChecked.getPlayer() == PLAYER_2){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

    }

    public boolean inBoardBounds(int[] buttonLocation, int directionIndex){
        int x = buttonLocation[0]+DIRECTIONS[directionIndex][0];
        int y = buttonLocation[1]+DIRECTIONS[directionIndex][1];
        if(x>=0 && x<dimensionX && y>=0 && y<dimensionY){
            return true;
        }else{
            return false;
        }
    }
}
