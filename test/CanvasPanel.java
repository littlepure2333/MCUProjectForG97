import javax.swing.*;

import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.PApplet;
import processing.core.PSurface;
public class CanvasPanel extends PApplet {
    static JFrame frame = new JFrame("JFrame Test");
    public static void main(String[] args) {
        CanvasPanel canvasPanel = new CanvasPanel();
        PSurface ps = canvasPanel.initSurface();
        ps.setSize(800,800);
        SmoothCanvas smoothCanvas = (SmoothCanvas)ps.getNative();
        frame.add(smoothCanvas);
        frame.setVisible(true);
        frame.setSize(800,800);
        ps.startThread();
    }

//    public void settings() {
//        size(800, 800);
//    }

    public void setup() {
        background(0, 0, 0);
        smooth();
        strokeWeight(5);
    }

    public void draw() {
        noStroke();
        fill(0,10);
        rect(0,0,width,height);
        stroke(255);
        if (mousePressed) {
            line(mouseX,mouseY,pmouseX,pmouseY);
        }
    }
}


