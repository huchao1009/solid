package com.huchao.solid.lsp.right;

/**
 * @author double
 * @Date 2021/7/1 11:29 上午
 */
public class SquareTest {

    public static void main(String[] args) {
        ClosedFigure square = new Square(3);
        System.out.println(square.getArea());

        ClosedFigure rectangle = new Rectangle(4,3);
        System.out.println(rectangle.getArea());
    }

}

/**
 * 闭合的图形
 * @author double
 * @Date 2021/7/1 11:40 上午
 */
interface ClosedFigure {
    /**
     * 获取图形的面积
     * @return
     */
    int getArea();
}


/**
 * 抽象的矩形
 * @author double
 * @Date 2021/7/1 11:40 上午
 */
abstract class AbstractRectangle implements ClosedFigure {
    /**
     * 获取矩形的长
     * @return
     */
    abstract int getLength();

    /**
     * 获取矩形的宽
     * @return
     */
    abstract int getWidth();
}

/**
 * 长方形
 * @author double
 * @Date 2021/7/1 11:42 上午
 */
class Rectangle extends AbstractRectangle implements ClosedFigure {
    private final int length;
    private final int width;

    Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    int getLength() {
        return length;
    }

    @Override
    int getWidth() {
        return width;
    }

    @Override
    public int getArea() {
        return length * width;
    }
}

/**
 * 正方形
 * @author double
 * @Date 2021/7/1 11:43 上午
 */
class Square extends AbstractRectangle implements ClosedFigure {
    private final int sideLength;

    Square(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    int getLength() {
        return sideLength;
    }

    @Override
    int getWidth() {
        return sideLength;
    }

    @Override
    public int getArea() {
        return sideLength * sideLength;
    }

    /**
     * 追加功能，获取边长
     */
    int getSideLength() {
        return sideLength;
    }
}