package com.nathan.br.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.breakout.BoBall;
import com.nathan.res.drawables.breakout.BoPlayer;
import com.nathan.res.drawables.breakout.BoWall;
import com.nathan.res.drawables.tools.advanced.MenuPausa;
import com.nathan.res.drawables.tools.simple.Background;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class State_Breakout implements State {
  public static int ID;
  private Background fundo;
  private BoBall bola;
  private BoPlayer player;
  private MenuPausa pausa;
  private BoWall parede;


  private boolean stop;

  public State_Breakout(int ID) {
    this.ID = ID;
    fundo = new Background(Color.BLACK);
    player = new BoPlayer(0, values.HEIGHT - 15, Color.white);
    parede = new BoWall(0, 0, 119);
    bola = new BoBall(values.WIDTH / 2, values.HEIGHT - 22, 10, 10, 3, true);

    pausa = new MenuPausa(values.WIDTH / 2, values.HEIGHT / 2, null, 0.9f, null, null, null, State_Inicio.ID, this);
    stop = false;
  }


  @Override
  public void init() {
    player = new BoPlayer(0, values.HEIGHT - 15, Color.white);
    parede = new BoWall(0, 0, 119);
    bola = new BoBall(values.WIDTH / 2, values.HEIGHT - 22, 10, 10, 3, true);
  }

  @Override
  public void update() {
    if (!stop) {
      bola.animate(player, parede);
      player.animate();
    }

  }

  @Override
  public void render(Graphics2D g) {
    fundo.draw(g);
    bola.draw(g);
    player.draw(g);
    parede.draw(g);

    pausa.draw(g);

  }

  @Override
  public void BtnPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_X) {
      stop = true;
      pausa.doit();
    }
    if (stop) {
      stop = pausa.input(e);
    }
  }

  @Override
  public void BtnReleased(KeyEvent e) {
  }

  @Override
  public void BtnTyped(KeyEvent e) {
  }

}
