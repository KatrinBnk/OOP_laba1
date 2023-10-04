
import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    private int x, y, D;
    public int n = 0;
    private Color color;
    private boolean VIS = true;

    public Circle (int c1, int c2, int diam, Color c) {
        setLayout(null);
        setOpaque(false);
        x = c1;
        y = c2;
        D = diam;
        color = c;
    }

    public Circle (int c1, int c2, int diam) {
        setLayout(null);
        setOpaque(false);
        x = c1;
        y = c2;
        D = diam;
        color= Color.BLACK;
    }

    public void MoveTo(int c1, int c2) {
        x += c1;
        y += c2;
    }

    public void Show(boolean VISION) {
        VIS = VISION;
        setVisible(VIS);
        n = 1;
        this.repaint();
    }

    public void chRad(int diam) {
        D = diam;
    }

    //????
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (n == 1) {
            g.setColor(color);
            g.drawOval(x-D/2, y-D/2, D, D); //???
            System.out.println("Circle Видимость: " + VIS);
        }
    }
}