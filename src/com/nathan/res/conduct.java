package com.nathan.res;

import com.nathan.res.drawables.flappybird.Bird;
import com.nathan.res.drawables.flappybird.FlappyCanvas;
import com.nathan.res.drawables.tools.advanced.NPC;

/**
 * Classe que armazena os metodos correspondentes ao comportamento dos NPC na tela
 *
 * @author Nathan
 */

/*
if(npc.getTicks()>=values.FPS/npc.getVelocity()){
            npc.updateSprite(npc.getNextSpriteX(), 0);
            
            
            npc.setNextSpriteX(npc.getNextSpriteX()+1);
            npc.setTicks(0);
            
            
        }else{
            npc.setTicks(npc.getTicks()+1);
        }
*/
public abstract class conduct {

  public static void SNOWBOY(NPC npc) {
    if (npc.getTicks() >= values.FPS / npc.getVelocity()) {
      npc.updateSprite(npc.getNextSpriteX(), 0);
      npc.setX(npc.getX() + 1);
      if (npc.getX() > values.WIDTH) {
        npc.setX(-npc.getWidth());
      }
      npc.setNextSpriteX(npc.getNextSpriteX() + 1);
      npc.setTicks(0);
      if (npc.getNextSpriteX() >= npc.getNumSpritesX()) {
        npc.setNextSpriteX(0);
      }
    } else {
      npc.setTicks(npc.getTicks() + 1);
    }
  }

  private static int FB_POSITION_Y = 0;
  private static boolean troca = false;

  public static void FLAPPYBIRD_BIRD(Bird bird) {
    // Gravity
    if (FlappyCanvas.start) {
      FB_POSITION_Y = 0;
      if (bird.getAngle() < 90 && bird.getACCELERATION() > 3) {
        bird.setAngle(bird.getAngle() + 10);
      }
      if (bird.getY() < 215) {
        bird.setY((int) (bird.getY() + bird.getACCELERATION()));
        if (bird.getTicks() % 2 == 0) {
          bird.setACCELERATION(bird.getACCELERATION() + 0.3f);
        }
      }
    } else {
      if (bird.getTicks() >= values.FPS / bird.getVelocity()) {
        if (troca) {
          FB_POSITION_Y++;
          bird.setY(bird.getY() + 1);
          if (FB_POSITION_Y == 5) {
            troca = false;
          }
        } else {
          FB_POSITION_Y--;
          bird.setY(bird.getY() - 1);
          if (FB_POSITION_Y == -5) {
            troca = true;
          }
        }
      } else {
        bird.setTicks(bird.getTicks() + 1);
      }

    }


    // Sprite Player
    if (bird.getTicks() >= values.FPS / bird.getVelocity()) {
      bird.updateSprite(bird.getNextSpriteX(), 0);
      bird.setNextSpriteX(bird.getNextSpriteX() + 1);
      bird.setTicks(0);
    } else {
      bird.setTicks(bird.getTicks() + 1);
    }

    if (FlappyCanvas.gameover) {
      bird.updateSprite(1, 0);
    }
  }

  public static void FLAPPYBIRD_GROUND(NPC npc) {
    npc.updateSprite(npc.getNextSpriteX(), 0, 1, 0);
    npc.setNextSpriteX(npc.getNextSpriteXwithout() + 1);
    if (npc.getNextSpriteXwithout() >= npc.getNumSpritesX()) {
      npc.setNextSpriteX(2);
    }
  }

  public static void FLAPPYBIRD_TITLE(NPC npc) {
    if (FlappyCanvas.start) {
      {
        npc.setAlpha(npc.getAlpha() - 0.1f);
        if (npc.getAlpha() < 0) npc.setAlpha(0);
      }
    }
  }


}
