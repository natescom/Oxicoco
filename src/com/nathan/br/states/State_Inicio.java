package com.nathan.br.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.tools.simple.Background;
import com.nathan.res.drawables.tools.simple.List;
import com.nathan.res.drawables.tools.simple.Text;
import com.nathan.res.font.CustomFont;
import com.nathan.res.values;

/**
 * @author natha
 */
public class State_Inicio implements State {
  public static int ID;
  private Background fundo = new Background(new Color(110, 196, 181));
  private List opcoes;

  public State_Inicio(int Id) {
    ID = Id;

    opcoes = new List(15, values.HEIGHT / 2, CustomFont.getFont("Pixel Digivolve.otf", 36), new Color(12, 25, 20), List.LEFT);
    opcoes.add(new Text("Pong"));
    opcoes.add(new Text("Snake"));
    opcoes.add(new Text("Breakout"));
    opcoes.add(new Text("Flappy Bird"));
    opcoes.add(new Text("Voltar"));
    opcoes.setSelecionable(new Color(246, 226, 136));
  }


  @Override
  public void init() {

  }

  @Override
  public void update() {
  }

  @Override
  public void render(Graphics2D g) {
    fundo.draw(g);
    opcoes.draw(g);
  }

  @Override
  public void BtnPressed(KeyEvent e) {
    if (e.getKeyCode() == 87) {
      opcoes.btn_UP();
    }
    if (e.getKeyCode() == 83) {
      opcoes.btn_DOWN();
    }
    if (e.getKeyCode() == KeyEvent.VK_C) {
      switch (opcoes.getSeletion()) {
        case 0:
          StateManager.setState(State_Pong.ID);
          break;
        case 1:
          StateManager.setState(State_Snake.ID);
          break;
        case 2:
          StateManager.setState(State_Breakout.ID);
          break;
        case 3:
          StateManager.setState(State_FlappyBird.ID);
          break;
        default:
          StateManager.setState(State_Menu.ID);
          break;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_X) {
      StateManager.setState(State_Menu.ID);
    }
  }

  @Override
  public void BtnReleased(KeyEvent e) {
  }

  @Override
  public void BtnTyped(KeyEvent e) {
  }

}
