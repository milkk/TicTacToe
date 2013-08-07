package me.igerman.tictactoe.player;

import me.igerman.tictactoe.field.Field;

public class Computer extends Player {

	private static final String NAME = "Компьютер";

	public Computer() {
		super();
		setName(NAME);
	}

	@Override
	public void makeStep(Field field) {
		System.out.println("Ходит " + getName() + ":");
	}
}
