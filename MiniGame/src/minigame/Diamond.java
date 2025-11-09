/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minigame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Sodium
 */
public class Diamond {

    public Diamond(double par, double par1, double par2, double par3) {
    }
        public double x, y;
        public double width, height;
        public double vy; // скорость по вертикали
    
    
        public Diamond(double x, double y, double width, double height, double vy) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.vy = vy;
        }

        public void update() {
            y -= vy; // двигаем вверх
        }

        public void draw(Graphics g, JPanel panel, Image img) {
            g.drawImage(img, (int) x, (int) y, (int) width, (int) height, null);
        }
    
}


