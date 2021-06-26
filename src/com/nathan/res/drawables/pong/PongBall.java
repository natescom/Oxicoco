package com.nathan.res.drawables.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.nathan.res.drawables.drawable;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class PongBall extends drawable {
  private Rectangle shape;
  private int width, height;
  private int moveX, moveY;
  private int velocity;

  public PongBall(int x, int y, int width, int height, int velocity, boolean randomDirection) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    shape = new Rectangle(x, y, width, height);
    if (randomDirection) {
      this.moveX = randomDirection() * velocity;
      this.moveY = randomDirection() * velocity;
    } else {
      this.moveX = 1 * velocity;
      this.moveY = 1 * velocity;
    }
    this.velocity = velocity;
  }

  public void animate(PongPlayer p1, PongPlayer p2) {
    shape.x += moveX;
    shape.y += moveY;
    limits(p1, p2);
  }

  @Override
  public void draw(Graphics2D g) {
    g.setColor(Color.WHITE);
    g.fillRect(shape.x - width / 2, shape.y - height / 2, width, height);
  }

  private void restart() {
    moveX = randomDirection() * velocity;
    moveY = randomDirection() * velocity;
    shape.x = x;
    shape.y = y;
  }

  private int randomDirection() {
    String[] valores = {"-1", "1"};
    return Integer.parseInt(valores[(int) (Math.random() * 2)]);
  }

  private void AllLimits() {
    if (shape.x > values.WIDTH) {
      moveX = -1 * velocity;
    }
    if (shape.y > values.HEIGHT) {
      moveY = -1 * velocity;
    }
    if (shape.x < 0) {
      moveX = 1 * velocity;
    }
    if (shape.y < 0) {
      moveY = 1 * velocity;
    }

  }

  private void limits(PongPlayer p1, PongPlayer p2) {
    if (shape.intersects(p1.getShape()) || shape.intersects(p2.getShape())) {
      moveX *= -1;
    }
    if (shape.y > values.HEIGHT) {
      moveY = -1 * velocity;
    }
    if (shape.y < 0) {
      moveY = 1 * velocity;
    }
    if (shape.x > values.WIDTH) {
      p2.setScore(p2.getScore() + 1);
      restart();
    }
    if (shape.x < 0) {
      p1.setScore(p1.getScore() + 1);
      restart();
    }

  }

  public Rectangle getShape() {
    return shape;
  }

  public void setShape(Rectangle shape) {
    this.shape = shape;
  }

  public void setMoveX(int moveX) {
    this.moveX = moveX;
  }


}
