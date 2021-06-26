package com.nathan.res.drawables.tools.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.nathan.res.drawables.drawable;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class TPScounter extends drawable {
  private long now, lastTime;
  private double timer;
  private int tick;
  private int t;

  public TPScounter() {
    lastTime = System.nanoTime();
    timer = 0;
    tick = 0;
    t = 0;
  }

  public void animate() {
    now = System.nanoTime();
    timer += now - lastTime;
    lastTime = now;
    tick++;
  }

  @Override
  public void draw(Graphics2D g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, values.WIDTH, values.HEIGHT);

    if (timer >= 1000000000) {
      t = tick;
      tick = 0;
      timer = 0;
    }

    g.setColor(Color.WHITE);
    Font font = new Font("Serif", Font.PLAIN, 12);
    g.setFont(font);

    String txt = "TPS: " + t;
    if (t == 0) {
      txt = "TPS: 00";
    }
    g.drawString(txt, g.getFontMetrics().stringWidth(txt), g.getFontMetrics(font).getHeight());
  }
}
