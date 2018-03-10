public class PostingConsole {
	//
	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public PostingConsole() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void findBoar() {
		//
		consoleUtil.getValueOf("\n You've select the board register menu [Enter to go back].");
	}

	public void register() {
		//
		consoleUtil.getValueOf("\n You've select the posting register menu [Enter to go back].");
	}

	public void findByBoardId() {
		//
		consoleUtil.getValueOf("\n You've select the all postings in board find menu [Enter to go back].");
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
