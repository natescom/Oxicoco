package com.nathan.res.drawables.breakout;

import com.nathan.br.input.KeyManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.nathan.res.values;

/**
 * Classe que descreve o player em Breakout
 *
 * @author Nathan
 */
public class BoPlayer {
  private Rectangle shape;
  private Color color;
  private int score;

  public static final int WIDTH = values.WIDTH / 6;
  public static final int HEIGHT = values.HEIGHT / 30;

  public BoPlayer(int x, int y, Color color) {
    this.color = color;
    this.score = 0;
    shape = new Rectangle(x, y, WIDTH, HEIGHT);
  }

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillRect(shape.x, shape.y, shape.width, shape.height);
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void animate() {
    int velocity = 8;
    if (KeyManager.a) {
      if (shape.x > 0) {
        shape.x -= velocity;
      }
    }
    if (KeyManager.d) {
      if (shape.x < -WIDTH + values.WIDTH) {
        shape.x += velocity;
      }
    }
  }

  public Rectangle getShape() {
    return shape;
  }

  public void setShape(Rectangle shape) {
    this.shape = shape;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

}
