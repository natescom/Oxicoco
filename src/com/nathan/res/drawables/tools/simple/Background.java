package com.nathan.res.drawables.tools.simple;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.ImageIcon;

import com.nathan.res.drawables.drawable;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class Background extends drawable {
  private Color color;
  private Image backgroundImage;
  private Shape shape;

  public Background(Color c) {
    this.color = c;
  }

  /**
   * @param x     Coordenada X
   * @param y     Coordenada Y
   * @param color Cor do fundo
   * @param name  Diretorio da imagem
   */
  public Background(int x, int y, Color color, String name) {
    this.x = x;
    this.y = y;
    this.color = color;
    backgroundImage = new ImageIcon("src/com/nathan/res/sprites/" + name).getImage();
    shape = new Rectangle(x, y, backgroundImage.getWidth(null), backgroundImage.getHeight(null));
  }


  @Override
  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillRect(0, 0, values.WIDTH, values.HEIGHT);
    g.drawImage(backgroundImage, x, y, null);
  }

  public Shape getShape() {
    return shape;
  }

  public void setShape(Shape shape) {
    this.shape = shape;
  }


}

