package com.nathan.res.drawables.breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.nathan.res.drawables.drawable;
import com.nathan.res.values;

/**
 * Classe que descreve a bola em BreakOut
 *
 * @author Nathan
 */
public class BoBall extends drawable {
  private Rectangle shape;
  private int width, height;
  private int DirectionX, DirectionY;
  private int velocity;

  /**
   * Construtor
   *
   * @param x               Coordenada X
   * @param y               Coordenada Y
   * @param width           Largura
   * @param height          Altura
   * @param velocity        Velocidade da bola
   * @param randomDirection Direção randomica ao spawnar
   */
  public BoBall(int x, int y, int width, int height, int velocity, boolean randomDirection) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    shape = new Rectangle(x, y, width, height);
    if (randomDirection) {
      this.DirectionX = randomDirection() * velocity;
      this.DirectionY = -1 * velocity;
    } else {
      this.DirectionX = 1 * velocity;
      this.DirectionY = 1 * velocity;
    }
    this.velocity = velocity;
  }

  /**
   * Atualiza a animação na tela. (Chame-o em update())
   *
   * @param p1   Jogador
   * @param wall Muro
   */
  public void animate(BoPlayer p1, BoWall wall) {
    shape.x += DirectionX;
    shape.y += DirectionY;
    limits(p1, wall);
  }

  @Override
  public void draw(Graphics2D g) {
    g.setColor(Color.WHITE);
    g.fillRect(shape.x - width / 2, shape.y - height / 2, width, height);
  }

  /**
   * Spawna a bola na posição inicail
   */
  private void restart() {
    DirectionX = randomDirection() * velocity;
    DirectionY = -1 * velocity;
    shape.x = x;
    shape.y = y;
  }

  /**
   * @return Retorna "1" ou "-1" que irá definir a nova direção da bola
   */
  private int randomDirection() {
    String[] valores = {"-1", "1"};
    return Integer.parseInt(valores[(int) (Math.random() * 2)]);
  }

  private void AllLimits() {
    if (shape.x > values.WIDTH) {
      DirectionX = -1 * velocity;
    }
    if (shape.y > values.HEIGHT) {
      DirectionY = -1 * velocity;
    }
    if (shape.x < 0) {
      DirectionX = 1 * velocity;
    }
    if (shape.y < 0) {
      DirectionY = 1 * velocity;
    }

  }

  /**
   * Aplica colisão
   *
   * @param p1   Usado para aplicar colisão com o jogador
   * @param wall Usado para aplicar colisão com a parede
   */
  private void limits(BoPlayer p1, BoWall wall) {
    wall.colision(this);
    if (shape.intersects(p1.getShape())) {
      DirectionY *= -1;
    }
    if (shape.y > values.HEIGHT) {
      restart();
    }
    if (shape.y < 0) {
      DirectionY = 1 * velocity;
    }
    if (shape.x > values.WIDTH) {
      DirectionX = -1 * velocity;
    }
    if (shape.x < 0) {
      DirectionX = 1 * velocity;
    }

  }

  public Rectangle getShape() {
    return shape;
  }

  public void setShape(Rectangle shape) {
    this.shape = shape;
  }

  public int getDirectionX() {
    return DirectionX;
  }

  public void setDirectionX(int DirectionX) {
    this.DirectionX = DirectionX;
  }

  public int getDirectionY() {
    return DirectionY;
  }

  public void setDirectionY(int DirectionY) {
    this.DirectionY = DirectionY;
  }


}
