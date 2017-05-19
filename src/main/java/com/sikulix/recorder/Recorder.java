package com.sikulix.recorder;

import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.sikulix.hook.NativeHook;
import com.sikulix.hook.NativeHookLog;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import com.sikulix.recorder.detector.EventDetector;
import com.sikulix.recorder.detector.MouseEventDetector;
import com.sikulix.recorder.detector.ScreenshotEventDetector;

import com.google.common.collect.Lists;


public class Recorder {

  static NativeHookLog logger = new NativeHookLog("Recorder");

  public Recorder() {
    EventDetector d1 = new MouseEventDetector();
    EventDetector d2 = new ScreenshotEventDetector();
    addEventDetector(d1);
    addEventDetector(d2);
  }

  public File getEventDir() {
    return writer.getEventDir();
  }

  public void setEventDir(File dir) {
    writer.setEventDir(dir);

  }

  private List<EventDetector> detectors = Lists.newArrayList();


  DefaultEventWriter writer = new DefaultEventWriter();

  public void addEventDetector(EventDetector d) {
    d.setWriter(writer);
    detectors.add(d);
  }

  public void startRecording() {
    logger.trace("Recording is started");
    for (EventDetector d : detectors) {
      d.start();
    }
  }

  CountDownLatch escapeSignal = new CountDownLatch(1);

  public void stopRecording() {
    for (EventDetector d : detectors) {
      d.stop();
    }
  }

  NativeHook GlobalScreen = null;

  public void start() {

    GlobalScreen = NativeHook.start();
    if (null != GlobalScreen) {
      startRecording();
      try {
        escapeSignal.await();
      } catch (InterruptedException e) {
      }

      GlobalScreen.stop();
      stopRecording();

      logger.trace("Recording is stopped.");
    } else {
      logger.error("NativeHook could not be activated");
    }
  }

  boolean isWindows() {
    String currentOs = System.getProperty("os.name");
    return currentOs.toLowerCase().contains("win");
  }

  class HotKeyListener implements NativeKeyListener {

    //private Logger logger = LoggerFactory.getLogger(HotKeyListener.class);

    public void nativeKeyPressed(NativeKeyEvent e) {

      boolean isMetaPressed = (e.getModifiers() & NativeKeyEvent.META_MASK) > 0;
      boolean isAltPressed = (e.getModifiers() & NativeKeyEvent.ALT_MASK) > 0;
      boolean isShiftPressed = (e.getModifiers() & NativeKeyEvent.SHIFT_MASK) > 0;
      boolean isCtrlPressed = (e.getModifiers() & NativeKeyEvent.CTRL_MASK) > 0;

      if (isWindows()) {

        // ALT+SHIFT+2
        if (e.getKeyCode() == NativeKeyEvent.VC_2 && isShiftPressed && isAltPressed) {
          logger.trace("ALT+SHIFT+2 is pressed");
          startRecording();
        }

        // ALTL+SHIFT+ESC
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && isShiftPressed && isAltPressed) {
          logger.trace("ALT+SHIFT+ESC is pressed");
          escapeSignal.countDown();
        }

      } else {
        // CTRL+SHIFT+2
        if (e.getKeyCode() == NativeKeyEvent.VC_2 && isShiftPressed && isCtrlPressed) {
          logger.trace("CTRL+SHIFT+2 is pressed");
          startRecording();
        }

        // CTRL+SHIFT+ESC
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && isShiftPressed && isCtrlPressed) {
          logger.trace("CTRL+SHIFT+ESC is pressed");
          escapeSignal.countDown();
        }
      }

    }

    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

  }
}