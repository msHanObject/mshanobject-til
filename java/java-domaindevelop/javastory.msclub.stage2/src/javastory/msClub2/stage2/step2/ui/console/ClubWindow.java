package javastory.msClub2.stage2.step2.ui.console;

import javastory.msClub2.stage2.util.ConsoleUtil;
import javastory.msClub2.stage2.util.Narrator;
import javastory.msClub2.stage2.util.TalkingAt;

public class ClubWindow {
	//
	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public ClubWindow() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void register() {
		//
		consoleUtil.getValueOf("\n You've select the club register menu [Enter to go back].");
	}

	public void find() {
		//
		consoleUtil.getValueOf("\n You've select the club find menu [Enter to go back].");
	}
	public void modify() {
		//
		consoleUtil.getValueOf("\n You've select the club modify menu [Enter to go back].");
	}
	public void remove() {
		//
		consoleUtil.getValueOf("\n You've select the club remove menu [Enter to go back].");
	}
}
