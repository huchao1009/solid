package com.huchao.solid.ocp.right;

/**
 * @author double
 * @Date 2021/7/1 2:26 下午
 */
public class Ocp {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
    }
}

//这是一个用于绘图的类 [使用方]
class GraphicEditor {
    //接收Shape对象，调用draw方法
    public void drawShape(Shape s) {
        s.draw();
    }
}

////Shape类，基类
//abstract class Shape {
//    int m_type;
//
//    public abstract void draw();//抽象方法
//}

interface Shape
{
    void draw();
}

class Rectangle implements Shape {
    Rectangle() {
//        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制矩形 ");
    }
}

class Circle implements Shape {
    Circle() {
//        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制圆形 ");
    }
}

//新增画三角形
class Triangle implements Shape {
    Triangle() {
//        super.m_type = 3;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制三角形 ");
    }
}
