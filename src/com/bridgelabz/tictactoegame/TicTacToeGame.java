package com.bridgelabz.tictactoegame;

import java.util.Iterator;
import java.util.Scanner;

public class TicTacToeGame {
	static char board[];
	static final Scanner scanner = new Scanner(System.in);
	static char userLetter, computerLetter;
	static int userNumber;
	static boolean moves[];
	static boolean moveFound = false;
	static int winningStates[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 },
			{ 1, 5, 9 }, { 3, 5, 7 } };

	public static void main(String[] args) {

		System.out.println("Welcome to TicTacToe Game!");
		createBoard();
		userLetter = getInput();
		computerLetter = userLetter == 'X' ? 'O' : 'X';
		displayBoard();
		if (tossingOutcome()) {
			System.out.println("Won Toss! User plays first.");
		} else {
			System.out.println("Lost Toss! Computer plays first.");
		}

		while (true) {
			playersTurn();
			if (checkWinner() != null) {
				break;
			}
			computersTurn();
			if (checkWinner() != null) {
				break;
			}
		}

	}

	public static void playersTurn() {
		moveIfLocationValid();

	}

	public static void computersTurn() {
		winningMove(userLetter);
		moveFound = setComputerMove();
		if (moveFound == false) {
			blockingMove();
			moveFound = setComputerMove();
			if (moveFound == false) {
				nextPossibleMoves();
				moveFound = setComputerMove();
				if (moveFound == false) {
					nextPossibleCentreMove();
					moveFound = setComputerMove();
					if (moveFound == false) {
						nextPossibleRemainingMoves();
					}
				}
			}
		}
		System.out.println(computerLetter + " is marked at location " + userNumber);
		displayBoard();
		resetMoves();
	}

	public static void resetMoves() {

		for (int i = 0; i < 9; i++) {
			moves[i] = false;
		}
	}

	public static boolean setComputerMove() {
		for (int index = 0; index < moves.length; index++) {
			if (moves[index] == true) {
				userNumber = index;
				board[userNumber] = computerLetter;
				return true;

			}
		}
		return false;
	}

	public static void createBoard() {

		board = new char[10];
		moves = new boolean[10];
		for (int index = 1; index < board.length; index++)
			board[index] = ' ';
	}

	public static char getInput() {

		System.out.print("Enter the letter (X or O): ");
		return scanner.next().toUpperCase().charAt(0);
	}

	public static void displayBoard() {

		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
				System.out.print("| " + board[(rowIndex * 3) + columnIndex + 1] + " ");
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
			System.out.println("Number which is selected is not free");
			moveIfLocationValid();
		} else {
			board[userNumber] = userLetter;
			System.out.println(userLetter + " is marked at location " + userNumber);
			displayBoard();
		}

	}

	public static boolean tossingOutcome() {
		System.out.print("\nEnter heads (0) or tails (1): ");
		return (scanner.nextInt() == (int) (Math.random() * 2));

	}

	public static String checkWinner() {
		for (int state = 0; state < winningStates.length; state++) {
			String line = "";
			for (int index = 0; index < 3; index++)
				line += board[winningStates[state][index]];
			if (line.equals(Character.toString(userLetter).repeat(3))) {
				return "User";
			} else if (line.equals(Character.toString(computerLetter).repeat(3))) {
				return "Computer";
			}
		}
		for (int index = 1; index < 10; index++) {
			if (board[index] == ' ') {
				return null;
			} else if (index == 9) {
				return "draw";
			}
		}
		return null;

	}

	public static void winningMove(char letter) {
		int combinationOfThreeItems[][] = { { 0, 1, 2 }, { 1, 0, 2 }, { 1, 2, 0 } };
		for (int state = 0; state < winningStates.length; state++) {
			for (int nthCombination = 0; nthCombination < combinationOfThreeItems.length; nthCombination++) {

				if (board[winningStates[state][combinationOfThreeItems[nthCombination][0]]] == letter
						&& board[winningStates[state][combinationOfThreeItems[nthCombination][1]]] == letter
						&& board[winningStates[state][combinationOfThreeItems[nthCombination][2]]] == ' ') {

					moves[winningStates[state][combinationOfThreeItems[nthCombination][2]]] = true;
				}
			}
		}

	}

	public static void blockingMove() {
		winningMove(userLetter);
	}

	public static void nextPossibleMoves() {
		int nextMoves[] = { 1, 3, 7, 9 };
		for (int index = 0; index < nextMoves.length; index++) {
			if (board[nextMoves[index]] == ' ') {
				moves[nextMoves[index]] = true;
			}
		}
	}

	public static void nextPossibleCentreMove() {
		int nextMoves[] = { 5 };
		for (int index = 0; index < nextMoves.length; index++) {
			if (board[nextMoves[index]] == ' ') {
				moves[nextMoves[index]] = true;
			}
		}
	}

	public static void nextPossibleRemainingMoves() {
		int nextMoves[] = { 2, 4, 6, 8 };
		for (int index = 0; index < nextMoves.length; index++) {
			if (board[nextMoves[index]] == ' ') {
				moves[nextMoves[index]] = true;
			}
		}
	}
}
