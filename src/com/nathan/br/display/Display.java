package com.nathan.br.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 * @author Nathan
 */
public class Display {
  private JFrame jframe;
  private Canvas canvas;

  public Display(String title, int width, int height) {

    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(width, height));
    canvas.setMaximumSize(new Dimension(width, height));
    canvas.setMinimumSize(new Dimension(width, height));
    canvas.setFocusable(false);


    jframe = new JFrame(title);
    jframe.add(canvas);
    jframe.pack();

    jframe.setVisible(true);
    jframe.setLocationRelativeTo(null);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.setResizable(false);


  }

  public JFrame getJframe() {
    return jframe;
  }

  public void setJframe(JFrame jframe) {
    this.jframe = jframe;
  }

  public BufferStrategy getBufferStrategy() {
    return canvas.getBufferStrategy();
  }

  public void createBufferStrategy() {
    canvas.createBufferStrategy(3);
  }

  public void setKeyListener(KeyListener k1) {
    jframe.addKeyListener(k1);
  }

}
