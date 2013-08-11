package me.igerman.tictactoe.menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class StartupMenu extends Menu {

	public static final int CODE_NEW_GAME = 1;
	public static final int CODE_RULES = 2;
	public static final int CODE_INFO = 3;
	public static final int CODE_EXIT = 4;

	private static final String TITLE_NEW_GAME = "Новая игра";
	private static final String TITLE_RULES = "Правила";
	private static final String TITLE_INFO = "Информация";
	private static final String TITLE_EXIT = "Выход";

	private Map<Integer, String> items;

	public StartupMenu(Menu.Listener listener) {
		super(listener);

	}

	@Override
	protected Map<Integer, String> getItems() {
		return items;
	}

	@Override
	protected String getCaption() {
		return "Выберите один из пунктов:";
	}

	@Override
	protected void create() {
		items =  new LinkedHashMap<Integer, String>();
		items.put(CODE_NEW_GAME, TITLE_NEW_GAME);
		items.put(CODE_RULES, TITLE_RULES);
		items.put(CODE_INFO, TITLE_INFO);
		items.put(CODE_EXIT, TITLE_EXIT);
	}
}
