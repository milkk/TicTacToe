package me.igerman.tictactoe.player;

import me.igerman.tictactoe.field.Cell;
import me.igerman.tictactoe.field.Field;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player {

	private static final String NAME = "Компьютер";

	public Computer() {
		super();
		setName(NAME);
	}

	@Override
	public void makeStep(Field field) {
		System.out.println("Ходит " + getName() + ":");
		System.out.println("Просчитываю ход...");
		try {
			Thread.currentThread().sleep(3000); // Emulate computer's hard work :-)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<String> emptyCells = new ArrayList<String>();
		Cell[][] cells = field.getField();
		for (int i = 0; i < field.getRows(); i++) {
			for (int j = 0; j < field.getCols(); j++) {
				if (cells[i][j].isEmpty()) {
					emptyCells.add(i + "." + j);
				}
			}
		}
		int randomIndex = new Random(System.currentTimeMillis()).nextInt(emptyCells.size());
		String coords = emptyCells.get(randomIndex);
		String[] inputChunks = coords.split("\\.");
		int rowIndex = Integer.valueOf(inputChunks[0]);
		int colIndex = Integer.valueOf(inputChunks[1]);
		field.getField()[rowIndex][colIndex].setSymbol(getSymbol());
	}
}