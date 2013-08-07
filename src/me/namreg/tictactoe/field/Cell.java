package me.namreg.tictactoe.field;


public class Cell {

	public static final char SYMBOL_X = 'X';
	public static final char SYMBOL_O = 'O';
	public static final char SYMBOL_DEFAULT = ' ';
	private char symbol;

	public Cell() {
		symbol = SYMBOL_DEFAULT;
	}

	public static boolean isSymbolValid(char symbol) {
		return symbol == SYMBOL_X || symbol == SYMBOL_O;
	}

	public void setSymbol(char s) {
		symbol = s;
	}

	public boolean isEmpty() {
		return symbol == SYMBOL_DEFAULT;
	}

	public void display() {
		System.out.printf("[%s]", symbol);
	}
}
