package com.example.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.stream.*;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //activePlayer 0 = circle, 1 = cross
    //gameState 2 = empty

    int activePlayer = 1;
    int winningPositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int[] gameState ={2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;
    boolean state = true;

    public void click(View view) {
        for (int a=0; a<gameState.length; a++){
            if(gameState[a]==2){
                state = true;
                break;
            }else{
                state=false;
            }
        }

                if(state) {
                    ImageView img = (ImageView) view;
                    int tappedCounter = Integer.parseInt(img.getTag().toString());
                    if (gameState[tappedCounter] == 2 && gameActive) {
                        gameState[tappedCounter] = activePlayer;
                        img.setTranslationY(-1500);
                        if (activePlayer == 1) {
                            img.setImageResource(R.drawable.cross);
                            activePlayer = 0;
                        } else {
                            img.setImageResource(R.drawable.circle);
                            activePlayer = 1;
                        }
                        img.animate().translationYBy(1500).setDuration(400);

                        for (int[] winningPosition : winningPositions) {
                            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                                String winner = "";
                                if (activePlayer == 0) {
                                    winner = "Cross";
                                } else {
                                    winner = "Circle";
                                }
                                gameActive = false;
                                ImageView playAgainButton = (ImageView) findViewById(R.id.playAgain);
                                TextView winnerTextView = (TextView) findViewById(R.id.textview);
                                winnerTextView.setText(winner + " has Won!");
                                playAgainButton.setVisibility(View.VISIBLE);
                                winnerTextView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                } else {
                    ImageView playAgainButton = (ImageView) findViewById(R.id.playAgain);
                    playAgainButton.setVisibility(View.VISIBLE);
                    TextView winnerTextView = (TextView) findViewById(R.id.textview);
                    winnerTextView.setText("It's a tie!");
                    winnerTextView.setVisibility(View.VISIBLE);
                }
    }
    public void playAgain(View view){
        ImageView playAgainButton = (ImageView) findViewById(R.id.playAgain);
        TextView winnerTextView = (TextView) findViewById(R.id.textview);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0 ; i<gridLayout.getChildCount();i++){
            ImageView img = (ImageView) gridLayout.getChildAt(i);
            img.setImageDrawable(null);
        }

        for(int i=0; i<gameState.length;i++){
            gameState[i] = 2;
        }
        activePlayer = 1;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}