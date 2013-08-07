package me.namreg.tictactoe.field;


public class Field {

	private static final int ROWS = 3;
	private static final int COLS = 3;

	private Cell[][] field = new Cell[ROWS][COLS];

	public Field() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				field[i][j] = new Cell();
			}
		}
	}

	public Cell[][] getField() {
		return field;
	}

	public void display() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				field[i][j].display();
			}
			System.out.println();
		}
	}

	public boolean isRowIndexValid(int index) {
		return index >= 0 && index < ROWS;
	}

	public boolean isColIndexValid(int index) {
		return index >= 0 && index < COLS;
	}
}