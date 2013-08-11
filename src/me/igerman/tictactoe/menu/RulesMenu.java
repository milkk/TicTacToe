package me.igerman.tictactoe.menu;

import java.util.LinkedHashMap;
import java.util.Map;


public class RulesMenu extends Menu {

	public static final int CODE_BACK = 0;
	private static final String TITLE_BACK = "Вернуться назад";

	private Map<Integer, String> items;

	public RulesMenu(Listener l) {
		super(l);
	}

	@Override
	protected Map<Integer, String> getItems() {
		return items;
	}

	@Override
	protected String getCaption() {
		return "Выберите пункт:";
	}

	@Override
	protected void create() {
		items = new LinkedHashMap<Integer, String>();
		items.put(CODE_BACK, TITLE_BACK);
	}
}
