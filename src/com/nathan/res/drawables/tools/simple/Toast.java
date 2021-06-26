package com.nathan.res.drawables.tools.simple;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.nathan.res.values;

/**
 * @author Nathan
 */
public class Toast {
  private final String txt;
  private final int x = 0;
  private final int y = values.HEIGHT / 2;
  private final int width = values.WIDTH;
  private final int height = values.HEIGHT / 9;

  public Toast(String txt, int duration) {
    this.txt = txt;
    this.duration = duration * values.FPS;
  }


  private int CenterY(int y1, int y2, int tam) {
    return ((y1 + y2) / 2) + (tam / 4);
  }


  private float alpha = 0.0f;
  private boolean drawing = false;
  private final int duration;
  private int cronometro;

  public void draw(Graphics2D g) {
    if (drawing) {
      if (cronometro == duration) {
        if (alpha <= 0.9f) {
          alpha += 0.1f;
        } else {
          cronometro--;
        }
      }
      if (cronometro < duration && cronometro > 0) {
        cronometro--;
      }
      if (cronometro == 0) {
        alpha -= 0.1f;
        if (alpha <= 0) {
          alpha = 0;
          drawing = false;
        }
      }

      g.setColor(new Color(204, 231, 232));
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
      g.fillRect(x, y, width, height);
      g.setColor(new Color(4, 47, 102));
      g.setFont(new Font("Dialog", Font.BOLD, 15));
      g.drawString(txt, (width - g.getFontMetrics().stringWidth(txt)) / 2, CenterY(y, height + y, g.getFontMetrics().getHeight()));
    }
  }

  public void make() {
    cronometro = duration;
    drawing = true;
  }
}
