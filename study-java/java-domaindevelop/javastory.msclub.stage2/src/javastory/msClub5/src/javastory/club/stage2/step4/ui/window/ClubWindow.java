public clas ClubWindow {
	//
	private ClubCoordinator clubCoordinator;

	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public ClubWindow(ClubCoordinator clubCoordinator) {
		//
		this.clubCoordinator = clubCoordinator;
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public TravelClub register() {
		//
		TravelClub newClub = null;

		while (true) {

			String clubName = consoleUtil.getValueOf("\n club name(0.Club menu)");

			if (clubName.equals("0") || clubName == null || clubName.equals("")) {
				break;
			}

			if (clubCoordinator.exist(clubName)) {
				narrator.sayln("Club name already exist. --> " + clubName);
				continue;
			}

			String intro = consoleUtil.getValueOf("club intro(0.Club menu)");
			if (intro.equals("0")) {
				break;
			}

			try {
				newClub = new TravelClub(clubName, intro);
				clubCoordinator.register(newClub);

				narrator.say("Registered club: " + newClub.toString());
			} catch (IllegalArgumentException e) {
				narrator.say(e.getMessage());
			}
		}

		return newClub;
	}

	public TravelClub find() {
		//
		TravelClub clubFound = null;

		if (!clubCoordinator.hasClubs()) {
			narrator.sayln("<?> No clubs in the storage.");
			return null;
		}

		while (true) {
			String clubName = consoleUtil.getValueOf("\n club name to find(0.Club menu)");

			if (clubName.equals("0")) {
				break;
			}

			if (clubCoordinator.exist(clubName)) {
				clubFound = clubCoordinator.find(clubName);
				narrator.sayln("\t > Found club: " + clubFound);
			} else {
				narrator.sayln("\t> No such club in the storage --> " + clubName);
			}
		}

		return clubFound;
	}

	public TravelClub findOne() {
		//
		TravelClub clubFound = null;

		if (!clubCoordinator.hasClubs()) {
			narrator.sayln("<?> No clubs in the storage.");
			return null;
		}

		while (true) {
			String clubName = consoleUtil.getValueOf(" Club name to cind?(0.Club menu)");

			if (clubName.equals("0")) {
				break;
			}

			if (clubCoordinator.exist(clubName)) {
				clubFound = clubCoordinator.find(clubName);
				narrator.sayln("\t> Found club: " + clubFound);
				break;
			} else {
				narrator.sayln("\t> No such club in the storage --> " + clubName);
			}
			clubFound = null;
		}

		return clubFound;
	}

	public TravelClub modify() {
		//
		TravelClub targetClub = findOne();
		if (targetClub == null) {
			return targetClub;
		}

		String newName = null;

		while (true) {
			newName = consoleUtil.getValueOf("\n new club name(0.Club menu, Enter. no change)");
			if (newName.equals("0")) {
				break;
			}

			if (newName.equals("")) {
				newName = targetClub.getName();
			}

			if (clubCoordinator.exist(newName)) {
				narrator.sayln("Already exist name --> " + newName);
				continue;
			}

			break;
		}

		String newIntro = consoleUtil.getValueOf("New intro(0.Club menu, Enter. no change)");
		if (newIntro.equals("0")) {
			return targetClub;
		}
		if (newIntro.equals("")) {
			newIntro = targetClub.getIntro();
		}

		String foundedDate = consoleUtil.getValueOf("new founded date(0.Club menu, Enter. no change");
		if (foundedDate.equals("0")) {
			return targetClub;
		}
		if (foundedDate.equals("")) {
			founedDate = targetClub.getFoundedDate();
		}

		try {
			targetClub.setIntro(newIntro);
			targetClub.setName(newName);
			targetClub.setFoundedDate(founedDate);

			narrator.sayln("Club changed: " + targetClub.toString());
		} catch (IllegalArgumentException e) {
			narrator.sayln(e.getMessage());
		}

		return targetClub;
	}

	public void remove() {
		//
		TravelClub targetClub = findOne();
		if (targetClub == null) {
			return;
		}

		String confirmStr = consoleUtil.getValueOf("Remove this club? (Y: yes, N: no)");
		if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
			narrator.sayln("Removing a club --> " + targetClub.getName());
			clubCoordinator.remove(targetClub);
		} else {
			narrator.sayln("Remove cancelled, your club is safe. --> " + targetClub.getName());
		}
	}
}
