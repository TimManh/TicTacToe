package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	//Set empty string for winner and yellowchip to 1
    String winner = "";
    int yellow_turn = 1;
    int [] gameState={2,2,2,2,2,2,2,2,2};
	//Declare with pattern of square will the game be finished
    int [][]winning = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean finish = false;
	//Function for playing activity
    public void play(View view){
		//Declare the image view for the chip
        ImageView chip = (ImageView) view;
		//get tag when ever user press a square
        int tappedCount = Integer.parseInt( chip.getTag().toString());
		//Check if gameState is at 2 mean nothing there and winner is an empty String. Then the game can be played.
        if(gameState[tappedCount]==2 && winner=="") {
            gameState[tappedCount] = yellow_turn;
			//Check the value of yellow if it is one set the source image is yellowchip
            if (yellow_turn == 1) {
                chip.setImageResource(R.drawable.yellowchip);
                yellow_turn = 0;
            
			}//else set the chip to redchip 
			else {
                chip.setImageResource(R.drawable.redchip);
                yellow_turn = 1;
            }
			//Create rotation animation for the chip
            chip.animate().rotation(360).setDuration(1000);
			//This loop check the winning position after each time the game is player
            for (int[] winning : winning) {
                if (gameState[winning[0]] == gameState[winning[1]] && gameState[winning[1]] == gameState[winning[2]] && gameState[winning[0]] != 2) {
                    if (yellow_turn == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
					//Print out the the screen who is the winner
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
					//The playAgain button and text are shown to ask for the user to play another game
                    Button button = (Button) findViewById(R.id.button);
                    TextView playAgain = (TextView) findViewById(R.id.playAgain);
                    button.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }

            }
        }
    }
	// function to restart the game
    public void restart(View view){
        Button button = (Button) findViewById(R.id.button);
        TextView playAgain = (TextView) findViewById(R.id.playAgain);
		//Make the button and text for playAgain to be invisible
        button.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
		//clear each square in the game, and set everything back to beginning state
        for(int i=0; i<gridLayout.getChildCount();i++){
            ImageView chip = (ImageView) gridLayout.getChildAt(i);
            chip.setImageDrawable(null);
        }
        for(int i =0; i<gameState.length;i++){
            gameState[i] = 2;
        }
        winner ="";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
