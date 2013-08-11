package me.igerman.tictactoe.menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameMenu extends Menu {

	public static final int CODE_HUMAN_HUMAN = 1;
	public static final int CODE_HUMAN_COMPUTER = 2;
	public static final int CODE_BACK = 0;

	private static final String TITLE_HUMAN_HUMAN = "Человек vs Человек";
	private static final String TITLE_HUMAN_COMPUTER = "Человек vs Компьютер";
	private static final String TITLE_BACK = "Вернуться назад";
	private Map<Integer, String> items;

	public GameMenu(Listener l) {
		super(l);
	}

	@Override
	protected Map<Integer, String> getItems() {
		return items;
	}

	@Override
	protected String getCaption() {
		return "Выберите режим игры:";
	}

	@Override
	protected void create() {
		items = new LinkedHashMap<Integer, String>();
		items.put(CODE_HUMAN_HUMAN, TITLE_HUMAN_HUMAN);
		items.put(CODE_HUMAN_COMPUTER, TITLE_HUMAN_COMPUTER);
		items.put(CODE_BACK, TITLE_BACK);
	}
}
