package me.namreg.tictactoe.menu;

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

//	public Menu(){
//
//	}

	public void show() {
		System.out.println(getCaption());
		for (Map.Entry<Integer, String> entry : getItems().entrySet()) {
			showItem(entry.getKey(), entry.getValue());
		}
		while (!isChosen()) {
			if (scanner.hasNextInt()) {
				Integer input = scanner.nextInt();
				if (isCodeValid(input)) {
					listener.codeSelectedWithSuccess(input, getItems().get(input));
					setChosen(true);
				} else {
					incorrectInput(input);
				}
			} else {
				incorrectInput(scanner.next());
			}
		}
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

	protected void showItem(Integer code, String title) {
		System.out.printf("%d. %s\n", code, title);
	}

	protected void incorrectInput(Object input) {
		setChosen(false);
		System.out.println("Некорректный ввод");
		listener.codeSelectedWithError(input);
	}

	public interface Listener {

		void codeSelectedWithSuccess(Integer code, String title);

		void codeSelectedWithError(Object input);
	}

}