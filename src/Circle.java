
import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    private int x, y, D; //координаты центра и диаметр окр-ти
    private int interfaceWidth = 900, interfaceHeight = 450; //границы области рисования
    private Color color; //цвет фигуры
    private boolean VISION = true; //видимость фигуры

    public Circle (int x, int y, int diameter, Color color) {
        setLayout(null);
        setOpaque(false);
        this.x = x;
        this.y = y;
        this.D = diameter;
        this.color = color;
    }

    public Circle (int x, int y, int diameter) {
        setLayout(null);
        setOpaque(false);
        this.x = x;
        this.y = y;
        this.D = diameter;
        this.color = Color.BLACK;
    }

    //смещение на некоторое число для х и у
    public void MoveTo(int dx, int dy) {
        int testx = x, testy = y;
        testx += dx;
        testy += dy;
        if (testx - D >= 0 && testx + D <= interfaceWidth && testy - D >= 0 && testy + D <= interfaceHeight) {
            this.x = testx;
            this.y = testy;
        }
        else {
            do {
                this.x = (int) (Math.random() * (interfaceWidth - 2 * D) + D);
                this.y = (int) (Math.random() * (interfaceHeight - 2 * D) + D);
            } while (x + D > interfaceWidth || y + D > interfaceHeight);
        }

    }

    //изменение видимости фигуры
    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.VISION = true;
        this.repaint();
    }

    //изменение диаметра фигуры
    public void chengeD(int Diam) {
        if (x - Diam >= 0 && x + Diam <= interfaceWidth && y - Diam >= 0 && y + Diam <= interfaceHeight){
            this.D = Diam;
        } else {
            this.D = 10;
        }
    }

    //рисование фигуры на холсте
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawOval(x-D/2, y-D/2, D, D);
        }
    }
}