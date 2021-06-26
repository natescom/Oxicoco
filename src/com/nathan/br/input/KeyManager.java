package com.nathan.br.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Nathan
 */
public class KeyManager implements KeyListener {
  private boolean[] keys = new boolean[256];
  public static boolean w, a, s, d, up, down, c, x, z;

  public void update() {
    w = keys[KeyEvent.VK_W];
    a = keys[KeyEvent.VK_A];
    s = keys[KeyEvent.VK_S];
    d = keys[KeyEvent.VK_D];
    up = keys[KeyEvent.VK_UP];
    down = keys[KeyEvent.VK_DOWN];
    c = keys[KeyEvent.VK_C];
    x = keys[KeyEvent.VK_X];
    z = keys[KeyEvent.VK_Z];
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() < 0 || e.getKeyCode() > 255) {
    } else {
      keys[e.getKeyCode()] = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() < 0 || e.getKeyCode() > 255) {
    } else {
      keys[e.getKeyCode()] = false;
    }
  }

}
