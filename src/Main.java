import javax.swing.*;
import java.awt.*;

public  class Main{
    private JFrame fNL = new JFrame("Лабораторная работа №1");

    private JPanel cP = centerPanel();
    private JPanel sP;

    // список всех кнопок, необходимых для рыботы
    private JButton[] buttons;
    private int buttonKey = 0;

    //для работы с объектами
    private JPanel line = null;
    private Line [] lines = null;
    private JPanel circle = null;
    private Circle [] circles = null;
    private JPanel rectangle = null;
    private Rectangle [] rectangles = null;
    private JPanel triangle = null;
    private Triangle[] triangles = null;

    private  int[] numbers;
    private boolean vision, visionRect, visionCircle, visionTriangle;
    private int a,b,c,d,e =0;
    private int cntCircles, cntTriangles, cntRects, cntLines = 10;


    private Main() {
        buttons = new JButton[]{
                new JButton("Назад"),
                new JButton("Линия"),
                new JButton("Окружность"),
                new JButton("Прямоугольник"),
                new JButton("Треугольник"),
                new JButton("Создать"),
                new JButton("Переместить"),
                new JButton("Удалить"),
                new JButton("Повернуть на 45"),
                new JButton("Изменить радиус"),
                new JButton("Изменить размер"),
                new JButton("Сделать видимым/невидимым"),
                new JButton("Массив")
        };
        sP = southPanel();
        //создаем основное окно
        fNL.setLayout(new BorderLayout()); //setLayout - метод для изменения менеджера размещения, BorderLayout - менеджер
        fNL.setSize(1280,680);
        fNL.add(cP, BorderLayout.CENTER);
        fNL.add(sP, BorderLayout.SOUTH);
        fNL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //позволяет указать действие, которое необходимо выполнить, когда пользователь закрывает окно нажатием на крестик.
        fNL.setVisible(true);


    }

    private JPanel centerPanel () {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.white);
        return p;
    }

    //панель с кнопками
    private JPanel southPanel () {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setBackground(Color.GRAY);
        p.add(buttons[1]);
        p.add(buttons[2]);
        p.add(buttons[3]);
        p.add(buttons[4]);

        // Добавляем обработчик события для кнопки "Назад"
        buttons[0].addActionListener(e -> {
            if (buttonKey >= 1 && buttonKey <= 4) {
                sP.removeAll();
                for (int i = 1; i <= 4; i++) {
                    sP.add(buttons[i]);
                }
            }
            if (buttonKey >= 11 && buttonKey <= 14) {
                if (buttonKey == 11) { sP.add(buttons[8]); buttonKey = 1; }
                if (buttonKey == 12) { sP.add(buttons[9]); buttonKey = 2; }
                if (buttonKey == 13) { sP.add(buttons[10]); buttonKey = 3; }
                if (buttonKey == 14) { sP.add(buttons[10]); buttonKey = 4; }
                sP.add(buttons[12]);
            }
            sP.revalidate();
            sP.repaint();
        });

        //линия
        buttons[1].addActionListener(e ->{
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 8, 11, 12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 1;

        });

        //окружность
        buttons[2].addActionListener(e ->{
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 9, 11, 12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 2;
        });

        //прямоугольник
        buttons[3].addActionListener(e ->{
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 10, 11, 12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 3;
        });

        buttons[4].addActionListener( e-> {
            sP.removeAll();
            numbers = new int[]{0,5,6,7,10,11,12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 4;
        });


        //создание
        buttons[5].addActionListener(e ->{
            int tag; // Инициализируем переменную tag значением, которое не может быть введено пользователем

            try {
                tag = Integer.parseInt(JOptionPane.showInputDialog("Хотите ввести параметры вручную? (0-нет, 1-да)"));
                if (tag != 0 && tag != 1) {
                    JOptionPane.showMessageDialog(null, "Ошибка: Введите 0 или 1.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (tag == 1){
                        create();
                    }
                    else {
                        createRandom();
                    }

                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка: некорректный ввод.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        });

        buttons[6].addActionListener( e -> {
            int x = -30 + (int) (Math.random() * 100);
            int y = -30 + (int) (Math.random() * 100);
            if (buttonKey == 1){
                if (line != null) {
                    ((Line) line).MoveTo(x, y);
                    ((Line) line).Show(vision);
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            } else if (buttonKey == 11) {
                if (lines != null) {
                    for (int i=0; i<cntLines; i++) {
                        lines[i].MoveTo(x, y);
                        lines[i].Show(vision);
                        cP.repaint();
                        System.out.println("перемещение Линии №" + (i+1));
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            } else if(buttonKey == 2){
                if (circle != null) {
                    ((Circle) circle).MoveTo(x, y);
                    ((Circle) circle).Show(visionCircle);
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            } else if (buttonKey == 12) {
                if (circles != null) {
                    for (int i=0; i<cntCircles; i++) {
                        circles[i].MoveTo(x, y);
                        circles[i].Show(visionCircle);
                        cP.repaint();
                        System.out.println("перемещение Окружности №" + (i+1));
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден");
                }
            } else if (buttonKey == 3) {
                if (rectangle != null) {
                    ((Rectangle) rectangle).MoveTo(x, y);
                    ((Rectangle) rectangle).Show(visionRect);
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            } else if (buttonKey == 13) {
                if (rectangles != null) {
                    for (int i=0; i<cntRects; i++) {
                        rectangles[i].MoveTo(x, y);
                        rectangles[i].Show(visionRect);
                        cP.repaint();
                        System.out.println("перемещение Прямоугольника №" + (i+1));
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив Прямоугольников не найден");
                }
            } else if (buttonKey == 4) {
                if (triangle != null) {
                    ((Triangle) triangle).MoveTo(x, y);
                    ((Triangle) triangle).Show(visionTriangle);
                    cP.repaint();
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            } else if (buttonKey == 14) {
                if (triangles != null) {
                    for (int i=0; i<10; i++) {
                        triangles[i].MoveTo(x, y);
                        triangles[i].Show(visionTriangle);
                        cP.repaint();
                        System.out.println("перемещение Треугольника №" + (i+1));
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив Треугольников не найден");
                }
            }
            x = y = 0;
        });

        // del
        buttons[7].addActionListener( e-> {
            if (buttonKey == 1) {
                if (line != null) {
                    cP.remove(line);
                    ((Line) line).Show(false);
                    line = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            }
            else if (buttonKey == 11) {
                if (lines != null) {
                    for (int i=0; i<10; i++) {
                        cP.remove(lines[i]);
                        lines[i].Show(false);
                    }
                    lines = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 2) {
                if (circle != null) {
                    cP.remove(circle);
                    ((Circle) circle).Show(false);
                    circle = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            }
            else if (buttonKey == 12) {
                if (circles != null) {
                    for (int i=0; i<10; i++) {
                        cP.remove(circles[i]);
                        circles[i].Show(false);
                    }
                    circles = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 3) {
                if (rectangle != null) {
                    cP.remove(rectangle);
                    ((Rectangle) rectangle).Show(false);
                    rectangle = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            }
            else if (buttonKey == 13) {
                if (rectangles != null) {
                    for (int i=0; i<10; i++) {
                        cP.remove(rectangles[i]);
                        rectangles[i].Show(false);
                    }
                    rectangles = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 4) {
                if (triangle != null) {
                    cP.remove(triangle);
                    ((Triangle) triangle).Show(false);
                    triangle = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            }
            else if (buttonKey == 14) {
                if (triangles != null) {
                    for (int i=0; i<10; i++) {
                        cP.remove(triangles[i]);
                        triangles[i].Show(false);
                    }
                    triangles = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
        });

        //повернуть
        buttons[8].addActionListener( e -> {
            if (buttonKey == 1) {
                if (line != null) {
                    ((Line) line).Turn();
                    ((Line) line).Show(vision);
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            }
            else if (buttonKey == 11) {
                if (lines != null) {
                    for (int i = 0; i < cntLines; i++) {
                        lines[i].Turn();
                        lines[i].Show(vision);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив не создан");
                }
            }
        });

        //изменить радиус
        buttons[9].addActionListener(e-> {
            if (buttonKey == 2) {
                int x = 50 + (int) (Math.random() * 150);
                if (circle != null) {
                    ((Circle) circle).chRad(x);
                    ((Circle) circle).Show(visionCircle);
                    x = 0;
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            }
            else if (buttonKey == 12) {
                if (circles != null) {
                    for (int i = 0; i < 10; i++) {
                        int x = 50 + (int) (Math.random() * 150);
                        circles[i].chRad(x);
                        circles[i].Show(visionCircle);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив Окружностей не создан");
                }
            }
        });

        buttons[10].addActionListener(e -> {
            int x = -25 + (int) (Math.random() * 50);
            int y = -25 + (int) (Math.random() * 50);
            if (buttonKey == 3) {
                if (rectangle != null) {
                    ((Rectangle) rectangle).chSize(x, y);
                    ((Rectangle) rectangle).Show(visionRect);
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            }
            else if (buttonKey == 13) {
                if (rectangles != null) {
                    for (int i = 0; i < cntRects; i++) {
                        rectangles[i].chSize(x, y);
                        rectangles[i].Show(visionRect);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не создан");
                }
            }
            else if (buttonKey == 4) {
                if (triangle != null) {
                    ((Triangle) triangle).chSize(x, y);
                    ((Triangle) triangle).Show(visionTriangle);
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            }
            else if (buttonKey == 14) {
                if (triangles != null) {
                    for (int i = 0; i < cntTriangles; i++) {
                        triangles[i].chSize(x, y);
                        triangles[i].Show(visionTriangle);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив треугольников не создан");
                }
            }
            x = y = 0;
        });

        //изменение видимости

        buttons[11].addActionListener(e -> {
            if (buttonKey == 1) {
                if (line != null) {
                    vision = !vision;
                    System.out.println("Видимость: " + vision);
                    ((Line) line).Show(vision);
                    cP.revalidate(); cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            }
            else if (buttonKey == 11) {
                if (lines != null) {
                    vision = !vision;
                    System.out.println("Видимость: " + vision);
                    for (int i=0; i<10; i++) {
                        lines[i].Show(vision);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 2) {
                if (circle != null) {
                    visionCircle = !visionCircle;
                    System.out.println("Видимость: " + visionCircle);
                    ((Circle) circle).Show(visionCircle);
                    cP.revalidate(); cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            }
            else if (buttonKey == 12) {
                if (circles != null) {
                    visionCircle = !visionCircle;
                    System.out.println("Видимость: " + visionCircle);
                    for (int i=0; i<10; i++) {
                        circles[i].Show(visionCircle);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 3) {
                if (rectangle != null) {
                    visionRect = !visionRect;
                    System.out.println("Видимость: " + visionRect);
                    ((Rectangle) rectangle).Show(visionRect);
                    cP.revalidate(); cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            }
            else if (buttonKey == 13) {
                if (rectangles != null) {
                    visionRect = !visionRect;
                    System.out.println("Видимость: " + visionRect);
                    for (int i=0; i<10; i++) {
                        rectangles[i].Show(visionRect);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 4) {
                if (triangle != null) {
                    visionTriangle = !visionTriangle;
                    System.out.println("Видимость: " + visionTriangle);
                    ((Triangle) triangle).Show(visionTriangle);
                    cP.revalidate(); cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            }
            else if (buttonKey == 14) {
                if (triangles != null) {
                    visionTriangle = !visionTriangle;
                    System.out.println("Видимость: " + visionTriangle);
                    for (int i=0; i<10; i++) {
                        triangles[i].Show(visionTriangle);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
        });

        //массив
        buttons[12].addActionListener(e -> {
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 11};
            for (int number: numbers){
                sP.add(buttons[number]);
            }
            if (buttonKey == 1 ) {
                sP.add(buttons[8]);
                buttonKey = 11;
                JOptionPane.showMessageDialog(fNL, "Следующие действия будут выполнены для массива линий");
            }
            if (buttonKey == 2 ) {
                sP.add(buttons[9]);
                buttonKey = 12;
                JOptionPane.showMessageDialog(fNL, "Следующие действия будут выполнены для массива окружностей");
            }
            if (buttonKey == 3 ) {
                sP.add(buttons[10]);
                buttonKey = 13;
                JOptionPane.showMessageDialog(fNL, "Следующие действия будут выполнены для массива прямоугольников");
            }
            if (buttonKey == 4 ) {
                sP.add(buttons[10]);
                buttonKey = 14;
                JOptionPane.showMessageDialog(fNL, "Следующие действия будут выполнены для массива треугольников");
            }
            sP.revalidate();
            sP.repaint();
        });


        return p;
    }
    private void create(){
        if(buttonKey == 1){
            if (line == null){
                vision = true;
                //добавить обработку корректности?
                int x1 = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату начала отрезка:"));
                int y1 = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату начала отрезка:"));
                int x2 = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату конца отрезка:"));
                int y2 = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату конца отрезка:"));

                line = new Line(x1, y1, x2, y2, Color.RED);
                ((Line) line).Show(vision);
                cP.add(line, BorderLayout.CENTER);
                cP.revalidate();
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Линия уже нарисована");
            }
        } else if(buttonKey == 11){
            if (lines == null) {
                JOptionPane.showMessageDialog(fNL, "Линии будут созданы по случайным координатам.");
                cntLines = Integer.parseInt(JOptionPane.showInputDialog("Сколько линий необходимо создать?"));
                createRandom();
            }
            else{
                JOptionPane.showMessageDialog(fNL, "Линии уже нарисованы");
            }

        } else if (buttonKey == 2) {
            if (circle == null) {
                visionCircle = true;
                int x = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату центра:"));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату центра:"));
                int D = Integer.parseInt(JOptionPane.showInputDialog("Введите диаметр окружности:"));

                circle = new Circle (x, y, D, Color.GREEN);
                ((Circle) circle).Show(visionCircle);
                cP.add(circle, BorderLayout.CENTER);
                cP.revalidate();
            } else {
                JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована");
            }

        }else if(buttonKey == 12){
            if (circles == null) {
                JOptionPane.showMessageDialog(fNL, "Окружности будут созданы по случайным координатам.");
                cntCircles = Integer.parseInt(JOptionPane.showInputDialog("Сколько окружностей необходимо создать?"));
                createRandom();
            }
            else{
                JOptionPane.showMessageDialog(fNL, "Окружности уже нарисованы");
            }
        } else if (buttonKey == 3) {
            if(rectangle == null){
                visionRect = true;
                int x = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату начала отрезка:"));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату начала отрезка:"));
                int w = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату конца отрезка:"));
                int h = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату конца отрезка:"));
                rectangle = new Rectangle(x, y, w, h, Color.GREEN);
                ((Rectangle) rectangle).Show(visionRect);
                cP.add(rectangle, BorderLayout.CENTER);
                cP.revalidate();
            } else {
                JOptionPane.showMessageDialog(fNL, "Прямоугольник уже нарисован");
            }
        } else if (buttonKey == 13) {
            if (rectangles == null) {
                JOptionPane.showMessageDialog(fNL, "Прямоугольники будут созданы по случайным координатам.");
                cntRects = Integer.parseInt(JOptionPane.showInputDialog("Сколько прямоугольники необходимо создать?"));
                createRandom();
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Прямоугольники уже нарисованы");
            }
        } else if (buttonKey == 4) {
            if (triangle == null){
                visionTriangle = true;
                //добавить обработку корректности?
                int x = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату начала отрезка:"));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату начала отрезка:"));
                int z = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату конца отрезка:"));
                int t = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату конца отрезка:"));
                int h = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату конца отрезка:"));

                triangle = new Triangle(x, y, z, t, h, Color.YELLOW);
                ((Triangle) triangle).Show(vision);
                cP.add(triangle, BorderLayout.CENTER);
                cP.revalidate();
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Треугольник уже нарисован");
            }
        } else if (buttonKey == 14) {
            if (triangles == null){
                JOptionPane.showMessageDialog(fNL,"Треугольники будут созданы по случайным координатам.");
                cntTriangles = Integer.parseInt(JOptionPane.showInputDialog("Сколько треугольников необходимо создать?"));
                createRandom();
            } else {
                JOptionPane.showMessageDialog(fNL, "Треугольники уже нарисованы");
            }
        }

    }

    private  void createRandom(){
        a = (int) (Math.random() * 300);
        b = (int) (Math.random() * 300);
        c = (int) (Math.random() * 300);
        d = (int) (Math.random() * 300);
        e = (int) (Math.random() * 300);
        if (buttonKey == 1) {
            if (line == null) {
                vision = true;
                line = new Line (a, b, c, d, Color.BLACK);
                ((Line) line).Show(vision);
                cP.add(line, BorderLayout.CENTER);
                cP.revalidate();
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Линия уже нарисована");
            }
        }else if(buttonKey == 11){
            if (lines == null){
                lines = new Line[cntLines];
                vision = true;
                lines[0] = new Line (a, b, c, d, Color.BLACK);
                System.out.println("Line №" + (1) + " координаты:: " + a +", "+ b +", "+ c +", "+ d);
                for (int i = 1; i < cntLines; i++){
                    a = (int) (Math.random() * 300);
                    b = (int) (Math.random() * 300);
                    c = (int) (Math.random() * 300);
                    d = (int) (Math.random() * 300);
                    lines[i] = new Line (a, b, c, d, Color.BLACK);
                    System.out.println("Line №" + (i+1) + " координаты:: " + a +", "+ b +", "+ c +", "+ d);
                    lines[i].Show(vision);
                    cP.add(lines[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            }else{
                JOptionPane.showMessageDialog(fNL, "Массив линий уже создан");

            }
        } else if (buttonKey == 2) {
            if (circle == null) {
                visionCircle = true;
                circle = new Circle (a, b, c, Color.GREEN);
                ((Circle) circle).Show(visionCircle);
                cP.add(circle, BorderLayout.CENTER);
                cP.revalidate();
            } else {
                JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована");
            }

        } else if(buttonKey == 12){
            if (circles == null){
                circles = new Circle[cntCircles];
                visionCircle = true;
                circles[0] = new Circle (a, b, c, Color.BLACK);
                System.out.println("Circle №" + (1) + " координаты:: " + a +", "+ b +", "+ c);
                for (int i = 1; i < cntCircles; i++){
                    a = (int) (Math.random() * 300);
                    b = (int) (Math.random() * 300);
                    c = (int) (Math.random() * 300);

                    circles[i] = new Circle (a, b, c, Color.BLACK);
                    System.out.println("Circle №" + (i+1) + " координаты:: " + a +", "+ b +", "+ c);
                    circles[i].Show(visionCircle);
                    cP.add(circles[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            } else{
                JOptionPane.showMessageDialog(fNL, "Массив окружностей уже создан");
            }
        } else if (buttonKey == 3) {
            if (rectangle == null) {
                visionRect = true;
                rectangle = new Rectangle (a, b, c,d, Color.MAGENTA);
                ((Rectangle) rectangle).Show(visionRect);
                cP.add(rectangle, BorderLayout.CENTER);
                cP.revalidate();
            } else {
                JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована");
            }
        } else if (buttonKey== 13) {
            if (rectangles == null){
                rectangles = new Rectangle[cntRects];
                visionRect = true;
                rectangles[0] = new Rectangle (a, b, c, d, Color.ORANGE);
                System.out.println("Rect №" + (1) + " координаты:: " + a +", "+ b +", "+ c +", "+ d);
                for (int i = 1; i < cntRects; i++){
                    a = (int) (Math.random() * 300);
                    b = (int) (Math.random() * 300);
                    c = (int) (Math.random() * 300);
                    d = (int) (Math.random() * 300);
                    rectangles[i] = new Rectangle (a, b, c, d, Color.BLACK);
                    System.out.println("Rect №" + (i+1) + " координаты:: " + a +", "+ b +", "+ c +", "+ d);
                    rectangles[i].Show(vision);
                    cP.add(rectangles[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            }else{
                JOptionPane.showMessageDialog(fNL, "Массив прямоугольников уже создан");

            }
        } else if (buttonKey == 14) {
            if (triangles == null){
                triangles = new Triangle[cntTriangles];
                visionTriangle = true;
                triangles[0] = new Triangle (a, b, c, d, e, Color.BLACK);
                System.out.println("Triangle №" + (1) + " координаты:: " + a +", "+ b +", "+ c +", "+ d);
                for (int i = 1; i < cntTriangles; i++){
                    a = (int) (Math.random() * 300);
                    b = (int) (Math.random() * 300);
                    c = (int) (Math.random() * 300);
                    d = (int) (Math.random() * 300);
                    e = (int) (Math.random() * 300);
                    triangles[i] = new Triangle (a, b, c, d,e, Color.BLACK);
                    System.out.println("Triangle №" + (i+1) + " координаты:: " + a +", "+ b +", "+ c +", "+ d);
                    triangles[i].Show(vision);
                    cP.add(triangles[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            }else{
                JOptionPane.showMessageDialog(fNL, "Массив треугольников уже создан");

            }
        } else if (buttonKey == 4) {
            if (triangle == null){
                visionTriangle = true;
                triangle = new Triangle (a, b, c,d,e, Color.MAGENTA);
                ((Triangle) triangle).Show(visionTriangle);
                cP.add(triangle, BorderLayout.CENTER);
                cP.revalidate();
            }
            else{
                JOptionPane.showMessageDialog(fNL, "Треугольник уже нарисован");
            }
        }
    }

    public static void main (String[] argc) {
        SwingUtilities.invokeLater(Main::new);
    }

}