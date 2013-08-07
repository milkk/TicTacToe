package me.namreg.tictactoe.game;

import me.namreg.tictactoe.helpers.FileHelper;
import me.namreg.tictactoe.menu.InfoMenu;
import me.namreg.tictactoe.menu.MenuListenerAdapter;
import me.namreg.tictactoe.menu.RulesMenu;
import me.namreg.tictactoe.menu.StartupMenu;
import me.namreg.tictactoe.player.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Game {

	public static final Game SHARED_INSTANCE = new Game();
	private static final String INFO_FILENAME = "info.txt";
	private static final String RULES_FILENAME = "rules.txt";
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
		System.out.println("Starting new game");
	}

	private void showStartupMenu() {
		StartupMenu menu = new StartupMenu(new MenuListenerAdapter() {
			@Override
			public void codeChosenWithSuccess(Integer code, String title) {
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

	private void rules() {
		try {
			System.out.println(FileHelper.getTextFromFile(RULES_FILENAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		RulesMenu menu = new RulesMenu(new MenuListenerAdapter() {
			@Override
			public void codeChosenWithSuccess(Integer code, String title) {
				showStartupMenu();
			}
		});
		menu.show();
	}

	private void info() {
		try {
			System.out.println(FileHelper.getTextFromFile(INFO_FILENAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InfoMenu menu = new InfoMenu(new MenuListenerAdapter() {
			@Override
			public void codeChosenWithSuccess(Integer code, String title) {
				showStartupMenu();
			}
		});
		menu.show();
	}
}