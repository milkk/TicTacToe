package me.igerman.tictactoe.menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class InfoMenu extends Menu {

	public static final int CODE_BACK = 0;
	private static final String TITLE_BACK = "Вернуться назад";

	private Map<Integer, String> items;

	public InfoMenu(Menu.Listener listener) {
		super(listener);

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
