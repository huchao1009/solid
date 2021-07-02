//package com.huchao.solid.lsp.right;
//
///**
// * 正方形
// * @author double
// * @Date 2021/7/1 11:43 上午
// */
//class Square extends AbstractRectangle implements ClosedFigure {
//    private final int sideLength;
//
//    Square(int sideLength) {
//        this.sideLength = sideLength;
//    }
//
//    @Override
//    int getLength() {
//        return sideLength;
//    }
//
//    @Override
//    int getWidth() {
//        return sideLength;
//    }
//
//    @Override
//    public int getArea() {
//        return sideLength * sideLength;
//    }
//
//    /**
//     * 追加功能，获取边长
//     */
//    int getSideLength() {
//        return sideLength;
//    }
//}