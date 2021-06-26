package com.nathan.br.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.flappybird.FlappyCanvas;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class State_FlappyBird implements State {
  public static int ID;
  private FlappyCanvas teladepintura;

  public State_FlappyBird(int id) {
    ID = id;
    teladepintura = new FlappyCanvas(values.WIDTH / 2, values.HEIGHT / 2, 144, 256, true);
  }


  @Override
  public void init() {
    teladepintura.init();
  }

  @Override
  public void update() {
    teladepintura.update();
  }

  @Override
  public void render(Graphics2D g) {
    teladepintura.render(g);

  }

  @Override
  public void BtnPressed(KeyEvent e) {
    teladepintura.BtnPressed(e);
    if (e.getKeyCode() == KeyEvent.VK_X) {
      StateManager.setState(State_Inicio.ID);
    }
  }

  @Override
  public void BtnReleased(KeyEvent e) {
    teladepintura.BtnReleased(e);
  }

  @Override
  public void BtnTyped(KeyEvent e) {
    teladepintura.BtnTyped(e);
  }

}
