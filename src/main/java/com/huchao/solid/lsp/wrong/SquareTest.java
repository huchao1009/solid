package com.huchao.solid.lsp.wrong;


/**
 * @author double
 * @Date 2021/7/1 11:29 上午
 */
public class SquareTest {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setLength(4);
        rectangle.setWidth(3);
        System.out.println(rectangle.getArea());

        Rectangle rectangle2 = new Square();
        rectangle2.setLength(4);
        rectangle2.setWidth(3);
        System.out.println(rectangle2.getArea());
    }

}

/**
 * 矩形
 */
class Rectangle {
    int length;
    int width;

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return length * width;
    }
}

/**
 * 正方形
 */
class Square extends Rectangle {

    @Override
    public void setLength(int length) {
        this.length = length;
        this.width = length;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.length = width;
    }
}
