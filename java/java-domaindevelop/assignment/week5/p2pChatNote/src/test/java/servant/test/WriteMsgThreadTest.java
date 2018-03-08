package servant.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import servant.Client;

public class WriteMsgThreadTest {

	@Test
	public void testRun() {
		Client localClient = new Client();
		Client localClient2 = new Client();
		
		try {
			localClient.setMsgToSend("local2");
			
			localClient2.setMsgToSend("local2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(localClient.getMsgToSend(),localClient2.getMsgToSend());
	}
}
