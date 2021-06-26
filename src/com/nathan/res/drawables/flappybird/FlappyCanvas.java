package com.nathan.res.drawables.flappybird;

import com.nathan.br.states.State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.util.ArrayList;

import com.nathan.res.drawables.tools.advanced.NPC;
import com.nathan.res.drawables.tools.simple.Background;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class FlappyCanvas implements State {
  private int x, y;
  private int width, height;
  private Background fundo;
  private NPC title, ground;
  private Bird bird;
  private ArrayList<Pipes> pipes;
  public static boolean start;
  public static boolean gameover;

  private Area outCanvas; // Area fora da tela de pintura

  public FlappyCanvas(int x, int y, int width, int height, boolean AligntoCenter) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    if (AligntoCenter) {
      this.x = x - width / 2;
      this.y = y - height / 2;
    }
    init();
  }

  private void pipeAnimate() {
    //<editor-fold desc="Limites">
    //Se o ultimo cano atingir a distancia de 80 então add novo cano
    if (pipes.get(pipes.size() - 1).getX() < x + width - 80) {
      pipes.add(new Pipes(x + width, y));
    }
    //Se o primeiro cano sair da borda da tela então delete-o
    if (pipes.get(0).getX() < x - 26) {
      pipes.remove(0);
    }
    //Se começou então inicie a animação
    if (start) {
      for (Pipes pipes : pipes) {
        pipes.animate();
      }
    }
    //</editor-fold>
  }

  private void limits() {
    if (bird.getShape().intersects(ground.getShape())) {
      gameover = true;
    }
    for (Pipes pip : pipes) {
      if (pip.getPipeUp().getShape().intersects(bird.getShape()) || pip.getPipeDown().getShape().intersects(bird.getShape())) {
        gameover = true;
      }
    }
  }

  @Override
  public void init() {
    fundo = new Background(this.x, this.y, Color.DARK_GRAY, "flappybird/FB_background01.png");
    outCanvas = new Area(new Rectangle(0, 0, values.WIDTH, values.HEIGHT));
    outCanvas.subtract(new Area(fundo.getShape()));
    title = new NPC(this.x + (-44 + this.width / 2), this.y + (50), "flappybird/FB_title.png", "FLAPPYBIRD_TITLE");
    bird = new Bird(this.x + (this.width / 2 - 8), this.y + (this.height / 2), "flappybird/FB_Bird01.png", 17, 12, 4, 0, 10, "FLAPPYBIRD_BIRD", this.x - 6 + (+this.width / 2), this.y + (25));
    ground = new NPC(this.x, this.y + this.height - 56, "flappybird/FB_ground.png", this.width, 56, 14, 1, 100, "FLAPPYBIRD_GROUND");
    pipes = new ArrayList();
    pipes.add(new Pipes(this.x + this.width, this.y));
    start = false;
    gameover = false;
  }

  @Override
  public void update() {
    limits();
    bird.animate();
    if (!gameover) {
      ground.animate();
      title.animate();
      pipeAnimate();
    }

    for (Pipes canos : pipes) {
      if (bird.getX() == canos.getX()) {
        bird.setScore(bird.getScore() + 1);
      }
    }


  }


  @Override
  public void render(Graphics2D g) {
    fundo.draw(g);
    pipes.forEach((pip) -> {
      pip.draw(g);
    });
    if (title.getAlpha() == 0) bird.drawScore(g);
    title.draw(g);
    ground.draw(g);
    bird.draw(g);


    g.setColor(Color.DARK_GRAY);
    g.fill(outCanvas);
  }

  @Override
  public void BtnPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      if (!start) {
        start = true;
      }
      if (!gameover) {
        bird.setAngle(-30);
        bird.setACCELERATION(-3);
      }
    }
  }

  @Override
  public void BtnReleased(KeyEvent e) {
  }

  @Override
  public void BtnTyped(KeyEvent e) {
  }


}
