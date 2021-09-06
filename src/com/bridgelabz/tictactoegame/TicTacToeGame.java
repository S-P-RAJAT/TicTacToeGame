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
		move();
		moveIfLocationValid();
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

	private static void displayBoard() {
		
		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3 ; columnIndex++) {
			System.out.print("| " + board[(rowIndex*3)+columnIndex+1]+" ");
			}
			System.out.println("| ");
		}
	}
	private static void move() {
		System.out.print("\nEnter a location on board to make the mark (1-9): ");
		userNumber = scanner.nextInt();
		if (userNumber < 1 || userNumber > 9) {
			displayBoard();
			System.out.println("Your input is Invalid");
			move();
		}
	}
	private static void moveIfLocationValid() {
		if (board[userNumber] == 'X' || board[userNumber] == 'O') {
			displayBoard();
			System.out.println("Number which is selected is not free");
			move();
			moveIfLocationValid();
		} else {
			board[userNumber] = userLetter;
			System.out.println(userLetter + " is marked at location " + userNumber);
			displayBoard();

		}
	}
}
