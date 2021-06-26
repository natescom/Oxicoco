package com.nathan.res.drawables.breakout;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.nathan.res.drawables.drawable;

/**
 * Classe que descreve a parede de blocos em Breakout
 *
 * @author Nathan
 */
public class BoWall extends drawable {
  private ArrayList<BoBlock> parede;

  /**
   * @param x         Coordenada X
   * @param y         Coordenada Y
   * @param numBlocks Quantidade de blocos na parede
   */
  public BoWall(int x, int y, int numBlocks) {
    this.x = x;
    this.y = y;
    parede = new ArrayList<>();
    int Xposition = 0;
    int Yposition = 0;
    for (int i = 0; i <= numBlocks; i++) {
      parede.add(new BoBlock(BoBlock.width * Xposition, BoBlock.height * Yposition, BoBlockColors.lgbt()));
      Xposition++;
      if (Xposition >= 10) {
        BoBlockColors.change();
        Yposition++;
        Xposition = 0;
      }
    }
  }

  @Override
  public void draw(Graphics2D g) {
    for (int i = 0; i < parede.size(); i++) {
      parede.get(i).draw(g);
    }
  }

  public ArrayList<BoBlock> getParede() {
    return parede;
  }

  public void setParede(ArrayList<BoBlock> parede) {
    this.parede = parede;
  }

  /**
   * Método que vai definir o comportamento da bola ao tocar um bloco da parede
   *
   * @param bola
   */
  public void colision(BoBall bola) {
    for (int i = 0; i < parede.size(); i++) {
      if (bola.getShape().intersects(parede.get(i).getShape())) {
        colisionWhere(bola, parede.get(i));
      }
    }
  }

  private void colisionWhere(BoBall bola, BoBlock bloco) {

//        if(bola.getShape().intersects(bloco.getShape().x, bloco.getShape().y, bloco.getShape().x, bloco.getShape().height)){
//            bola.setDirectionX(bola.getDirectionX()*-1);
//        }
//        if(bola.getShape().intersects(bloco.getShape().x, bloco.getShape().y, bloco.getShape().width, bloco.getShape().y)){
//            bola.setDirectionY(bola.getDirectionY()*-1);
//        }
    if (bola.getShape().intersects(bloco.getShape())) {
      bola.setDirectionY(bola.getDirectionY() * -1);
    }
    parede.remove(bloco);
  }
}
