package com.nathan.res.drawables.tools.advanced;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import com.nathan.res.conduct;
import com.nathan.res.drawables.drawable;
import com.nathan.res.values;

/**
 * Classe que instancia NPCs na tela
 *
 * @author Nathan
 */
public class NPC extends drawable {

  protected Image sprite;  // Imagem
  protected Rectangle shape; // Forma da imagem
  protected int width, heitgh; // Largura e altura do sprite
  protected int numSpritesX, numSpritesY; // Numero de sprites da imagem horizontalmente e verticalmente
  protected int sx = 0, sy = 0; // Coordenadas no arquivo de imagem
  protected int spriteVeloticy;   // Velocidade da passagem de sprites
  protected Method behavior; // Comportamento do npc na tela
  protected int angle = 0; // Angulo de rotação
  protected float alpha = 1f; // Transparencia

  /**
   * Construtor simples - use-o para instanciar e imprimir imagens na tela
   *
   * @param x             Coordenada X
   * @param y             Coordenada Y
   * @param name          Nome do arquivo em src/res/sprites/
   * @param comportamento Coloque o nome do metodo que será chamado, se não tiver comportamento deixe aspas vazias
   */
  public NPC(int x, int y, String name, String comportamento) {
    this.x = x;
    this.y = y;
    sprite = new ImageIcon("src/com/nathan/res/sprites/" + name).getImage();
    width = sprite.getWidth(null);
    heitgh = sprite.getHeight(null);
    if (!comportamento.equals("")) {
      initConduct(comportamento);
    }
  }

  /**
   * Construtor complexo - utilize para instantiar npc e definir seu comportamento na tela
   *
   * @param x             Coordenada X
   * @param y             Coordenada Y
   * @param name          Nome do arquivo em src/res/sprites/
   * @param width         Largura do sprite em pixel
   * @param heitgh        Altura do sprite em pixel
   * @param numSpritesX   Número de sprites na imagem horizontalmente
   * @param numSpritesY   Número de sprites na imegem verticalmente
   * @param velocity      Velocidade na passagem de sprites
   * @param comportamento Passe o nome do metodo presente em res/conduct.java que corresponde ao comportamento do NPC
   */
  public NPC(int x, int y, String name, int width, int heitgh, int numSpritesX, int numSpritesY, int velocity, String comportamento) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.heitgh = heitgh;
    this.numSpritesX = numSpritesX;
    this.numSpritesY = numSpritesY;
    sprite = new ImageIcon("src/com/nathan/res/sprites/" + name).getImage();
    if (velocity == 0) {
      this.spriteVeloticy = values.DEFAULT_SPRITE_VELOCITY;
    } else {
      this.spriteVeloticy = velocity;
    }

    initConduct(comportamento);
  }

  protected void initConduct(String comportamento) {
    try {
      this.behavior = conduct.class.getDeclaredMethod(comportamento, NPC.class);
    } catch (SecurityException | NoSuchMethodException ex) {
      Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  protected int ticks = 0;
  protected int nextSpriteX = 0;
  protected int nextSpriteY = 0;

  /**
   * Troca o sprite a ser desenhado na tela
   *
   * @param i Número do sprite horizontal
   * @param j Número do sprite vertical
   */
  public void updateSprite(int i, int j) {
    if (i > numSpritesX) {
      i = i % numSpritesX;
    }
    if (j > numSpritesY) {
      j = j % numSpritesY;
    }
    sx = width * i;
    sy = heitgh * j;
  }

  public void updateSprite(int i, int j, int width, int heitgh) {
    if (i > numSpritesX) {
      i = i % numSpritesX;
    }
    if (j > numSpritesY) {
      j = j % numSpritesY;
    }
    sx = width * i;
    sy = heitgh * j;
  }


  /**
   * Atualiza as animações da tela (chame-o me update())
   */
  public void animate() {
    try {
      behavior.invoke(this, this);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void draw(Graphics2D g) {
    AffineTransform old = g.getTransform();
    g.rotate(Math.toRadians(angle), x + width / 2, y + heitgh / 2);
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    g.drawImage(sprite, x, y, x + width, y + heitgh, sx, sy, sx + width, sy + heitgh, null);
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    g.setTransform(old);
  }


  public int getTicks() {
    return ticks;
  }

  public void setTicks(int ticks) {
    this.ticks = ticks;
  }

  public int getNextSpriteX() {
    if (nextSpriteX >= numSpritesX) {
      nextSpriteX = 0;
    }
    return nextSpriteX;
  }

  public int getNextSpriteXwithout() {
    return nextSpriteX;
  }

  public void setNextSpriteX(int nextSpriteX) {
    this.nextSpriteX = nextSpriteX;
  }

  public int getNextSpriteY() {
    if (nextSpriteY >= numSpritesY) {
      nextSpriteY = 0;
    }
    return nextSpriteY;
  }

  public void setNextSpriteY(int nextSpriteY) {
    this.nextSpriteY = nextSpriteY;
  }

  public int getVelocity() {
    return spriteVeloticy;
  }

  public void setVelocity(int velocity) {
    this.spriteVeloticy = velocity;
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

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeitgh() {
    return heitgh;
  }

  public void setHeitgh(int heitgh) {
    this.heitgh = heitgh;
  }

  public int getNumSpritesX() {
    return numSpritesX;
  }

  public void setNumSpritesX(int numSpritesX) {
    this.numSpritesX = numSpritesX;
  }

  public int getNumSpritesY() {
    return numSpritesY;
  }

  public void setNumSpritesY(int numSpritesY) {
    this.numSpritesY = numSpritesY;
  }

  public int getAngle() {
    return angle;
  }

  public void setAngle(int angle) {
    this.angle = angle;
  }

  public Rectangle getShape() {
    return shape = new Rectangle(x, y, width, heitgh);
  }

  public void setShape(Rectangle shape) {
    this.shape = shape;
  }

  public float getAlpha() {
    return alpha;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
  }


}
