package me.namreg.tictactoe.game;

import me.namreg.tictactoe.helpers.FileHelper;
import me.namreg.tictactoe.menu.*;
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
	private Mode mode;

	public void start() {
		if (running) {
			System.out.println("Игра уже запущена");
			return;
		}
		showStartupMenu();
	}

	public void stop() {
		running = false;
		System.exit(0);
	}

	private void initialize() {

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

	private void newGame() {

		System.out.println();
		GameMenu menu = new GameMenu(new MenuListenerAdapter() {
			@Override
			public void codeChosenWithSuccess(Integer code, String title) {
				switch (code) {
					case GameMenu.CODE_BACK:
						showStartupMenu();
						break;
					case GameMenu.CODE_HUMAN_COMPUTER:
						mode = Mode.HUMAN_COMPUTER;
					case GameMenu.CODE_HUMAN_HUMAN:
						mode = Mode.HUMAN_HUMAN;
						initialize();
						break;
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
				if (code == RulesMenu.CODE_BACK) {
					showStartupMenu();
				}
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
				if (code == InfoMenu.CODE_BACK) {
					showStartupMenu();
				}
			}
		});
		menu.show();
	}

	public enum Mode {HUMAN_HUMAN, HUMAN_COMPUTER}
}