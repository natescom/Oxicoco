package com.nathan.br.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * @author Nathan
 */
public class StateManager implements State, KeyListener {

  public static ArrayList<State> states = new ArrayList<>();
  public static int currentState = 0;

  public static void setState(int state) {
    currentState = state;
    states.get(currentState).init();
  }

  public StateManager() {
    states.add(new State_00(states.size()));
    states.add(new State_Menu(states.size()));
    states.add(new State_Inicio(states.size()));
    states.add(new State_Config(states.size()));
    states.add(new State_Snake(states.size()));
    states.add(new State_Pong(states.size()));
    states.add(new State_Breakout(states.size()));
    states.add(new State_FlappyBird(states.size()));
  }

  @Override
  public void init() {
    states.get(currentState).init();
  }

  @Override
  public void update() {
    states.get(currentState).update();
  }

  @Override
  public void render(Graphics2D g) {
    states.get(currentState).render(g);
  }


  public boolean currentStateExist() {
    return states.get(currentState) != null;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    states.get(currentState).BtnTyped(e);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    states.get(currentState).BtnPressed(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    states.get(currentState).BtnReleased(e);
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
