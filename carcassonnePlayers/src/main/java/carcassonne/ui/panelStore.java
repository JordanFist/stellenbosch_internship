package carcassonne.ui;

import java.util.ArrayList;

class Panel {
	tileDrawable tile;
	meepleDrawable meeple;

	public Panel(tileDrawable tile, meepleDrawable meeple) {
		this.tile = tile;	
		this.meeple = meeple;	
	}
}

public class panelStore {
	public ArrayList<Panel> panels;

	public panelStore() {
		panels = new ArrayList<Panel>();
	}

	public Panel getPanel(int i) {
		return panels.get(i);
	}

	public Panel getLast() {
		if (panels.size() != 0)
			return panels.get(panels.size() - 1);
		return null;	
	}

	public void add(tileDrawable tile, meepleDrawable meeple) {
		Panel panel = new Panel(tile, meeple);
		panels.add(panel);
	}

	public void change(Panel panel, tileDrawable tile, meepleDrawable meeple) {
		if (tile != null) 
			panel.tile = tile;
		if (meeple != null)
			panel.meeple = meeple;
	}
}
