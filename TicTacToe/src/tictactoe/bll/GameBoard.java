/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{

    private int currentPlayer = 0; // The int Id for the current player (0/1).
    private int countTurns = 0; // counts the turns taken.
    private int playerWon; // is given the winning players int Id as value.
    // gameBoard initializes the gameboard in a 3x3 two-dimensional array with the values -1.
    private int[][] gameBoard = 
    {
        {
            -1, -1, -1
        },
        {
            -1, -1, -1
        },
        {
            -1, -1, -1
        }
    };

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        return (currentPlayer);
    }

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     * 
     * If play attempt is successful, counts the play as 1 turn(countTurn).
     * If play attempt is successful, adds player's number to the corresponding
     * gameBoard multi-array to keep track of X/O's.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If isGameOver ==
     * true this method will always return false.
     */
    public boolean play(int col, int row)
    {
        if (isGameOver() == true)
        {
            return false;
        }
        else if (gameBoard[col][row] == -1 && isGameOver() == false)
        {
            if (currentPlayer == 0)
            {
                gameBoard[col][row] = 0;
                currentPlayer = 1;
                countTurns++;
                return true;
            } else if (currentPlayer == 1)
            {
                gameBoard[col][row] = 1;
                currentPlayer = 0;
                countTurns++;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the board for a winner, if either player meets the conditions for
     * winning or 9 turns has passed(meaning the board is full) it will tell us
     * that the game has ended. Also if a player wins, sets playerWon = player's int id.
     * 
     * @return true if the board is full or a player meets a win condition, else
     * it will return false.
     */
    public boolean isGameOver()
    {
        for (int j = 0; j < gameBoard.length; j++)
        {
            if (gameBoard[j][0] != -1 && gameBoard[j][0] == gameBoard[j][1] && gameBoard[j][1] == gameBoard[j][2])
            {
                playerWon = gameBoard[j][0];
                return true;
            } else if (gameBoard[0][j] != -1 && gameBoard[0][j] == gameBoard[1][j] && gameBoard[1][j] == gameBoard[2][j])
            {
                playerWon = gameBoard[0][j];
                return true;
            } 
            else if (gameBoard[0][0] != -1 && gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2])
            {
                playerWon = gameBoard[0][0];
                return true;
            } 
            else if (gameBoard[0][2] != -1 && gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1]==gameBoard[2][0])
            {
                playerWon = gameBoard[0][2];
                return true;
            }
        }
        if (countTurns == 9)
        {
            playerWon = -1;
            return true;
        }
        return false;

    }

    /**
     * Gets the Id of the winner, -1 if its a draw.
     *
     * @return int Id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        return playerWon;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        currentPlayer = 0;
        countTurns = 0;
        gameBoard = new int[][]
        {
            {
                -1, -1, -1
            },
            {
                -1, -1, -1
            },
            {
                -1, -1, -1
            }
        };
    }

}
