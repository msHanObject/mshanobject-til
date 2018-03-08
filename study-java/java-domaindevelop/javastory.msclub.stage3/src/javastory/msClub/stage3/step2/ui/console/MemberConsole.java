package javastory.msClub.stage3.step2.ui.console;

import javastory.msClub.stage3.util.ConsoleUtil;
import javastory.msClub.stage3.util.Narrator;
import javastory.msClub.stage3.util.TalkingAt;

public class MemberConsole {
	//
	private ConsoleUtil consoleUtil;
	private Narrator narrtor;

	public MemberConsole() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void register() {
		//
		consoleUtil.getValueOf("\n You've select the member register menu [Enter to go back].");
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
