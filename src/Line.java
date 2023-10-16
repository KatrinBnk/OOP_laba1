import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    private int x1, y1, x2, y2;
    private int interfaceWidth = 1000, interfaceHeight = 500;
    private Color color;
    private boolean VISION;

    public Line (int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.VISION = true;
        setOpaque(false);
    }

    public Line(int x1, int y1, int x2, int y2) {
        this(x1, y1, x2, y2, Color.BLACK);
        setOpaque(false);
    }

    //можно сделать красивее и проще
    public void MoveTo(int dx, int dy) {
        int testx1 = x1, testx2 = x2, testy1 = y1, testy2 = y2;
        testx1 += dx;
        testy1 += dy;
        testx2 += dx;
        testy2 += dy;
        if (testx1 > interfaceWidth || testx2 > interfaceWidth || testy1 > interfaceHeight || testy2 > interfaceHeight){
            x1 -= dx;
            y1 -= dy;
            x2 -= dx;
            y2 -= dy;
        } else if(testx1 < 0 || testx2 < 0 || testy1 < 0 || testy2 < 0) {
            x1 -= dx;
            y1 -= dy;
            x2 -= dx;
            y2 -= dy;
        } else{
            x1 += dx;
            y1 += dy;
            x2 += dx;
            y2 += dy;
        }

    }


    public void Show(boolean VISION) {
        this.VISION = VISION;
        setVisible(this.VISION);
        this.repaint();
    }

    //вращение относительно одной из точек прямой
    public void Turn() {
        double c1, c2;
        c1 = (x2 - x1) * Math.cos(Math.toRadians(45.0)) - (y2 - y1) * Math.sin(Math.toRadians(45.0)) + x1;
        c2 = (x2 - x1) * Math.sin(Math.toRadians(45.0)) + (y2 - y1) * Math.cos(Math.toRadians(45.0)) + y1;
        if (c1 >= interfaceWidth || c2 >= interfaceHeight || c1 <= 0 || c2 <= 0){
            c1 = (x1 - x2) * Math.cos(Math.toRadians(45.0)) - (y1 - y2) * Math.sin(Math.toRadians(45.0)) + x2;
            c2 = (x1 - x2) * Math.sin(Math.toRadians(45.0)) + (y1 - y2) * Math.cos(Math.toRadians(45.0)) + y2;
            JOptionPane.showMessageDialog(null,  "Turn the other way" ,"ERROR", JOptionPane.ERROR_MESSAGE );
        }
        x2= (int)c1;
        y2= (int)c2;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}