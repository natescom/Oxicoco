package com.nathan.res.drawables.tools.advanced;

import com.nathan.br.states.State;
import com.nathan.br.states.StateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nathan.res.drawables.tools.simple.List;
import com.nathan.res.drawables.tools.simple.Text;

/**
 * @author Nathan
 */
public class MenuPausa {
  private List op;
  private boolean make;
  private int StateBack;
  private State atual;

  /**
   * O menu é automaticamente centralizado
   *
   * @param x          Coordenada X
   * @param y          Coordenada Y
   * @param font       Fonte
   * @param Alpha      Transparência
   * @param UnSelected Cor das opções não selecionadas
   * @param Selected   Cor das opções selecionadas
   * @param Bg         Cor do fundo do menu
   * @param StateBack  ID da tela anterior
   */
  public MenuPausa(int x, int y, Font font, float Alpha, Color UnSelected, Color Selected, Color Bg, int StateBack, State stateatual) {
    float A = Alpha;
    this.StateBack = StateBack;
    this.atual = stateatual;
    Color unS;
    if (UnSelected == null) {
      unS = Color.gray;
    } else {
      unS = UnSelected;
    }
    Color S;
    if (Selected == null) {
      S = Color.YELLOW;
    } else {
      S = Selected;
    }
    Color B;
    if (Bg == null) {
      B = new Color(110, 196, 181);
    } else {
      B = Bg;
    }
    op = new List(x, y, font, unS, List.CENTER);
    op.add(new Text("Continuar"));
    op.add(new Text("Reiniciar"));
    op.add(new Text("Sair"));
    op.setSelecionable(S);
    op.setBackground("PAUSA", B, A);
    make = false;
  }


  public boolean input(KeyEvent e) {
    if (make) {
      if (e.getKeyCode() == KeyEvent.VK_W) {
        op.btn_UP();
      }
      if (e.getKeyCode() == KeyEvent.VK_S) {
        op.btn_DOWN();
      }
      if (e.getKeyCode() == KeyEvent.VK_C) {
        switch (op.getSeletion()) {
          case 0:
            make = false;
            return false;
          case 1:
            make = false;
            atual.init();
            return false;
          case 2:
            make = false;
            StateManager.setState(StateBack);
            return false;
        }
      }
    }
    return true;
  }

  public void draw(Graphics2D g) {
    if (make) {
      op.draw(g);
    }
  }

  public void doit() {
    make = true;
  }
}
