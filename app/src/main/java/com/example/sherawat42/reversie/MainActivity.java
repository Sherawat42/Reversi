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
    int PLAYER_1=1;
    int PLAYER_2=2;

    private int dimensionX=8;
    private int dimensionY=8;
    private int p1Score=0;
    private int p2Score=0;
    LinearLayout scoreBoard;
    TextView p1ScoreTV;
    TextView p2ScoreTV;
    TextView turnTextView;
    private static int[][] DIRECTIONS = new int[8][2];
    boolean player1Turn = true;
    ReversieBoard gameBoard;
    private int validMoves=0;
    private int scoreTotal=0;
    TextView validMovesTextView;
    TextView TotalScoreTextView;

//    final static ReversieButton nullReversieButton = null;
    int numTargets=0;
//    Toast mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.activity_main);
        mainLayout.setBackgroundColor(Color.CYAN);
        setDirections();
        setGame();
    }

    private void setDirections() {
        DIRECTIONS[0][0] = 0;
        DIRECTIONS[1][0] = -1;
        DIRECTIONS[2][0] = -1;
        DIRECTIONS[3][0] = -1;
        DIRECTIONS[4][0] = 0;
        DIRECTIONS[5][0] = 1;
        DIRECTIONS[6][0] = 1;
        DIRECTIONS[7][0] = 1;


        DIRECTIONS[0][1] = -1;
        DIRECTIONS[1][1] = -1;
        DIRECTIONS[2][1] = 0;
        DIRECTIONS[3][1] = 1;
        DIRECTIONS[4][1] = 1;
        DIRECTIONS[5][1] = 1;
        DIRECTIONS[6][1] = 0;
        DIRECTIONS[7][1] = -1;
    }

    private void setGame() {
        mainLayout.removeAllViews();

        LinearLayout.LayoutParams params;
        scoreBoard = new LinearLayout(this);
        scoreBoard.setOrientation(LinearLayout.HORIZONTAL);
        TextView textP1 = new TextView(this);
        textP1.setText("PLAYER1: ");
        textP1.setTextSize(25);
        TextView textP2 = new TextView(this);
        textP2.setTextSize(25);
        TextView space = new TextView(this);
        space.setText("      ");
        textP2.setText("PLAYER2: ");
        p1ScoreTV = new TextView(this);
        p1ScoreTV.setText("0");
        p1ScoreTV.setTextSize(25);
        p2ScoreTV = new TextView(this);
        p2ScoreTV.setText("0");
        p2ScoreTV.setTextSize(25);
        scoreBoard.addView(textP1);
        scoreBoard.addView(p1ScoreTV);
        scoreBoard.addView(space);
        scoreBoard.addView(textP2);
        scoreBoard.addView(p2ScoreTV);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        scoreBoard.setLayoutParams(params);
        turnTextView = new TextView(this);
        turnTextView.setText("Player_1 turn:   "+player1Turn);
        mainLayout.addView(scoreBoard);
        turnTextView.setTextSize(20);
        mainLayout.addView(turnTextView);
        validMovesTextView = new TextView(this);
        validMovesTextView.setTextSize(20);
        mainLayout.addView(validMovesTextView);
        validMovesTextView.setText("Valid Moves: "+validMoves);
        TotalScoreTextView = new TextView(this);
        TotalScoreTextView.setText("Total Score: "+scoreTotal);
        mainLayout.addView(TotalScoreTextView);

        gameBoard = new ReversieBoard(this, dimensionX, dimensionY);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 5);
        gameBoard.setLayoutParams(params);
        mainLayout.addView(gameBoard);

        for(int i=0;i<dimensionX;i++){
            for(int j=0;j<dimensionY;j++){
                gameBoard.getButton(i,j).setOnClickListener(this);
                gameBoard.getButton(i,j).setPlayer(NO_PLAYER);
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
        updateScore();
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
            if(clickedButton.getPlayer() == PLAYER_1 || clickedButton.getPlayer() == PLAYER_2){
                Toast.makeText(this, "choose unmarked cell :P", Toast.LENGTH_SHORT).show();
                System.out.println(clickedButton.getPlayer());
                return;
            }


            int[][] targets = getTargets(clickedButton);
            if(targets == null){
                System.out.println("null target");
                Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show();
            }else{
                System.out.println("printing target array:");
                for(int i=0;i<targets.length;i++){
                    for(int j=0;j<3;j++){
                        System.out.print(targets[i][j]+" ");
                    }
                    System.out.println();
                }
                makeMove(clickedButton,targets);
                player1Turn = !player1Turn;
                turnTextView.setText("Player_1 turn:   "+player1Turn);
                validMoves++;
                updateScore();
            }
        }
    }

    private void updateScore(){
        p1Score=0;
        p2Score=0;
        for(int i=0;i<dimensionX;i++){
            for(int j=0;j<dimensionY;j++){
                if(gameBoard.getButton(i,j).getPlayer() == PLAYER_1){
                    p1Score++;
                    gameBoard.getButton(i,j).setBackgroundColor(Color.WHITE);
                    gameBoard.getButton(i,j).setTextColor(Color.BLACK);
                }else if(gameBoard.getButton(i,j).getPlayer() == PLAYER_2){
                    p2Score++;
                    gameBoard.getButton(i,j).setBackgroundColor(Color.BLACK);
                    gameBoard.getButton(i,j).setTextColor(Color.WHITE);
                }
            }
            p1ScoreTV.setText(p1Score+"");
            p2ScoreTV.setText(p2Score+"");
            scoreTotal = p1Score+p2Score;
            TotalScoreTextView.setText("Total Score: "+scoreTotal);

            validMovesTextView.setText("Valid Moves: "+validMoves);
        }
    }

    private void makeMove(ReversieButton clickedButton, int[][] targets) {
        int directionIndex = targets[0][2];
        int currentLocation[] = new int[2];
        for(int i=0; i<numTargets;i++){
            currentLocation[0] = clickedButton.getLocation()[0];
            currentLocation[1] = clickedButton.getLocation()[1];
            do {
                if(player1Turn){
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setPlayer(PLAYER_1);
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setText("X");


                }else{
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setPlayer(PLAYER_2);
                    gameBoard.getButton(currentLocation[0],currentLocation[1]).setText("0");
                }
                System.out.println("target:" + targets[i][0]+" "+ targets[i][1]);
                System.out.println("currX: "+currentLocation[0]+" currY: "+currentLocation[1]);
                currentLocation[0]+=DIRECTIONS[targets[i][2]][0];
                currentLocation[1]+=DIRECTIONS[targets[i][2]][1];
                System.out.println("X-inc: " +DIRECTIONS[targets[i][2]][0] + " Y-inc: " +DIRECTIONS[targets[i][2]][1]);
            }while(currentLocation[0]!=targets[i][0] || currentLocation[1]!=targets[i][1]);
            if(player1Turn){
            }
        }
    }

    private int[][] getTargets(ReversieButton clickedButton) {
        //First check the clicked button
        int[][] targetButtons = new int[8][3];
        numTargets = 0;
        for(int i=0;i<8;i++){
            // i is for direction index!
            int[] target = getPossibility(i,clickedButton);
            if(target == null){
                continue;
            }else{
                targetButtons[numTargets++] = target;
            }
        }
        if(numTargets==0){
            return null;
        }
        return targetButtons;
    }

    private int[] getPossibility(int directionIndex, ReversieButton clickedButton){
        int[] possibility = new int[3];
        int[] location1 = clickedButton.getLocation();
        int[] location = new int[2];
        location[0] = location1[0];
        location[1] = location1[1];
//        System.out.println("locArray"+ location);
//        System.out.println("loc x: "+location[0]);
//        System.out.println("loc y: "+location[1]);
        location[0]=location[0]+DIRECTIONS[directionIndex][0];
        location[1]=location[1]+DIRECTIONS[directionIndex][1];
//        System.out.println("Din : "+directionIndex);
//        System.out.println("D x: "+DIRECTIONS[directionIndex][0]);
//        System.out.println("D y: "+DIRECTIONS[directionIndex][1]);
//        System.out.println("location-x: "+location[0]);
//        System.out.println("location-y: "+location[1]);

        if(player1Turn){
            if(inBoardBounds(location, directionIndex)){
                if(!checkButton(gameBoard.getButton(location[0],location[1]),PLAYER_2)){
                    return null;
                }
            }else{
                return null;
            }
        }else{
            if(inBoardBounds(location, directionIndex)){
                if(!checkButton(gameBoard.getButton(location[0],location[1]),PLAYER_1)){
                    return null;
                }
            }else{
                return null;
            }
        }
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
                }else{
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
