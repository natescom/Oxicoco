package com.nathan.br.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.tools.simple.Background;
import com.nathan.res.drawables.tools.simple.Text;
import com.nathan.res.drawables.tools.advanced.MenuPausa;
import com.nathan.res.drawables.pong.PongBall;
import com.nathan.res.drawables.pong.PongPlayer;
import com.nathan.res.font.CustomFont;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class State_Pong implements State {

  public static int ID;

  private Background fundo = new Background(Color.black);
  private Text ScoreP1 = new Text("0", 3 * values.WIDTH / 4, 0, CustomFont.getFont("Pixel Digivolve.otf", 48), Color.white, true);
  private Text ScoreCOM = new Text("0", values.WIDTH / 4, 0, CustomFont.getFont("Pixel Digivolve.otf", 48), Color.white, true);

  private PongBall bola = new PongBall(values.WIDTH / 2, values.HEIGHT / 2, 15, 15, 3, true);
  private PongPlayer p1 = new PongPlayer(0, 0, Color.white);
  private PongPlayer com = new PongPlayer(values.WIDTH - PongPlayer.WIDTH, 0, Color.white);

  private MenuPausa pausa = new MenuPausa(values.WIDTH / 2, values.HEIGHT / 2, null, 0.9f, null, null, null, State_Inicio.ID, this);
  private boolean stop = false;

  public State_Pong(int id) {
    ID = id;
  }


  @Override
  public void init() {
    bola = new PongBall(values.WIDTH / 2, values.HEIGHT / 2, 15, 15, 3, true);
    p1 = new PongPlayer(0, 0, Color.white);
    com = new PongPlayer(values.WIDTH - PongPlayer.WIDTH, 0, Color.white);
  }

  @Override
  public void update() {
    if (!stop) {
      bola.animate(p1, com);
      p1.animate();
      com.animateIA(bola);
      ScoreP1.setTxt(String.valueOf(p1.getScore()));
      ScoreCOM.setTxt(String.valueOf(com.getScore()));
    }
  }

  @Override
  public void render(Graphics2D g) {

    fundo.draw(g);
    ScoreP1.draw(g);
    ScoreCOM.draw(g);
    bola.draw(g);
    p1.draw(g);
    com.draw(g);
    g.fillRect((values.WIDTH / 2) - values.WIDTH / 80, 0, values.WIDTH / 40, values.HEIGHT);

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
