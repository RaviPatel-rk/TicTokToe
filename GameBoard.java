package com.main.tictoktoe;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameBoard {

	char[][]board;
	int boardSize;
	Queue<Player>nexTturn;
	Scanner input;
	
	public GameBoard(int boardSize,Player[]players) {
		this.boardSize = boardSize;
		this.board = new char[2*boardSize-1][2*boardSize-1];
		initialise(board);
		nexTturn = new LinkedList<>();
		Arrays.stream(players).forEach(player->nexTturn.offer(player));
		input = new Scanner(System.in);
		
	}

	private void initialise(char[][] board) {
		for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i % 2 == 0 && j % 2 != 0) board[i][j] = '|';
                if (i % 2 != 0 && j % 2 == 0) board[i][j] = '-';
                if (i % 2 != 0 && j % 2 != 0) board[i][j] = '+';
            }
        }
		
	}
	
	public void printBoard() {
		for (char[] row : this.board) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
	}
	
	public void startGame() {
		int noOfMoves=0;
		printBoard();
		while(true) {
			noOfMoves++;
			if(noOfMoves==(boardSize*boardSize)+1) {
				System.out.println("match draw ");
				break;
			}
			
			Player p = nexTturn.poll();
			int position = getUserInput(p);
			int row = getRow(position);
			int col = getCol(position);
			board[row][col] = p.getSymbol();
			System.out.println("Board after the move ");
			printBoard();
			if(noOfMoves>=boardSize && checkEndGame(p,row,col))break;
			nexTturn.offer(p);
		}
	}

	private boolean checkEndGame(Player p, int row, int col) {
		String winString = ""; // 000 or XXX
        for (int i = 0; i < boardSize; i++) {
            winString += String.valueOf(p.getSymbol());
        }
        String rowString = "";
        String colString = "";
        String diagonalString = "";
        String reverseDiagonalString = "";
        for (int i = 0; i < board.length; i = i + 2) {
            rowString += board[row][i];
            colString += board[i][col];
            if (row == col) {
                diagonalString += board[i][i];
            }
            if (row + col == board.length - 1) {
                reverseDiagonalString += board[board.length - i - 1][i];
            }
        }
        if (winString.equals(rowString) || winString.equals(colString) || winString.equals(diagonalString)
                || winString.equals(reverseDiagonalString)) {
            System.out.println(p.getPlayerName() + " won the game");
            return true;
        }
        return false;
	}

	private int getCol(int position) {
		 return 2 * ((position % boardSize == 0 ? boardSize : position % boardSize) - 1);
	}

	private int getRow(int position) {
		 return 2 * ((position % boardSize == 0) ? (position / boardSize) - 1 : position / boardSize);
	}

	private int getUserInput(Player p) {
		System.out.println(p.getPlayerName() +
                " Please Enter a number between 1 - " + (boardSize * boardSize));
        int val = input.nextInt();
        while (!validateInput(val)) {
//            printBoard();
            System.out.println("Wrong Position - " + p.getPlayerName() +
                    " Please Enter a number between 1 - " + (boardSize * boardSize));
            val = input.nextInt();
        }
        return val;
	}

	private boolean validateInput(int val) {
		if (val < 1 || val > (boardSize * boardSize)) return false;
        int row = getRow(val);
        int col = getCol(val);
        if ((int) board[row][col] != 0) return false;
        return true;
	}
}
