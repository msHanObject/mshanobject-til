public class BoardConsole {
	//
	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public BoardConsole() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void register() {
		//
		consoleUtil.getValueOf("\n You've select the board register menu [Enter to go back].");
	}

	public void findByName() {
		//
		consoleUtil.getValueOf("\n You've select the board find menu [Enter to go back].");
	}

	public void modify() {
		//
		consoleUtil.getValueOf("\n You've select the boadr modify menu [Enter to go back].");
	}

	public void remove() {
		//
		consoleUtil.getValueOf("\n You've select the board remove menu [Enter to go back].");
	}
}
