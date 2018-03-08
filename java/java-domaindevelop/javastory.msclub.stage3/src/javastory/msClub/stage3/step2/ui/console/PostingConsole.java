package javastory.msClub.stage3.step2.ui.console;

import javastory.msClub.stage3.util.ConsoleUtil;
import javastory.msClub.stage3.util.Narrator;
import javastory.msClub.stage3.util.TalkingAt;

public class PostingConsole {
	//
	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public PostingConsole() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void findBoard() {
		//
		consoleUtil.getValueOf("\n You've select the target board register menu [Enter to go back].");
	}

	public void register() {
		//
		consoleUtil.getValueOf("\n You've selecth the posting register menu [Enter to go back].");
	}

	public void findByBoardId() {
		//
		consoleUtil.getValueOf("\n You've select all Postings in board find menu [Enter to go back].");
	}

	public void find() {
		//
		consoleUtil.getValueOf("\n You've select the posting find menu [Enter to go back].");
	}

	public void modify() {
		//
		consoleUtil.getValueOf("\n You've select the posting modify menu [Enter to go back].");
	}

	public void remove() {
		//
		consoleUtil.getValueOf("\n You've select the posting remove menu [Enter to go back].");
	}
}
