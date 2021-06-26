package com.nathan.res.drawables.breakout;

import java.awt.Color;

/**
 * Classe abstrata que define cores para os blocos
 *
 * @author Nathan
 */
public abstract class BoBlockColors {
  private static int i = 0;
  private static final Color[] color = new Color[]{new Color(235, 128, 113),
      new Color(255, 202, 134),
      new Color(254, 255, 136),
      new Color(170, 255, 151),
      new Color(160, 172, 255),
      new Color(168, 130, 221)};

  /**
   * Altera a cor atual
   */
  public static void change() {
    i++;
    if (i > 5) {
      i = 0;
    }
  }

  /**
   * @return Cor atual
   */
  public static Color lgbt() {
    return color[i];
  }
}
