package com.sikulix.recorder.detector;

import com.sikulix.hook.NativeHookCallback;
import com.sikulix.recorder.DefaultEventWriter;
import com.sikulix.recorder.EventWriter;
import com.sikulix.recorder.event.Event;
import org.jnativehook.NativeInputEvent;

import java.util.EventObject;

public class EventDetector extends NativeHookCallback{
	private EventWriter writer;

	public EventDetector(){
		writer = new DefaultEventWriter();
	}
	
	public void eventDetected(Event event){
		if (writer != null)
			writer.write(event);
	}

	public void callback(NativeInputEvent evt) {
	}
	
	public void start(){		
	}

	public void stop(){		
	}
	
	public void setWriter(EventWriter writer) {
		this.writer = writer;
	}
}