import javax.swing.*;
import java.awt.*;

public class Triangle extends JPanel {
    private int x, y, z, t, h;
    private Color color;
    private boolean VISION = true;

    public Triangle (int c1, int c2, int c3, int c4, int c5, Color c) {
        setLayout(null);
        setOpaque(false);
        x = c1;
        y = c2;
        z = c3;
        t = c4;
        h = c5;
        color = c;
    }

    public Triangle (int c1, int c2, int c3, int c4, int c5) {
        setLayout(null);
        setOpaque(false);
        x = c1;
        y = c2;
        z = c3;
        t = c4;
        h = c5;
        color= Color.BLACK;
    }

    public void MoveTo(int c1, int c2) {
        x += c1;
        y += c1;
        z += c1;
        h += c2;
        t += c2;

    }

    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.VISION = true;
        this.repaint();
    }

    public void chSize(int c1, int c2) {
        x -= c1;
        z += c1;
        t -= c2;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawPolygon(new int[] {x, y, z}, new int[] {t, h, t},3);
            System.out.println("Triangle Видимость: " + VISION);
        }
    }

}
