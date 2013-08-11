package me.igerman.tictactoe.player;

import me.igerman.tictactoe.field.Cell;
import me.igerman.tictactoe.field.Field;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Player {

	private static final int NAME_MIN_SIZE = 3;
	private static final int NAME_MAX_SIZE = 10;
	private static ArrayList<Character> selectedSymbols = new ArrayList<Character>();

	protected Scanner scanner = new Scanner(System.in);
	private String name;
	private char symbol;

	abstract public void makeStep(Field field);

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		if (!Cell.isSymbolValid(symbol)) {
			throw new IllegalArgumentException("ERROR:Выбран недопустимый сивмол");
		}
		if (selectedSymbols.contains(symbol)) {
			throw new IllegalArgumentException("ERROR: Символ уже занят");
		}
		this.symbol = symbol;
		selectedSymbols.add(symbol);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalNameException {
		if (name.length() < NAME_MIN_SIZE || name.length() > NAME_MAX_SIZE) {
			throw new IllegalNameException();
		}
		this.name = name;
	}

	public static class IllegalNameException extends IllegalArgumentException {
		private static final String msg = "ERROR:Некорректное имя.\n Должно быть больше " + NAME_MIN_SIZE + " " +
				"символов и меньше " + NAME_MAX_SIZE;

		public IllegalNameException() {
			super(msg);
		}
	}
}