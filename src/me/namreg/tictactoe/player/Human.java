package me.namreg.tictactoe.player;

import me.namreg.tictactoe.field.Cell;
import me.namreg.tictactoe.field.Field;

public class Human extends Player {

	private boolean hasName;
	private boolean hasSymbol;

	public Human() {
		super();
		chooseName();
		chooseSymbol();
	}

	@Override
	public void makeStep(Field field) {
		System.out.println("Ходит " + getName() + ":");
		boolean stepCompleted = false;
		while (!stepCompleted) {
			if (scanner.hasNext()) {
				String input = scanner.next();
				if (input.matches("\\d\\.\\d")) {
					String[] inputChunks = input.split("\\.");
					int rowIndex = Integer.valueOf(inputChunks[0]);
					int colIndex = Integer.valueOf(inputChunks[1]);
					if (field.isRowIndexValid(rowIndex) && field.isColIndexValid(colIndex)) {
						if (!field.getField()[rowIndex][colIndex].isEmpty()) {
							System.out.println("Ячейка не свободна!");
							System.out.println("Повторите свой ход:");
						} else {
							field.getField()[rowIndex][colIndex].setSymbol(getSymbol());
							stepCompleted = true;
						}
					} else {
						System.out.println("ERROR:Неккоректный ввод");
					}
				} else {
					System.out.println("ERROR:Неправильный формат ввода.");
					System.out.println("Пример \"2.3\"");
				}
			}
		}
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
					System.out.println("ERROR:Введен некорректный символ.");
					System.out.printf("Выберите символ, которым будете играть[%s, %s]:\n", Cell.SYMBOL_X, Cell.SYMBOL_O);
				}
			}
		}
	}
}
