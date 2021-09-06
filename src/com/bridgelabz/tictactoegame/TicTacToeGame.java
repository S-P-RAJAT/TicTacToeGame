package com.bridgelabz.tictactoegame;

import java.util.Scanner;

public class TicTacToeGame {
	static char board[];
	static final Scanner scanner = new Scanner(System.in);
	static char userLetter, computerLetter;
	static int userNumber;

	public static void main(String[] args) {

		System.out.println("Welcome to TicTacToe Game!");
		createBoard();
		userLetter = getInput();
		computerLetter = userLetter == 'X' ? 'O' : 'X';
		displayBoard();
		moveIfLocationValid();
		if(tossingOutcome()) {
			System.out.println("Won Toss! User plays first.");
		} else {
			System.out.println("Lost Toss! Computer plays first.");
		}
	}

	public static void createBoard() {

		board = new char[10];
		for (int index = 1; index < board.length; index++)
			board[index] = ' ';
	}

	public static char getInput() {

		System.out.print("Enter the letter (X or O): ");
		return scanner.next().toUpperCase().charAt(0);
	}

	public static void displayBoard() {
		
		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3 ; columnIndex++) {
			System.out.print("| " + board[(rowIndex*3)+columnIndex+1]+" ");
			}
			System.out.println("| ");
		}
	}
	public static void move() {
		System.out.print("\nEnter a location on board to make the mark (1-9): ");
		userNumber = scanner.nextInt();
		scanner.nextLine();
		if (userNumber < 1 || userNumber > 9) {
			displayBoard();
			System.out.println("Your input is Invalid");
			move();
		}
	}
	public static void moveIfLocationValid() {
		move();
		if (board[userNumber] == 'X' || board[userNumber] == 'O') {
			displayBoard();
			System.out.println(userNumber+"Number which is selected is not free");
			moveIfLocationValid();
		} else {
			board[userNumber] = userLetter;
			System.out.println(userLetter + " is marked at location " + userNumber);
			displayBoard(); 
			checkWinner();
		}
		
	}
	public static boolean tossingOutcome() {
		System.out.print("\nEnter heads (0) or tails (1): ");
		return (scanner.nextInt()==(int)(Math.random()*2)); 
		
	}
	public static String checkWinner() {
		int winningStates[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
		for (int state = 0; state < winningStates.length; state++) {
			String line =  "";
			for (int index = 0; index < 3; index++)
				line += board[winningStates[state][index]];
			if (line.equals(Character.toString(userLetter).repeat(3))) {
                return "User";
            }
            else if (line.equals(Character.toString(computerLetter).repeat(3))) {
                return "Computer";
            }
		}
		for (int index = 1; index < 10; index++) {
            if (board[index]==' ') {
        		return null;
            }
            else if (index == 9) {
                return "draw";
            }
        }
		return null;

	}
}
