package me.igerman.tictactoe.game;

import me.igerman.tictactoe.field.Cell;
import me.igerman.tictactoe.field.Field;
import me.igerman.tictactoe.helpers.FileHelper;
import me.igerman.tictactoe.menu.*;
import me.igerman.tictactoe.player.Computer;
import me.igerman.tictactoe.player.Human;
import me.igerman.tictactoe.player.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Game {

	public static final Game SHARED_INSTANCE = new Game();
	private boolean running = false;
	private Mode mode;
	private MenuManager menuManager = new MenuManager();
	private ArrayList<Player> players = new ArrayList<Player>(2);

	public void start() {
		if (running) {
			System.out.println("Игра уже запущена");
			return;
		}
		menuManager.showStartupMenu();
	}

	public void stop() {
		running = false;
		System.exit(0);
	}

	private void initialize() {
		if (mode == Mode.HUMAN_COMPUTER) {
			initializeHumanComputer();
		} else if (mode == Mode.HUMAN_HUMAN) {
			initializeHumanHuman();
		}
		GameManager gameManager = new GameManager();
		gameManager.manage();

	}

	private void initializeHumanComputer() {
		Player humanPlayer = new Human();
		Player computerPlayer = new Computer();
		if (humanPlayer.getSymbol() == Cell.SYMBOL_O) {
			computerPlayer.setSymbol(Cell.SYMBOL_X);
		} else if (humanPlayer.getSymbol() == Cell.SYMBOL_X) {
			computerPlayer.setSymbol(Cell.SYMBOL_O);
		}
		players.add(humanPlayer);
		players.add(computerPlayer);
	}

	private void initializeHumanHuman() {
		players.add(new Human());
		players.add(new Human());
	}


	public enum Mode {HUMAN_HUMAN, HUMAN_COMPUTER}

	public class MenuManager {

		private static final String INFO_FILENAME = "info.txt";
		private static final String RULES_FILENAME = "rules.txt";

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
							return;
						case GameMenu.CODE_HUMAN_COMPUTER:
							mode = Mode.HUMAN_COMPUTER;
							break;
						case GameMenu.CODE_HUMAN_HUMAN:
							mode = Mode.HUMAN_HUMAN;
							break;
					}
					initialize();
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
	}

	private class GameManager {

		private Player player1;
		private Player player2;
		private Field field;
		private int currentQueueIndex = 0;

		public void manage() {
			player1 = players.get(0);
			player2 = players.get(1);
			createField();
			System.out.printf("%s[%s] VS %s[%s]\n", player1.getName(), player1.getSymbol(), player2.getName(),
					player2.getSymbol());
			System.out.println("Игра начинается.");
			System.out.printf("Первым ходит %s[%s].\n", getPlayerWhoWillMakeTheFirstStep().getName(),
					getPlayerWhoWillMakeTheFirstStep().getSymbol());
			showField();
			running = true;
			ArrayList<Player> queue = new ArrayList<Player>(2);
			queue.add(getPlayerWhoWillMakeTheFirstStep());
			queue.add(getPlayerWhoWillMakeTheSecondStep());
			int totalSteps = 0;
			while (running) {
				Player currentPlayer = queue.get(currentQueueIndex);
				System.out.println();
				currentPlayer.makeStep(field);
				totalSteps++;
				checkWinner(currentPlayer);
				if (totalSteps == field.getCols() * field.getRows()) {
					announceATie();
				}
				showField();
				nextQueueIndex();
			}
		}

		private void checkWinner(Player player) {
			boolean isPlayerWon = field.isHorizontalCellsFilledWithSymbol(player.getSymbol());
			isPlayerWon = isPlayerWon || field.isVerticalCellsFilledWithSymbol(player.getSymbol());
			isPlayerWon = isPlayerWon || field.isDiagonalCellsFilledWithSymbol(player.getSymbol());
			if (isPlayerWon) {
				announceTheWinner(player);
			}
		}

		private void announceTheWinner(Player player) {
			System.out.println("СТОП игра");
			System.out.printf("Выиграл игрок %s[%s].\n", player.getName(), player.getSymbol());
			field.display();
			stop();
		}

		private void announceATie() {
			System.out.println("СТОП игра");
			System.out.println("Победила дружба :-)");
			field.display();
			stop();
		}

		private void nextQueueIndex() {
			currentQueueIndex++;
			if (currentQueueIndex > players.size() - 1) {
				currentQueueIndex = 0;
			}
		}

		private void createField() {
			field = new Field();
		}

		private void showField() {
			field.display();
		}

		private Player getPlayerWhoWillMakeTheFirstStep() {
			if (player1.getSymbol() == Cell.SYMBOL_X) {
				return player1;
			} else if (player2.getSymbol() == Cell.SYMBOL_X) {
				return player2;
			}
			return null;
		}

		private Player getPlayerWhoWillMakeTheSecondStep() {
			if (player1.getSymbol() == Cell.SYMBOL_O) {
				return player1;
			} else if (player2.getSymbol() == Cell.SYMBOL_O) {
				return player2;
			}
			return null;
		}
	}
}