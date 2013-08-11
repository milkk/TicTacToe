package me.igerman.tictactoe.field;


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

	public boolean isHorizontalCellsFilledWithSymbol(char symbol) {
		boolean isFilled = false;
		for (int i = 0; i < ROWS; i++) {
			isFilled = isFilled || isHorizontalLineFilled(symbol, i);
		}
		return isFilled;
	}

	private boolean isHorizontalLineFilled(char symbol, int lineIndex) {
		boolean isFilled = true;
		for (int j = 0; j < COLS; j++) {
			isFilled = isFilled && field[lineIndex][j].getSymbol() == symbol;
		}
		return isFilled;
	}

	public boolean isVerticalCellsFilledWithSymbol(char symbol) {
		boolean isFilled = false;
		for (int i = 0; i < COLS; i++) {
			isFilled = isFilled || isVerticalLineFilled(symbol, i);
		}
		return isFilled;
	}

	private boolean isVerticalLineFilled(char symbol, int lineIndex) {
		boolean isFilled = true;
		for (int i = 0; i < ROWS; i++) {
			isFilled = isFilled && field[lineIndex][i].getSymbol() == symbol;
		}
		return isFilled;
	}

	public boolean isDiagonalCellsFilledWithSymbol(char symbol) {
		return isRightDiagonalFilled(symbol) || isLeftDiagonalFilled(symbol);
	}

	private boolean isLeftDiagonalFilled(char symbol) {
		boolean isFilled = true;
		for (int i = 0; i < ROWS; i++) {
			isFilled = isFilled && field[i][i].getSymbol() == symbol;
		}
		return isFilled;
	}

	private boolean isRightDiagonalFilled(char symbol) {
		boolean isFilled = true;
		for (int i = 0, j = COLS - 1; i < j; i++, j--) {
			isFilled = isFilled && field[i][j].getSymbol() == symbol;
		}
		return isFilled;
	}

	public boolean isRowIndexValid(int index) {
		return index >= 0 && index < ROWS;
	}

	public boolean isColIndexValid(int index) {
		return index >= 0 && index < COLS;
	}
}