/* Name: Brock Rossman
# Date: 4/23/2025
# Class: CSC1120
# Pledge: I have neither given nor received unauthorized aid on this program.
# Description: RPSLS is a program that allows users to play the original RPS but
now with cooler gestures.
# Input: a text file containing datasets with coordinates to a bunch of throws at a dartboard
# Output:The program will display the winner after each round of play.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class DartThrow
{
    private int p1score;
    private int p2score;

    private Double[][] xVals;
    private Double[][] yVals;

    private Vector<String> rounds;



    public DartThrow(String fileName) throws FileNotFoundException
    {
        //fileName = "/Users/brockrossman/Desktop/Coding Projects/Bullseye/coordinates.txt";
        Scanner scan = new Scanner(new File(fileName));
        rounds = new Vector<String>();

        // Reads the file and stores it to a vector
        while(scan.hasNext())
        {

            rounds.add(scan.nextLine());

        }

        xVals = new Double[rounds.size()][6];
        yVals = new Double[rounds.size()][6];

        int r = 0;

        // Iterates over each line given in the text file
        for(String round : rounds)
        {

            int i = 0;

            String[] tosses = round.split(" ");

            // Iterates over each double in each line and then stores it
            for(String toss : tosses)
            {
                // If statement to determine if the coordinate is an x or a y value
                if(i % 2 == 0) {
                    xVals[r][i / 2] = Double.parseDouble(toss);
                }
                else {
                    yVals[r][i / 2] = Double.parseDouble(toss);
                }
                i += 1;
            }
            r += 1;
        }

    }

    // Prints the results of the games
    public void printGame()
    {
        // iterates over each game
        for(int i = 0; i < rounds.size(); i++)
        {
            int win = winner(i); // Finds the winner

            // Prints score every time
            System.out.print("Score: " + getP1score() + " to " + getP2score() + ", ");

            // Printes result based on the return of winner;
            if(win == 0)
            {
                System.out.print("Tie");
            }
            else
            {
                System.out.print("PLAYER " + win + " WINS");
            }

            System.out.println();
        }
    }

    // Returns the winner based on the round. 1 means that player 1 won, 2 for player 2, and 0 for a tie
    public int winner(int round)
    {

        getPoints(xVals[round], yVals[round]);

        // If statement to determine what gets returned
        if(getP1score() > getP2score())
        {
            return 1;
        }
        else if(getP1score() < getP2score()) {
            return 2;
        }
        else
        {
            return 0;
        }
    }

    // Gets the points for all the tosses in a round
    public  void getPoints(Double[] xVals, Double[] yVals)
    {
        p1score = 0;
        p2score = 0;

        boolean p1 = true;

        for(int i = 0; i < xVals.length; i++)
        {
            if(p1) {
                p1score += score(xVals[i], yVals[i]);
            }
            else {
                p2score += score(xVals[i], yVals[i]);
            }

            // Changes from adding to player 1's score to player 2's every 3 sets of x and y values
            if((i + 1) % 3 == 0)
            {
                p1 = !p1;
            }
        }
    }

    // Returns player 1's score
    public int getP1score()
    {
        return p1score;
    }

    // Function that returns player 2's score
    public int getP2score()
    {
        return p2score;
    }

    // Function takes an X and a Y and finds how many points it is worth
    public  int score(double x, double y)
    {
        int score = 0;

        // determines radius from center
        double r = Math.sqrt((x * x) + (y * y));

        if(r <= 15)
        {
            score = 20;
            if(r <= 12)
            {
                score = 40;
                if(r <= 9)
                {
                    score = 60;
                    if(r <= 6)
                    {
                        score = 80;
                        if(r <= 3)
                        {
                            score = 100;
                        }
                    }
                }
            }
        }
        return score;
    }
}
