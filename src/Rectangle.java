import javax.swing.*;
import java.awt.*;

public class Rectangle extends JPanel {
    private int x, y, w, h;
    private int interfaceWidth = 1000, interfaceHeight = 500;
    private Color color;
    private boolean VISION;

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.color = color;
        this.VISION = true;
        setLayout(null);
        setOpaque(false);
    }

    public Rectangle (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.color = Color.BLACK;
        this.VISION = true;
        setLayout(null);
        setOpaque(false);

    }

    public void MoveTo(int dx, int dy) {
        int testx = x, testy = y;
        testx += dx;
        testy += dy;
        if (testx > 0 && testy > 0 && testx < interfaceWidth && testy < interfaceHeight &&testx +w > 0 && testy + h > 0 && testx+w < interfaceWidth && testy+h < interfaceHeight ){
            this.x += dx;
            this.y += dy;
        } else {
            do {
                this.x = (int) (Math.random() * 400 + 1);
                this.y = (int) (Math.random() * 400 + 1);
            } while (x + w > interfaceWidth || y + h > interfaceHeight);
        }

    }



    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.VISION = true;
        this.repaint();
    }

    public void chSize(int dw, int dh) {
        int testw = w, testh = h;
        testw += dw;
        testh+= dh;
        if (testw > 0 && testh > 0 && testw < interfaceWidth && testh < interfaceHeight &&testw +w > 0 && testh + h > 0 && testw+w < interfaceWidth && testh+h < interfaceHeight ){
            this.w += dw;
            this.h += dh;
        } else {
            do {
                this.w = (int) (Math.random() * 400 + 1);
                this.h = (int) (Math.random() * 400 + 1);
            } while (x + w > interfaceWidth || y + h > interfaceHeight);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawRect(x, y, w, h);
        }
    }
}