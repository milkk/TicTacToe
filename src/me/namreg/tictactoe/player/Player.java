package me.namreg.tictactoe.player;

import me.namreg.tictactoe.field.Cell;
import me.namreg.tictactoe.field.Field;

abstract public class Player {

	private String name;
	private char symbol;

	abstract protected void makeStep(Field field);

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		if (!Cell.isSymbolValid(symbol)) {
			throw new IllegalArgumentException("Выбран недопустимый сивмол");
		}
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalNameException {
		if (name.length() < 3 || name.length() > 10) {
			throw new IllegalNameException();
		}
		this.name = name;
	}

	public static class IllegalNameException extends IllegalArgumentException {
		private static final String msg = "Некорректное имя.\n Должно быть больше 3-х символов и меньше 10";

		public IllegalNameException() {
			super(msg);
		}

		public IllegalNameException(String message) {
			super(message);
		}

	}
}