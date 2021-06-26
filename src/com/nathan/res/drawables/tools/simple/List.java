package com.nathan.res.drawables.tools.simple;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.nathan.res.drawables.drawable;
import com.nathan.res.font.CustomFont;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class List extends drawable {
  private ArrayList<Text> box;
  private int fontSize;
  private Font font;
  private Color color, selectColor;
  private String lineUp;

  public List(int x, int y, Font font, Color color, String lineup) {
    this.box = new ArrayList<>();
    this.x = x;
    this.y = y;
    if (font == null) {
      this.font = CustomFont.getFont("AtariSmall.ttf", 18f);
    } else {
      this.font = font;
    }
    this.color = color;
    this.lineUp = lineup;
  }

  public final static String CENTER = "Centro";
  public final static String LEFT = "Esquerda";
  public final static String RIGHT = "Direita";

  private void align(Graphics2D g) {
    for (int i = 0; i < box.size(); i++) {
      switch (lineUp) {
        case "Centro":
          box.get(i).setX(x - (g.getFontMetrics().stringWidth(box.get(i).getTxt()) / 2));
          break;
        case "Esquerda":
          box.get(i).setX(x);
          break;
        case "Direita":
          box.get(i).setX(values.WIDTH - x - g.getFontMetrics().stringWidth(box.get(i).getTxt()));
          break;

      }

      int altura = ((g.getFontMetrics().getAscent() + g.getFontMetrics().getDescent()) * (box.size() - 2) + g.getFontMetrics().getDescent() * 3);
      int Yp = y - (altura / 2);
      box.get(i).setY(Yp + fontSize * i);

    }
  }


  public void add(Text txt) {
    txt.setColor(color);
    box.add(txt);
  }

  @Override
  public void draw(Graphics2D g) {
    g.setFont(font);
    this.fontSize = g.getFontMetrics().getHeight();
    align(g);

    if (bgStatus) {
      g.setColor(bgColor);
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, bgAlpha));
      int size = 100;
      g.fillRect(x - size / 2, y - size / 2, size, size);
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

      //new Text(background_title, x, y-100, new Font("Dialog",Font.BOLD, font.getSize()+8), Color.WHITE).draw(g);
    }

    for (int i = 0; i < box.size(); i++) {
      g.setFont(font);
      g.setColor(box.get(i).getColor());
      g.drawString(box.get(i).getTxt(), box.get(i).getX(), box.get(i).getY());
    }
  }

  private boolean bgStatus = false;
  private String bgTitle;
  private float bgAlpha;
  private Color bgColor;

  public void setBackground(String title, Color c, Float a) {
    bgTitle = title;
    bgColor = c;
    bgAlpha = a;
    bgStatus = true;
  }

  //<editor-fold desc="Opções para menu de seleção">
  private ArrayList<Boolean> choices;

  public void setSelecionable(Color c) {
    selectColor = c;
    choices = new ArrayList<>();
    box.forEach((_item) -> {
      choices.add(false);
    });
    choices.set(0, true);
    paintSelection(0);
  }


  private void paintSelection(int atual, int anterior) {
    box.get(atual).setColor(selectColor);
    box.get(anterior).setColor(color);

  }

  private void paintSelection(int atual) {
    box.get(atual).setColor(selectColor);
  }

  private void paintSelection() {
    for (int i = 0; i < choices.size(); i++) {
      if (choices.get(i)) {
        box.get(i).setColor(Color.YELLOW);
      } else {
        box.get(i).setColor(Color.WHITE);
      }
    }
  }


  public void btn_UP() {
    for (int i = 0; i < choices.size(); i++) {
      if (choices.get(i)) {
        int j;
        if (i == 0) {
          j = choices.size() - 1;
        } else {
          j = i - 1;
        }
        choices.set(j, Boolean.TRUE);
        choices.set(i, Boolean.FALSE);
        paintSelection(j, i);
        break;
      }
    }
  }

  public void btn_DOWN() {
    for (int i = 0; i < choices.size(); i++) {
      if (choices.get(i)) {
        int j;
        if (i == choices.size() - 1) {
          j = 0;
        } else {
          j = i + 1;
        }
        choices.set(j, Boolean.TRUE);
        choices.set(i, Boolean.FALSE);
        paintSelection(j, i);
        break;
      }
    }
  }

  public int getSeletion() {
    for (int i = 0; i < box.size(); i++) {
      if (choices.get(i)) {
        return i;
      }
    }
    return 0;
  }
  //</editor-fold>

  public ArrayList<Text> getBox() {
    return box;
  }

  public void setBox(ArrayList<Text> box) {
    this.box = box;
  }


}
