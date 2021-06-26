package com.nathan.res.drawables.snake;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.nathan.res.drawables.drawable;
import com.nathan.res.drawables.tools.advanced.NPC;
import com.nathan.res.values;

/**
 * @author natha
 */
public class Snake extends drawable {
  private int Score;
  private NPC cabeca;
  private ArrayList<NPC> tronco;
  private ArrayList shadowX, shadowY, shadowAngle;
  private int limitUP, limitDOWN, limitLEFT, limitRIGHT;

  /**
   * @param x          Coordenada X
   * @param y          Coordenada Y
   * @param tam        Tamanho do corpo
   * @param limitup    Limite de cima
   * @param limitdown  Limite de baixo
   * @param limitright Limite da direita
   * @param limitleft  Limite da esquerda
   */
  public Snake(int x, int y, int tam, int limitup, int limitdown, int limitright, int limitleft) {
    this.x = x;
    this.y = y;
    this.limitUP = limitup;
    this.limitDOWN = limitdown;
    this.limitLEFT = limitleft;
    this.limitRIGHT = limitright;
    cabeca = new NPC(x, y, "snake01.png", "");
    tronco = new ArrayList<>();
    shadowX = new ArrayList();
    shadowY = new ArrayList();
    shadowAngle = new ArrayList();
    bodySize(tam);

  }

  private void bodySize(int n) {
    for (int i = 0; i < n; i++) {
      shadowX.add(cabeca.getX() - cabeca.getWidth() * (tronco.size() + 1));
      shadowY.add(cabeca.getY());
      shadowAngle.add(cabeca.getAngle());
      tronco.add(new NPC(-50, -50, "snake02.png", ""));
    }
  }

  public void toFeed() {
    shadowX.add(tronco.get(tronco.size() - 1).getX());
    shadowY.add(tronco.get(tronco.size() - 1).getY());
    shadowAngle.add(tronco.get(tronco.size() - 1).getAngle());
    tronco.add(new NPC((int) shadowX.get(0), (int) shadowY.get(0), "snake02.png", ""));
    Score++;
  }

  public void btn_up() {
    if (x != (int) shadowX.get(shadowX.size() - 1)) {
      cabeca.setAngle(270);
    }
  }

  public void btn_left() {
    if (y != (int) shadowY.get(shadowY.size() - 1)) {
      cabeca.setAngle(180);
    }
  }

  public void btn_down() {
    if (x != (int) shadowX.get(shadowX.size() - 1)) {
      cabeca.setAngle(90);
    }
  }

  public void btn_right() {
    if (y != (int) shadowY.get(shadowY.size() - 1)) {
      cabeca.setAngle(0);
    }
  }


  private void updateCoord() {
    cabeca.setX(x);
    cabeca.setY(y);
    for (int i = 0; i < tronco.size(); i++) {
      tronco.get(i).setX((int) shadowX.get(i));
      tronco.get(i).setY((int) shadowY.get(i));
      tronco.get(i).setAngle((int) shadowAngle.get(i));
    }
  }

  private int ticks = -1;
  private int velocity = 5;

  private void limits() {
    if ((cabeca.getAngle() / 10) % 2 == 0) {
      if (cabeca.getAngle() == 0) {
        x += cabeca.getWidth();
        if (x > limitLEFT) {
          x = limitRIGHT;
        }
      } else {
        x -= cabeca.getWidth();
        if (x < limitRIGHT) {
          x = limitLEFT;
        }
      }
    } else {
      if (cabeca.getAngle() == 90 || cabeca.getAngle() == -270) {
        y += cabeca.getWidth();
        if (y > limitDOWN) {
          y = limitUP;
        }
      } else {
        y -= cabeca.getHeitgh();
        if (y < limitUP) {
          y = limitDOWN;
        }
      }
    }
  }

  public void animate() {
    ticks++;
    if (ticks >= values.FPS / velocity) {
      shadowX.add(x);
      shadowX.remove(0);
      shadowY.add(y);
      shadowY.remove(0);
      shadowAngle.add(cabeca.getAngle());
      shadowAngle.remove(0);
      limits();
      ticks = 0;
      updateCoord();
    }
  }


  @Override
  public void draw(Graphics2D g) {
    for (int i = 0; i < tronco.size(); i++) {
      tronco.get(i).draw(g);
    }
    cabeca.draw(g);

  }

  public NPC getCabeca() {
    return cabeca;
  }

  public void setCabeca(NPC cabeca) {
    this.cabeca = cabeca;
  }

  public int getScore() {
    return Score;
  }

  public void setScore(int Score) {
    this.Score = Score;
  }

  public ArrayList<NPC> getTronco() {
    return tronco;
  }

  public void setTronco(ArrayList<NPC> tronco) {
    this.tronco = tronco;
  }


}
