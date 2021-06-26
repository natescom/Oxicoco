package com.nathan.res.drawables.flappybird;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.nathan.res.drawables.drawable;
import com.nathan.res.drawables.tools.advanced.NPC;

/**
 * @author Nathan
 */
public class Pipes extends drawable {
  private NPC pipeUp;
  private NPC pipeDown;
  private Rectangle c;

  public Pipes(int x, int y) {
    this.x = x;
    this.y = y + (int) (30 + Math.random() * 80);
    int abertura = 60;
    pipeUp = new NPC(x, this.y - 160, "flappybird/FB_pipe02.png", "");
    pipeDown = new NPC(x, this.y + abertura, "flappybird/FB_pipe01.png", "");
    c = new Rectangle(this.x + 13, this.y, 0, abertura);
  }


  @Override
  public void draw(Graphics2D g) {
    g.setColor(Color.red);
    //g.draw(c);
    pipeUp.draw(g);
    pipeDown.draw(g);
  }

  public void animate() {
    pipeDown.setX(x);
    pipeUp.setX(x);
    c.setBounds(x + 13, c.y, 0, c.height);
    x--;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public NPC getPipeUp() {
    return pipeUp;
  }

  public void setPipeUp(NPC pipeUp) {
    this.pipeUp = pipeUp;
  }

  public NPC getPipeDown() {
    return pipeDown;
  }

  public void setPipeDown(NPC pipeDown) {
    this.pipeDown = pipeDown;
  }


}
