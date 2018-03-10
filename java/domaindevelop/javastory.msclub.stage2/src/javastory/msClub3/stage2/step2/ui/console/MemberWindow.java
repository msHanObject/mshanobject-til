package javastory.msClub3.stage2.step2.ui.console;

import javastory.msClub3.stage2.util.ConsoleUtil;
import javastory.msClub3.stage2.util.Narrator;
import javastory.msClub3.stage2.util.TalkingAt;

public class MemberWindow {
	//
	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public MemberWindow() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void findClub() {
		//
		consoleUtil.getValueOf("\n You've select the club find menu [Enter to go back].");
	}

	public void add() {
		//
		consoleUtil.getValueOf("\n You've select the member add menu [Enter to go back].");
	}

	public void find() {
		//
		consoleUtil.getValueOf("\n You've select the member find menu [Enter to go back].");
	}

	public void modify() {
		//
		consoleUtil.getValueOf("\n You've select the member modify menu [Enter to go back].");
	}

	public void remove() {
		//
		consoleUtil.getValueOf("\n You've select the member remove menu [Enter to go back].");
	}
}
