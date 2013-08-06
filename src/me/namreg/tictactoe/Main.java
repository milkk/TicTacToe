package me.namreg.tictactoe;

import me.namreg.tictactoe.game.Game;

public class Main {

	public static void main(String[] args) {

		Game game = Game.SHARED_INSTANCE;
		game.start();
	}
}
