package vn.hieplq.asynshutdown;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class NormalShutdownApp implements IApplication {

	@Override
	public Object start(IApplicationContext arg0) throws Exception {
		Timer holdThread = new Timer("holdThread");
		holdThread.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Timer task running");
				
			}
		}, 120000);
		
		Thread userThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("Start User Thread");
					Thread.sleep(10000);
					System.out.println("Finish User Thread");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "User Thread");
		
		userThread.start();
		
		userThread.join();
		
		System.out.println("Go to exit app");
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		

	}

}
