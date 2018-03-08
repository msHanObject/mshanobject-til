public class ClubFile {
	//
	private static Map<String,Integer> keyIndexMap;

	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("id", 0);
		keyIndexMap.put("name", 1);
	}

	private FileDbWrapper clubFile;
	private FileDbWrapper clubTempFile;

	public ClubFile() {
		//
		this.clubFile = new FileDbWrapper(
				"step41",
				FileConfig.getFileName("Club"),
				FileConfig.getDelimiter());
		
		this.clubTempFile = new FileDbWrapper(
				"step41",
				FileConfig.getFileName("ClubTemp"),
				FileConfig.getDelimiter());

		this.clubFile.setKeyIndexMap(keyIndexMap);
		this.clubTempFile.setKeyIndexMap(keyIndexMap);
	}

	public boolean exists(String clubId) {
		//
		boolean found = false;
		BufferedReader reader;

		try {
			reader = clubFile.reqeustReader();

			String line = null;
			while (true) {
				if ((line = reader.readLine()) == null) {
					break;
				}

				if (clubFile.hasValueOf("id", clubId, line)) {
					found = true;
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return found;
	}

	public void write(TravelClub club) {
		//
		if (this.exists(club.getId())) {
			return;
		}

		FileWriter fileWriter;
		try {
			fileWriter = clubFile.requestFileWriter();
			fileWriter.write(converToStr(club));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TravelClub read(String clubId) {
		//
		TravelClub club = null;
		BufferedReader reader = null;

		try {
			reader = clubFile.reqeustReader();
			String line = null;

			while ((line = reader.readLine()) != null) {

				if (clubFile.hasValueOf("id", clubId,line)) {
					club = converToClub(line);
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return club;
	}

	public TravelClub readLast() {
		//
		String lastLine = null;

