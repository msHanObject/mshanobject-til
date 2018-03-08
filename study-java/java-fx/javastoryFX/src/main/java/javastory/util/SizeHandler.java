package javastory.util;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class SizeHandler {
	public static final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	public static final double SCREEN_FULL_WIDTH = visualBounds.getWidth();
	public static final double SCREEN_FULL_HEIGHT = visualBounds.getHeight();
	
	public static final double POPUP_SCREEN_WIDTH = SCREEN_FULL_WIDTH/1.65;
	public static final double POPUP_SCREEN_HEIGHT= SCREEN_FULL_HEIGHT/1.54;
	
	public static final double POPUP_HALF_WIDTH = POPUP_SCREEN_WIDTH/2;
	public static final double POPUP_HALF_HEIGHT= POPUP_SCREEN_HEIGHT/2;
	
	public static final double POPUP_QUARTER_WIDTH = POPUP_SCREEN_WIDTH/4;
	public static final double POPUP_QUARTER_HEIGHT = POPUP_SCREEN_HEIGHT/4;
	
	public static final double SEARCH_FIELD_WIDTH = SCREEN_FULL_WIDTH/4.2;
	
	public static final double SEARCH_ICON_WIDTH = POPUP_QUARTER_WIDTH/16;
	public static final double SEARCH_ICON_HEIGHT= POPUP_QUARTER_HEIGHT/8;
	
	public static final double WARNING_ICON_WIDTH = POPUP_QUARTER_WIDTH/8;
	public static final double WARNING_ICON_HEIGHT= POPUP_QUARTER_WIDTH/8;
	
	////////not used yet
	public static final double POPUP_LEFT_PANE_WIDTH = POPUP_SCREEN_WIDTH/5;
	public static final double POPUP_LEFT_PANE_HEIGHT = POPUP_SCREEN_HEIGHT/3;
	public static final double POPUP_BORDER_CENTER_WIDTH = POPUP_SCREEN_WIDTH - POPUP_LEFT_PANE_WIDTH;
	public static final double POPUP_BORDER_CENTER_HEIGHT= POPUP_SCREEN_HEIGHT;
	public static final double POPUP_CENTER_ITEM_WIDTH = POPUP_BORDER_CENTER_WIDTH/2;
	public static final double POPUP_CENTER_ITEM_HEIGHT = POPUP_BORDER_CENTER_HEIGHT/2;
	public static final double POPUP_IMGVIEW_WIDTH = POPUP_LEFT_PANE_WIDTH/3;
	public static final double POPUP_IMGVIEW_HEIGHT = POPUP_LEFT_PANE_HEIGHT/4;
	public static final double POPUP_USERICON_HEIGHT = POPUP_LEFT_PANE_HEIGHT/3;
 	public static final double POPUP_EMPTYBOX_WIDTH = POPUP_LEFT_PANE_WIDTH;
 	public static final double POPUP_TOTAL_MENU_HEIGHT = POPUP_IMGVIEW_HEIGHT + POPUP_LEFT_PANE_HEIGHT*2;
 	public static final double POPUP_EMPTYBOX_HEIGHT = POPUP_LEFT_PANE_HEIGHT - POPUP_TOTAL_MENU_HEIGHT;
 	

}
