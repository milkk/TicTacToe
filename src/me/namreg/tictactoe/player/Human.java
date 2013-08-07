package me.namreg.tictactoe.player;

import me.namreg.tictactoe.field.Cell;
import me.namreg.tictactoe.field.Field;

import java.util.Scanner;

public class Human extends Player {

	private boolean hasName;
	private boolean hasSymbol;

	private Scanner scanner;

	public Human() {
		super();
		scanner = new Scanner(System.in);
		chooseName();
		chooseSymbol();
		scanner.close();
	}

	@Override
	protected void makeStep(Field field) {

	}

	private void chooseName() {
		System.out.println("Введите имя:");
		while (!hasName) {
			if (scanner.hasNextLine()) {
				String input = scanner.next();
				try {
					setName(input);
					hasName = true;
				} catch (IllegalNameException e) {
					hasName = false;
					System.out.println(e.getMessage());
					System.out.println("Введите имя:");
				}
			}
		}
	}

	private void chooseSymbol() {
		System.out.printf("Выберите символ, которым будете играть[%s, %s]:\n", Cell.SYMBOL_X, Cell.SYMBOL_O);
		while (!hasSymbol) {
			if (scanner.hasNext()) {
				char input = scanner.next().charAt(0);
				if (Cell.isSymbolValid(input)) {
					setSymbol(input);
					hasSymbol = true;
				} else {
					hasSymbol = false;
					System.out.println("Введен некорректный символ.");
					System.out.printf("Выберите символ, которым будете играть[%s, %s]:\n", Cell.SYMBOL_X, Cell.SYMBOL_O);
				}
			}
		}
	}
}
