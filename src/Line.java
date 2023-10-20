import javax.swing.*;
import java.awt.*;
import java.util.Random;


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

    public void MoveTo(int dx, int dy) {
        int newx1 = x1 + dx;
        int newy1 = y1 + dy;
        int newx2 = x2 + dx;
        int newy2 = y2 + dy;
        if (newx1 > 0 && newx2 > 0 && newx1 < interfaceWidth && newx2 < interfaceWidth && newy1 > 0 && newy2 > 0 && newy1 < interfaceHeight && newy2 < interfaceHeight) {
            x1 = newx1;
            y1 = newy1;
            x2 = newx2;
            y2 = newy2;
        } else {
            int deltaX = x2 - x1;
            int deltaY = y2 - y1;
            Random random = new Random();
            x1 = random.nextInt(interfaceWidth - deltaX);
            y1 = random.nextInt(interfaceHeight - deltaY);
            x2 = x1 + deltaX;
            y2 = y1 + deltaY;
        }
        System.out.println("Координаты линии: (" + x1 + ", " + y1 + ") - (" + x2 + ", " + y2 + ")");
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