package me.igerman.tictactoe.menu;

import java.util.Map;
import java.util.Scanner;

abstract public class Menu {

	private boolean chosen;
	private Scanner scanner = new Scanner(System.in);
	private Listener listener;

	public Menu(Listener l) {
		listener = l;
		create();
	}

	public void show() {
		draw();
		while (!isChosen()) {
			if (scanner.hasNextInt()) {
				Integer input = scanner.nextInt();
				if (isCodeValid(input)) {
					listener.codeChosenWithSuccess(input, getItems().get(input));
					setChosen(true);
				} else {
					incorrectInput(input);
				}
			} else {
				incorrectInput(scanner.next());
			}
		}
		scanner.close();
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean c) {
		chosen = c;
	}

	public boolean isCodeValid(Integer code) {
		return getItems().containsKey(code);
	}

	abstract protected Map<Integer, String> getItems();

	abstract protected String getCaption();

	abstract protected void create();

	protected void draw() {
		System.out.println(getCaption());
		for (Map.Entry<Integer, String> entry : getItems().entrySet()) {
			drawItem(entry.getKey(), entry.getValue());
		}
	}

	protected void drawItem(Integer code, String title) {
		System.out.printf("%d. %s\n", code, title);
	}

	protected void incorrectInput(Object input) {
		setChosen(false);
		System.out.println();
		System.out.println("ERROR:Некорректный ввод");
		listener.codeChosenWithError(input);
		draw();
	}

	public interface Listener {

		void codeChosenWithSuccess(Integer code, String title);

		void codeChosenWithError(Object input);
	}

}