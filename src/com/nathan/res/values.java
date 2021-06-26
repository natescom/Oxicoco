package com.nathan.res;

/**
 * Classe abstrata com os valores padrões de todo o jogo
 *
 * @author Nathan
 */
public abstract class values {
  // STRINGS //
  public static final String TITLE = "Oxicoco";
  public static final String MENU_TITLE = "Oxicoco";

  // VALUES //
  public static int CURRENT_WIDTH = 1, CURRENT_HEIGHT = 1;
  public static final int WIDTH = 400, HEIGHT = 300;
  public static final int FPS = 60;
  public static final int DEFAULT_SPRITE_VELOCITY = 3;
  private static int SCALE = 3;

  // METODS //
  public static void scale_Change() {
    SCALE++;
    if (SCALE > 3) {
      SCALE = 1;
    }
  }

  public static int getScale() {
    return SCALE;
  }


}
