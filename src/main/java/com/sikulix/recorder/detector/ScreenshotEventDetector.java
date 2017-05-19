package com.sikulix.recorder.detector;

import java.awt.image.BufferedImage;

import com.sikulix.recorder.event.ScreenShotEvent;

public class ScreenshotEventDetector extends EventDetector {

	public void stop(){
		running = false;
		try {
			capturingThread.join();
		} catch (InterruptedException e) {
		}
	}

	volatile  boolean running = true;
	private Thread capturingThread;
	
	
	public void start(){		
		 
		
		capturingThread = new Thread(){
			public void run(){
				while (running){		
					running = true;
					performScreenCapture();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		};
				
		capturingThread.start();
	}

	private void performScreenCapture(){		 
		BufferedImage image = null; //getRegionOfInterest().capture();
		ScreenShotEvent e = new ScreenShotEvent();
		e.setImage(image);
		eventDetected(e);
	}

}