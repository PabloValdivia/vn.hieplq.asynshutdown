package vn.hieplq.asynshutdown;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;

public class AsynShutdownApp implements IApplication{

	@Override
	public Object start(IApplicationContext context) throws Exception {
		Timer holdThread = new Timer("holdThread");
		holdThread.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Timer task running");
				holdThread.cancel();
			}
		}, 60000);
		
		Thread userThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("Start User Thread");
					Thread.sleep(10000);
					System.out.println("Finish User Thread");
					
					System.out.println("Go to exit app async");
					FrameworkUtil.getBundle(AsynShutdownApp.class).getBundleContext().getBundle(0).stop();
				} catch (InterruptedException | BundleException e) {
					e.printStackTrace();
				}
			}
		}, "User Thread");
		
		userThread.start();
		
		return IApplicationContext.EXIT_ASYNC_RESULT;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
