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

    private int currentPlayer = 0;
    private int countTurns = 0;
    private int playerWon;
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
//        int playerTurn;
//        if (currentPlayer == 0)
//        {
//            playerTurn = 0;
//        } else if (currentPlayer == 1)
//        {
//            playerTurn = 1;
//        }
        return (currentPlayer);
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    public boolean play(int col, int row)
    {
//        if (isGameOver() == false)
//        {
//            return false;
//        }
        if (gameBoard[col][row] == -1 && isGameOver() == false)
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
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
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
