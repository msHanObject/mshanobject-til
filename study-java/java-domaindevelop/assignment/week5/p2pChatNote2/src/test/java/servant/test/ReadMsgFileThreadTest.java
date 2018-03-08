package servant.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Iterator;

import org.junit.Test;

import servant.MessageFileReader;

public class ReadMsgFileThreadTest {

	
	@Test
	public void testRun() {
		MessageFileReader readMsgFile = new MessageFileReader("testdesk1");
		Iterator<File> iterator =  readMsgFile.sortOut().values().iterator();
		File file = null;
		while(iterator.hasNext()) {
			file = iterator.next();
		}
		assertEquals("170906.dat", file.getName());
	}

}
