package com.nathan.br.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * @author Nathan
 */
public interface State {
    
    void init();
    void update();
    void render(Graphics2D g);
    
    void BtnPressed(KeyEvent e);
    void BtnReleased(KeyEvent e);
    void BtnTyped(KeyEvent e);
}
