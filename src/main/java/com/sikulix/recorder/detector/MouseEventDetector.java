package com.sikulix.recorder.detector;

import com.sikulix.hook.NativeHookCallback;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import com.sikulix.recorder.event.ClickEvent;

public class MouseEventDetector extends EventDetector {

  public void callback(NativeInputEvent inputEvent) {
    if (inputEvent instanceof NativeMouseEvent) {
      NativeMouseEvent e = (NativeMouseEvent) inputEvent;
      ClickEvent event = new ClickEvent();
      event.setX(e.getX());
      event.setY(e.getY());
      event.setButton(e.getButton());
      event.setClickCount(e.getClickCount());
      eventDetected(event);
    }
  }

  public void start() {
  }

  public void stop() {
  }


}