package com.nathan.br.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.tools.simple.TPScounter;

/**
 * @author Nathan
 */
public class State_00 implements State {
  public static int ID;
  private TPScounter tps;

  public State_00(int id) {
    ID = id;
  }


  @Override
  public void init() {
    tps = new TPScounter();
  }

  @Override
  public void update() {
    tps.animate();
  }

  @Override
  public void render(Graphics2D g) {
    tps.draw(g);
  }


  @Override
  public void BtnPressed(KeyEvent e) {

  }

  @Override
  public void BtnReleased(KeyEvent e) {
  }

  @Override
  public void BtnTyped(KeyEvent e) {
  }


}
