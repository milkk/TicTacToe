package me.namreg.tictactoe.game;

import me.namreg.tictactoe.menu.InfoMenu;
import me.namreg.tictactoe.menu.MenuListenerAdapter;
import me.namreg.tictactoe.menu.StartupMenu;
import me.namreg.tictactoe.player.Player;

import java.util.ArrayList;
import java.util.List;


public class Game {

	public static final Game SHARED_INSTANCE = new Game();
	private boolean running = false;
	private List players = new ArrayList<Player>(2);

	public void start() {
		showStartupMenu();
	}

	public void stop() {
		running = false;
		System.exit(0);
	}

	private void newGame() {
		if (running) {
			System.out.println("Игра уже запущена");
		}
		System.out.println("New game");
	}

	private void rules() {

	}

	private void showStartupMenu() {
		StartupMenu menu = new StartupMenu(new MenuListenerAdapter() {
			@Override
			public void codeSelectedWithSuccess(Integer code, String title) {
				switch (code) {
					case StartupMenu.CODE_NEW_GAME:
						newGame();
						break;
					case StartupMenu.CODE_RULES:
						rules();
						break;
					case StartupMenu.CODE_INFO:
						info();
						break;
					case StartupMenu.CODE_EXIT:
						stop();
				}
			}
		});
		menu.show();
	}

	private void info() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$ Информация $$$$$$$$$$$$$$$$$$$$$$");
		System.out.println();
		System.out.println("Эта игра разработана дла свободного университета hexlet.org");
		System.out.println("Раработчик: Igor German(namreg)");
		System.out.println("Email:iggerman@yandex.ru");
		System.out.println();
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		InfoMenu menu = new InfoMenu(new MenuListenerAdapter() {
			@Override
			public void codeSelectedWithSuccess(Integer code, String title) {
				showStartupMenu();
			}
		});
		menu.show();
	}
}