package me.namreg.tictactoe.field;


public class Cell {

	public static final char SYMBOL_X = 'X';
	public static final char SYMBOL_O = 'O';
	public static final char SYMBOL_DEFAULT = ' ';

	public static boolean isSymbolValid(char symbol) {
		return symbol == SYMBOL_X || symbol == SYMBOL_O;
	}
}
