package com.nathan.br.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.tools.simple.Background;
import com.nathan.res.drawables.tools.simple.List;
import com.nathan.res.drawables.tools.simple.Text;
import com.nathan.res.drawables.tools.simple.Toast;
import com.nathan.res.font.CustomFont;
import com.nathan.res.values;

/**
 * @author Nathan
 */
public class State_Menu implements State {
  public static int ID;

  private Text title;
  private List opcoes;
  private Background fundo;
  private Toast toast;

  public State_Menu(int id) {
    ID = id;
  }


  @Override
  public void init() {
    fundo = new Background(new Color(17, 19, 44));
    title = new Text(values.MENU_TITLE, (values.WIDTH / 2), 0, CustomFont.getFont("Pixel Digivolve.otf", 50f), new Color(124, 163, 199), true);
    opcoes = new List((values.WIDTH / 2), 205 + title.getY(), CustomFont.getFont("AtariSmall.ttf", 30f), new Color(103, 93, 145), List.CENTER);

    opcoes.add(new Text("Iniciar"));
    opcoes.add(new Text("Opções"));
    opcoes.add(new Text("Sair"));

    opcoes.setSelecionable(new Color(148, 223, 244));

    toast = new Toast("Isso eh um Toast", 2);

  }

  @Override
  public void update() {
  }

  @Override
  public void render(Graphics2D g) {
    fundo.draw(g);
    title.draw(g);
    opcoes.draw(g);


    toast.draw(g);

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
          StateManager.setState(State_Inicio.ID);
          break;
        case 1:
          StateManager.setState(State_Config.ID);
          break;
        case 2:
          System.exit(0);
          break;
        default:
          break;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_X) {
      toast.make();
    }
  }

  @Override
  public void BtnReleased(KeyEvent e) {
  }

  @Override
  public void BtnTyped(KeyEvent e) {
  }

}
