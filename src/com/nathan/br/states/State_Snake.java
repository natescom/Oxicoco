package com.nathan.br.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.nathan.res.drawables.tools.advanced.MenuPausa;
import com.nathan.res.drawables.tools.simple.Background;
import com.nathan.res.drawables.tools.simple.Text;
import com.nathan.res.drawables.snake.Snake;
import com.nathan.res.drawables.tools.advanced.NPC;
import com.nathan.res.font.CustomFont;
import com.nathan.res.values;

/**
 * @author natha
 */
public class State_Snake implements State {

  public static int ID;

  private Background fundo;
  private Shape limites;
  private Snake cobra;
  private ArrayList<NPC> foodes;
  private Text score;
  private MenuPausa pausa = new MenuPausa(values.WIDTH / 2, values.HEIGHT / 2, null, 0.9f, null, null, new Color(48, 98, 47), State_Inicio.ID, this);
  private boolean stop = false;

  public State_Snake(int id) {
    ID = id;
    fundo = new Background(new Color(155, 187, 14));
    limites = new Rectangle(new Dimension(values.WIDTH - 30, 240));
    cobra = new Snake(16, 100, 5, 40, limites.getBounds().height + 25, 16, limites.getBounds().width);
    score = new Text(String.valueOf(cobra.getScore()), 200, 0, CustomFont.getFont("AtariSmall.ttf", 30), new Color(15, 56, 17), true);
    foodes = new ArrayList<>();
    novafood();
  }


  private void novafood() {
    int i = 16 * (1 + (int) (Math.random() * 23));
    int j = 16 * (3 + (int) (Math.random() * 14));
    foodes.add(new NPC(i, j, "food01.png", ""));
  }


  @Override
  public void init() {
    cobra = new Snake(16, 100, 5, 40, limites.getBounds().height + 25, 16, limites.getBounds().width);
    score = new Text(String.valueOf(cobra.getScore()), 200, 0, CustomFont.getFont("AtariSmall.ttf", 30), new Color(15, 56, 17), true);
    foodes = new ArrayList<>();
    novafood();
  }

  @Override
  public void update() {
    if (!stop) {
      for (NPC npc : cobra.getTronco()) {
        if (cobra.getCabeca().getShape().intersects(npc.getShape())) {
          init();
        }
      }
      cobra.animate();
      if (!foodes.isEmpty()) {
        for (int i = 0; i < foodes.size(); i++) {
          if (cobra.getCabeca().getShape().intersects(foodes.get(i).getShape())) {
            cobra.toFeed();
            foodes.remove(i);
            novafood();
          }
        }
      }
      score.setTxt(String.valueOf(cobra.getScore()));
    }
  }

  @Override
  public void render(Graphics2D g) {
    fundo.draw(g);
    score.draw(g);

    Stroke old = g.getStroke();
    g.setStroke(new BasicStroke(3));
    g.setColor(new Color(15, 56, 17));
    g.drawRect(15, 40, limites.getBounds().width, limites.getBounds().height);
    g.setStroke(old);

    cobra.draw(g);
    if (!foodes.isEmpty()) {
      for (NPC npc : foodes) {
        npc.draw(g);
      }
    }
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
    } else {
      if (e.getKeyCode() == KeyEvent.VK_W) {
        cobra.btn_up();
      }
      if (e.getKeyCode() == KeyEvent.VK_A) {
        cobra.btn_left();
      }
      if (e.getKeyCode() == KeyEvent.VK_S) {
        cobra.btn_down();
      }
      if (e.getKeyCode() == KeyEvent.VK_D) {
        cobra.btn_right();
      }
    }
  }

  @Override
  public void BtnReleased(KeyEvent e) {
  }

  @Override
  public void BtnTyped(KeyEvent e) {
  }

}
