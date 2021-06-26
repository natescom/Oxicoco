package com.nathan.res.drawables.flappybird;

import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nathan.res.conduct;
import com.nathan.res.drawables.tools.advanced.NPC;

/**
 * @author Nathan
 */
public class Bird extends NPC {
  private int score = 0;
  private float ACCELERATION = 0.5f;
  private ArrayList<NPC> graphicScore;
  private int scoreX, scoreY;

  public Bird(int x, int y, String name, int width, int heitgh, int numSpritesX, int numSpritesY, int velocity, String comportamento, int scoreX, int scoreY) {
    super(x, y, name, width, heitgh, numSpritesX, numSpritesY, velocity, comportamento);
    this.scoreX = scoreX;
    this.scoreY = scoreY;
    makeGraphicScore();
  }


  @Override
  protected void initConduct(String comportamento) {
    try {
      this.behavior = conduct.class.getDeclaredMethod(comportamento, Bird.class);
    } catch (SecurityException | NoSuchMethodException ex) {
      Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


  public void animate() {
    try {
      behavior.invoke(this, this);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


  private void makeGraphicScore() {
    graphicScore = new ArrayList();
    int temp = score;
    do {
      //<editor-fold desc="IFs">
      if (temp % 10 == 0) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num00.png", ""));
      }
      if (temp % 10 == 1) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num01.png", ""));
      }
      if (temp % 10 == 2) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num02.png", ""));
      }
      if (temp % 10 == 3) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num03.png", ""));
      }
      if (temp % 10 == 4) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num04.png", ""));
      }
      if (temp % 10 == 5) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num05.png", ""));
      }
      if (temp % 10 == 6) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num06.png", ""));
      }
      if (temp % 10 == 7) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num07.png", ""));
      }
      if (temp % 10 == 8) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num08.png", ""));
      }
      if (temp % 10 == 9) {
        graphicScore.add(new NPC(scoreX, scoreY, "flappybird/numbers/num09.png", ""));
      }
      //</editor-fold>
      temp /= 10;
    } while (temp >= 1);

    if (graphicScore.size() > 1) {
      for (int i = 0; i < graphicScore.size(); i++) {
        graphicScore.get(graphicScore.size() - 1 - i).setX(graphicScore.get(0).getX() + (i * 13) - (3 * graphicScore.size()));
      }
    }
  }


  public void drawScore(Graphics2D g) {
    graphicScore.forEach((npc) -> {
      npc.draw(g);
    });
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
    makeGraphicScore();
  }

  public float getACCELERATION() {
    return ACCELERATION;
  }

  public void setACCELERATION(float ACCELERATION) {
    this.ACCELERATION = ACCELERATION;
  }


}
