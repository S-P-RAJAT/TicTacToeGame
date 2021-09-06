package com.bridgelabz.tictactoegame;

public class TicTacToeGame {
	static char board[] = new char[10];

	public static void main(String[] args) {
		System.out.println("Welcome to TicTacToe Game!");
		for (int i = 1; i <= board.length; i++) {
			board[i] = ' ';
		}
	}
}
