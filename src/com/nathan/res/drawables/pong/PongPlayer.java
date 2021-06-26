package com.nathan.res.drawables.pong;

import com.nathan.br.input.KeyManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.nathan.res.values;

/**
 * @author Nathan
 */
public class PongPlayer {
  private Rectangle shape;
  private Color color;
  private int score;

  public static final int WIDTH = values.WIDTH / 40;
  public static final int HEIGHT = values.HEIGHT / 4;

  public PongPlayer(int x, int y, Color color) {
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


  public void animateIA(PongBall ball) {
    shape.y = ball.getShape().y - 45;
  }


  public void animate() {
    int velocity = 8;
    if (KeyManager.w) {
      if (shape.y > 0) {
        shape.y -= velocity;
      }
    }
    if (KeyManager.s) {
      if (shape.y < -HEIGHT + values.HEIGHT) {
        shape.y += velocity;
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
