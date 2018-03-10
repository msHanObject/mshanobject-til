public class BoardFile {
	//
	private stataic Map<String,Integer> keyIndexMap;

	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("id", 0);
		keyIndexMap.put("name", 1);
		keyIndexMap.put("adminEmail", 2);
	}

	private FileDbWrapper baordFile;
	private FileDbWrapper boardTempFile;

	public BoardFile() {
		//
		this.boardFile = new FileDbWrapper(
				"step41",
				FileConfig.getFileName("Board"),
				FileConfig.getDelimiter());

		this.boardTempFile = new FileDbWarpper(
				"step41",
				FileConfig.getFileName("BoardTemp"),
				FileConfig.getDelimiter());

		this.boardFile.setKeyIndexMap(keyIndexMap);
		this.boardTempFile.setKeyIndexMap(keyIndexMap);
	}

	public boolean exists(String boardId) {
		//
		boolean found = false;
		BufferedReader reader;

		try {
			reader = boardFile.requestReader();

			String line = null;
			while (true) {
				if ((line  = reader.readLine()) == null) {
					break;
				}

				if (boardFile.hasValueOf("id", boardId, line)) {
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

	public void write(SocialBoard board) {
		//
		if (this.exists(board.getId())) {
			return;
		}

		FileWriter fileWriter;
		try {
			fileWriter = boardFile.requestFileWriter();
			fileWriter.write(converToStr(board));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SocialBoard read(String boardId) {
		//
		SocialBoard board = null;
		BufferedReader reader = null;

		try {
			reader = boardFile.requestReader();
			String line = null;

			try {
				reader = baordFile.reqeustReader();
				String line = null;

				while ((line = reader.readLine()) != null) {

					if (boardFile.hasValueOf("id", boardId, line)) {
						board = converToBoard(line);
						break;
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return board;
		}

		public SocialBoard readLast() {
			//
			String lastLine = null;
			BufferedReader reader = null;

			try {
				reader = boardFile.requestReader();
				String line = null;

				while ((line = reader.readLine()) != null) {
					lastLine = line;
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return converToBoard(lastLine);
		}

		public List<SocialBoard> readByName(String name) {
			//
			List<SocialBoard> boards = new ArrayList<>();
			BufferedReader reader = null;

			try {
				reader = boardFile.requestReader();
				String line = null;

				while ((line = reader.readLine()) != null) {

					if (boardFile.hasValueOf("name", name, line)) {
						SocialBoard club = converToBoard(line);
						boards.add(club);
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return boards;
		}

		public List<SocialBoard> readByAdminEmail(String adminEmail) {
			//
			List<SocialBoard> boards = new ArrayList<>();
			BufferedReader reader =  null;

			try {
				reader = boardFile.requestReader();
				String line = null;

				while ((line = reader.readline()) != null) {

					if (boardFile.hasValueOf("adminEmail", adminEmail, line)) {
						SocialBoard club = converToBoard(line);
						boards.add(club);
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return boards;
		}

		public void update(SocialBoard board) {
			//
			BufferedReader reader;
			PrintWriter writer;

			try {
				reader = boardFile.reequestReader();
				writer = boardTempFile.requestPrintWriter();

				String line = null;
				String boardId = board.getId();
				while ((line = reader.readLine()) != null) {
					line = convertToStr(board);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();

			if (!boardFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			if (!boardTempFile.renameTo(boardFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String boardId) {
		//
		BufferedReader reader;
		PrintWriter writer;

		try {
			reader = boardFile.requestReader();
			writer = boardTempFile.requestPrintWriter();
			String line = null;

			while ((line = reader.readLine()) != null) {

				if (boardFile.hasValueOf("id", boardId, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}

			writer.close();
			writer.close();

			if (!boardFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			if (!boardTempFile.renameTo(boardFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SocialBoard convertToBoard(String readLine) {
		//
		return (SocialBoard)boardFile.convertTo(readLine, SocialBoard.class);
	}

	private String converToStr(SocialBoard board) {
		//
		return boardFile.convertToFrom(board);
	}

	public static void main(String[] args) {
		//
		BoardFile boardFile = new BoardFile();
		SocialBoard boardOne = SocialBoard.getSample(TravelClubl.getSample(false));
		String boardId = boardOne.getId();

		boardFile.write(boardOne);
		SocialBoard readBoardOne = boardFile.read(boardId);
		System.out.println(" > read board: " + readBoardOne);

		readBoardOne.setName("NewName");
		boardFile.update(readBoardOne);
		List<SocialBoard> updateBoardsOne = boardFile.readByAdminEmail(readBoardOne.getAdminEmail());
		System.out.println(" > read board: " + updateBoardsOne.get(0).toString());
	}
}
