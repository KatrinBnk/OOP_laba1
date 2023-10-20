import javax.swing.*;
import java.awt.*;

public class Triangle extends JPanel {
    private int interfaceWidth = 1000, interfaceHeight = 500;
    private  int[] coordinates; //координаты точек треугольник: первые три по оси ох, оставшиеся три по оу
    private Color color;
    private boolean VISION = true;

    public Triangle (int x, int y, int z, int t, int h, int f, Color c) {
        setLayout(null);
        setOpaque(false);
        this.coordinates = new int[] {x, y, z, t, h, f};
        this.color = c;
        this.VISION = true;
    }

    public Triangle (int x, int y, int z, int t, int h, int f) {
        setLayout(null);
        setOpaque(false);
        this.coordinates = new int[] {x, y, z, t, h, f};
        this.color = Color.BLACK;
        this.VISION = true;
    }

    public void MoveTo(int d1, int d2) {

        if (check(d1,d2)){
            for (int i =0; i < 6; i++){
                if ( i < 3){
                    this.coordinates[i] += d1;
                } else{
                    this.coordinates[i] += d2;
                }
            }
        } else{
            do {
                d1 = (int) (Math.random() * 600 - 100);
                d2 = (int) (Math.random() * 600 - 100);
            } while (!check(d1,d2));

            for (int i =0; i < 6; i++){
                if ( i < 3){
                    this.coordinates[i] += d1;
                } else{
                    this.coordinates[i] += d2;
                }
            }
        }

    }

    private boolean check(int d1, int d2) {
        int[] tests = new int[6];
        boolean f = true;
        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                tests[i] = coordinates[i] + d1;
                if (f & (tests[i] <= 0 || tests[i] >= interfaceWidth)) {
                    f = false;
                    return f;
                }
            } else {
                tests[i] = coordinates[i] + d2;
                if (f & (tests[i] <= 0 || tests[i] >= interfaceHeight)) {
                    f = false;
                    return f;
                }
            }
        }
        return f;
    }


    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.VISION = true;
        this.repaint();
    }

    //вращение вокруг центра (при многократном использовании дает погрешность в координатах)
    public void rotate() {
        double centerX = (coordinates[0] + coordinates[1] + coordinates[2]) / 3.0;
        double centerY = (coordinates[3] + coordinates[4] + coordinates[5]) / 3.0;
        double angle = Math.toRadians(45); // Угол поворота в радианах (45 градусов)

        // Создаем временный массив для хранения новых координат точек
        int[] newCoordinates = new int[6];

        for (int i = 0; i < 3; i++) {
            double x = coordinates[i] - centerX;
            double y = coordinates[i + 3] - centerY;

            // Поворачиваем точку вокруг центра треугольника
            double newX = x * Math.cos(angle) - y * Math.sin(angle);
            double newY = x * Math.sin(angle) + y * Math.cos(angle);

            newCoordinates[i] = (int) (newX + centerX);
            newCoordinates[i + 3] = (int) (newY + centerY);
        }

        // Проверяем, не выходят ли новые координаты за пределы интерфейса
        if (checkBounds(newCoordinates)) {
            // Если новые координаты не выходят за пределы, обновляем текущие координаты
            this.coordinates = newCoordinates;
        } else {
            JOptionPane.showMessageDialog(null, "Невозможно повернуть. Выходит за пределы холста.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkBounds(int[] newCoordinates) {
        for (int i = 0; i < 6; i++) {
            if (i < 3 && (newCoordinates[i] < 0 || newCoordinates[i] >= interfaceWidth)) {
                return false;
            } else if (i >= 3 && (newCoordinates[i] < 0 || newCoordinates[i] >= interfaceHeight)) {
                return false;
            }
        }
        return true;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawPolygon(new int[] {coordinates[0], coordinates[1], coordinates[2]}, new int[] {coordinates[3], coordinates[4], coordinates[5]},3);
        }
    }

}
