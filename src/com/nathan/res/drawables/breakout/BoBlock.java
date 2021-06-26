package com.nathan.res.drawables.breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.nathan.res.drawables.drawable;
import com.nathan.res.values;

/**
 * Classe que descreve os blocos em Breakout
 *
 * @author Nathan
 */
public class BoBlock extends drawable {
  private Rectangle shape;
  public static int width = (values.WIDTH / 10), height = values.HEIGHT / 20;
  private Color color;
  private boolean breakstatus;

  /**
   * @param x     Coordenada X
   * @param y     Coordenada Y
   * @param color Cor do bloco
   */
  public BoBlock(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.breakstatus = false;
    shape = new Rectangle(x, y, width, height);
  }

  public void animate() {
    if (breakstatus) {

    }
  }

  @Override
  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fill(shape);
    g.setColor(g.getColor().darker());
    g.draw(shape);
  }

  public Rectangle getShape() {
    return shape;
  }

  public void setShape(Rectangle shape) {
    this.shape = shape;
  }


}
