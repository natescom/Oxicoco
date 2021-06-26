package com.nathan.res.drawables.tools.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.nathan.res.drawables.drawable;

/**
 * @author Nathan
 */
public class Text extends drawable {
  private String txt;
  private int xtemp, ytemp;
  private Font font;
  private Color color;
  private boolean toAlign;


  /**
   * @param txt     Texto do Text
   * @param x       Coordenada X
   * @param y       Coordenada Y
   * @param font    Fonte
   * @param color   Cor da Fonte
   * @param toAlign Centralizar?
   */
  public Text(String txt, int x, int y, Font font, Color color, boolean toAlign) {
    this.txt = txt;
    this.font = font;
    this.color = color;
    this.xtemp = x;
    this.ytemp = y;
    this.toAlign = toAlign;
  }

  public Text(String txt) {
    this.txt = txt;
  }

  private void align(Graphics2D g) {
    if (toAlign) {
      x = xtemp - g.getFontMetrics().stringWidth(txt) / 2;
    } else {
      x = xtemp;
    }
    y = ytemp + g.getFontMetrics().getHeight();
  }


  @Override
  public void draw(Graphics2D g) {
    g.setColor(color);
    g.setFont(font);
    align(g);
    g.drawString(txt, x, y);
  }

  //<editor-fold desc="Getters and Setters">

  public String getTxt() {
    return txt;
  }

  public void setTxt(String txt) {
    this.txt = txt;
  }

  public Font getFont() {
    return font;
  }

  public void setFont(Font font) {
    this.font = font;
  }

  public int getX() {
    return xtemp;
  }

  public void setX(int x) {
    this.xtemp = x;
  }

  public int getY() {
    return ytemp;
  }

  public void setY(int y) {
    this.ytemp = y;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }


  //</editor-fold>


}
