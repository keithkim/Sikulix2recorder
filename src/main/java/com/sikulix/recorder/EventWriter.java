package com.sikulix.recorder;

import com.sikulix.recorder.event.Event;

public interface EventWriter {
	public void write(Event event);
}