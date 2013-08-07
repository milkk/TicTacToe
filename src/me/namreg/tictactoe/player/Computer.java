package me.namreg.tictactoe.player;

import me.namreg.tictactoe.field.Field;

public class Computer extends Player {

	private static final String NAME = "Компьютер";

	public Computer() {
		super();
		setName(NAME);
	}

	@Override
	protected void makeStep(Field field) {

	}
}
