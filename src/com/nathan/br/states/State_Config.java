package com.nathan.br.states;

import com.nathan.br.input.KeyManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.tools.simple.Background;
import com.nathan.res.drawables.tools.simple.List;
import com.nathan.res.drawables.tools.simple.Text;
import com.nathan.res.font.CustomFont;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class State_Config implements State {
  public static int ID;
  private final Text title;
  private final Background fundo;
  private List op;
  private List opres;

  public State_Config(int ID) {
    State_Config.ID = ID;
    title = new Text("Opções", 10, 0, CustomFont.getFont("Pixel Digivolve.otf", 50f), Color.white, false);
    fundo = new Background(new Color(17, 19, 44));
    op = new List(15, title.getY() + title.getFont().getSize() + 50, CustomFont.getFont("AtariSmall.ttf", 30f), Color.yellow, List.LEFT);
    opres = new List(15, title.getY() + title.getFont().getSize() + 50, CustomFont.getFont("AtariSmall.ttf", 30f), Color.yellow, List.RIGHT);
    op.add(new Text("Tamanho da janela"));
    opres.add(new Text("x" + values.getScale()));
    op.setSelecionable(Color.red);
  }


  @Override
  public void init() {
  }

  @Override
  public void update() {
    opres.getBox().get(0).setTxt("x" + String.valueOf(values.getScale()));
  }

  @Override
  public void render(Graphics2D g) {
    fundo.draw(g);
    title.draw(g);
    op.draw(g);
    opres.draw(g);
  }

  @Override
  public void BtnPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_X) {
      StateManager.setState(State_Menu.ID);
    }
    if (KeyManager.w) {
      op.btn_UP();
    }
    if (KeyManager.s) {
      op.btn_DOWN();
    }
    if (e.getKeyCode() == KeyEvent.VK_C) {
      switch (op.getSeletion()) {
        case 0:
          values.scale_Change();
          break;
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
