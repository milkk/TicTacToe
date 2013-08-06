package me.namreg.tictactoe.game;

import me.namreg.tictactoe.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

	public static final Game SHARED_INSTANCE = new Game();

	private static final int MENU_CODE_NEW_GAME = 1;
	private static final int MENU_CODE_RULES = 2;
	private static final int MENU_CODE_INFO = 3;
	private static final int MENU_CODE_EXIT = 4;

	private static final String MENU_TITLE_NEW_GAME = "Новая игра";
	private static final String MENU_TITLE_RULES = "Правила";
	private static final String MENU_TITLE_INFO = "Информация";
	private static final String MENU_TITLE_EXIT = "Выход";

	private List players = new ArrayList<Player>(2);
	private boolean running = false;
	private Scanner scanner = new Scanner(System.in);
	private boolean isMenuChosen = false;

	public void start() {
		showMenu();

	}

	public void stop() {
		running = false;
	}

	private void newGame() {
		if (running) {
			System.out.println("Игра уже запущена");
		}
		System.out.println("New game");
	}

	private void rules() {

	}

	private void info() {

	}

	private void incorrectInput() {
		isMenuChosen = false;
		System.out.println("Некорректный ввод");
	}

	private void showMenu() {

		System.out.println("Выберите один из пунктов: ");
		drawMenuItem(MENU_CODE_NEW_GAME, MENU_TITLE_NEW_GAME);
		drawMenuItem(MENU_CODE_RULES, MENU_TITLE_RULES);
		drawMenuItem(MENU_CODE_INFO, MENU_TITLE_INFO);
		drawMenuItem(MENU_CODE_EXIT, MENU_TITLE_EXIT);

		while (!isMenuChosen) {
			if (scanner.hasNextInt()) {
				Integer selectedMenuNumber = scanner.nextInt();
				isMenuChosen = true;
				switch (selectedMenuNumber) {
					case MENU_CODE_NEW_GAME:
						newGame();
						break;
					case MENU_CODE_RULES:
						rules();
						break;
					case MENU_CODE_INFO:
						info();
						break;
					case MENU_CODE_EXIT:
						stop();
						System.exit(0);
						break;
					default:
						incorrectInput();
				}

			} else {
				incorrectInput();
			}
		}
	}

	private void drawMenuItem(int number, String title) {
		System.out.printf("%d. %s\n", number, title);
	}

}