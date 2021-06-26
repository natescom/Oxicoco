package com.nathan.res.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nathan
 */
public abstract class CustomFont {
  /**
   * @param name Nome da font em src/res/font
   * @param size Tamanho
   * @return
   */
  public static Font getFont(String name, float size) {
    try {
      return Font.createFont(Font.TRUETYPE_FONT, new File("src/com/nathan/res/font/" + name)).deriveFont(size);
    } catch (FontFormatException | IOException ex) {
      Logger.getLogger(CustomFont.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
