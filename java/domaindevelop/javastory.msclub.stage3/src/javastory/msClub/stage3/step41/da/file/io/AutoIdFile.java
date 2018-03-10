public class AutoIdFile {
	//
	private static Map<String,Integer> keyIndexMap;

	static {
		keyIndexMap = new LinkedHashMap<String,Ingeter>();
		keyIndexMap.put("className", 0);
	}

	private FileDbWrapper autoIdFile;
	private FIleDbWrapper autoIdTempFile;


