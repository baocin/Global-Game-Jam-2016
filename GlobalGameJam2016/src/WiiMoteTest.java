import ca.ubc.cs.wiimote.Wiimote;
import ca.ubc.cs.wiimote.WiimoteDiscoverer;
import ca.ubc.cs.wiimote.WiimoteDiscoveryListener;


public class WiiMoteTest {

	public static void main(String[] args) {
		WiimoteDiscoverer wd = WiimoteDiscoverer.getWiimoteDiscoverer();
		wd.addWiimoteDiscoveryListener(new WiimoteDiscoveryListener() {
			
			@Override
			public void wiimoteDiscovered(Wiimote arg0) {
				System.out.println ("Wheeeee!");
				arg0.setLight(0x4);
				arg0.cleanup();
			}
		});
		wd.startWiimoteSearch();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
