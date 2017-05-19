package com.sikulix.recorder.examples;

import java.io.File;

//import com.sikulix.api.DesktopScreenRegion;
import com.sikulix.recorder.Recorder;
import com.sikulix.recorder.Utils;

public class RecordScreenRegionExample {
	
	public static void main(String[] args) {
		
		File eventDir = new File("tmp/example");
		if (eventDir.exists()){
			Utils.deleteFilesInFolder(eventDir);
		}else{
			eventDir.mkdirs();
		}
		
		Recorder recorder = new Recorder();
		//recorder.setRegionOfInterest(new DesktopScreenRegion(5,30,300,800));
		recorder.setEventDir(eventDir);
		recorder.start();
		
		
	}
}
