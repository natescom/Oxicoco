package com.nathan.res.drawables;

import java.awt.Color;
import java.awt.Graphics2D;

import com.nathan.res.values;

/**
 * Classe abstrata que desenha linhas de grade na tela
 *
 * @author Nathan
 */
public abstract class Gridlines {
  /**
   * Método utilizado para desenhar as linhas de grade
   *
   * @param g        Objeto Graphics
   * @param numlines Número de linhas
   */
  public static void draw(Graphics2D g, int numlines) {
    g.setColor(Color.red);
    for (int i = 1; i <= numlines; i++) {
      g.drawLine((values.WIDTH * i) / (numlines + 1), 0, (values.WIDTH * i) / (numlines + 1), values.HEIGHT);
      g.drawLine(0, (values.HEIGHT * i) / (numlines + 1), values.WIDTH, (values.HEIGHT * i) / (numlines + 1));
    }

  }
}
