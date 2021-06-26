package com.nathan.br;

import com.nathan.br.display.Display;
import com.nathan.br.input.KeyManager;
import com.nathan.br.states.StateManager;

import java.awt.Dimension;

import com.nathan.res.values;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nathan
 */
public class Game implements Runnable {

  private Display display;
  private Thread thread;
  private boolean running = false;

  private StateManager sm;
  private KeyManager km;

  public Game() {
    display = new Display(values.TITLE, values.WIDTH, values.HEIGHT);
    sm = new StateManager();
    km = new KeyManager();
    display.setKeyListener(sm);
    display.setKeyListener(km);
    sm.setState(1);
    start();
  }

  @Override
  public void run() {
    init();
    double timePerTick = 1000000000 / values.FPS;
    double delta = 0;
    long now;
    long lastTime = System.nanoTime();

    while (running) {
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick;
      lastTime = now;

      if (delta >= 1) {
        update();
        render();

        //System.out.println(delta);
        delta--;
      }
    }
    stop();
  }

  public synchronized void start() {
    if (thread != null) return;
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    if (thread == null) return;
    try {
      thread.join();
    } catch (InterruptedException ex) {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void init() {
    sm.init();
    values.CURRENT_WIDTH = values.WIDTH;
    values.CURRENT_HEIGHT = values.HEIGHT;
  }

  private void update() {
    if (!sm.currentStateExist()) return;

    if (values.CURRENT_HEIGHT != values.HEIGHT * values.getScale()) {
      values.CURRENT_WIDTH = values.WIDTH * values.getScale();
      values.CURRENT_HEIGHT = values.HEIGHT * values.getScale();
      display.getJframe().setSize(new Dimension((values.CURRENT_WIDTH + 5 * values.getScale()), values.CURRENT_HEIGHT + 13 * values.getScale()));
      if (values.getScale() == 1) {
        display.getJframe().pack();
      }
      display.getJframe().setLocationRelativeTo(null);
    }

    sm.update();
    km.update();


  }

  private void render() {
    BufferStrategy bs = display.getBufferStrategy();
    if (bs == null) {
      display.createBufferStrategy();
      bs = display.getBufferStrategy();
    }
    Graphics2D g = (Graphics2D) bs.getDrawGraphics();
    g.clearRect(0, 0, values.WIDTH, values.HEIGHT);


    g.scale(values.CURRENT_WIDTH / values.WIDTH, values.CURRENT_HEIGHT / values.HEIGHT);
    if (sm.currentStateExist()) sm.render(g);

    g.dispose();
    bs.show();
  }

}
