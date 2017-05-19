package com.sikulix.recorder.examples;

import java.io.File;

import com.sikulix.recorder.html.HTMLGenerator;

public class HTMLGeneratorExample {
	
	public static void main(String[] args) {
		
		File eventDir = new File("tmp/example");
		eventDir.mkdirs();
		HTMLGenerator.generate(eventDir, new File("tmp/html"));
		
	}
}
