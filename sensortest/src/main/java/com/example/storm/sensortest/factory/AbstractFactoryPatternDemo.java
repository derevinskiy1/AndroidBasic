package com.example.storm.sensortest.factory;


interface Shape1 {
    void draw();
}

class Rectangle1 implements Shape1 {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle1::draw() method.");
    }
}

class Square1 implements Shape1 {
    @Override
    public void draw() {
        System.out.println("Inside Square1::draw() method.");
    }
}

class Circle1 implements Shape1 {
    @Override
    public void draw() {
        System.out.println("Inside Circle1::draw() method.");
    }
}

interface Color {
    void fill();
}

class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}

class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}

class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape1 getShape1(String Shape1);
}
class Shape1Factory extends AbstractFactory {
    @Override
    public Shape1 getShape1(String Shape1Type) {
        if (Shape1Type == null) {
            return null;
        }
        if (Shape1Type.equalsIgnoreCase("Circle1")) {
            return new Circle1();
        } else if (Shape1Type.equalsIgnoreCase("Rectangle1")) {
            return new Rectangle1();
        } else if (Shape1Type.equalsIgnoreCase("Square1")) {
            return new Square1();
        }
        return null;
    }
    @Override
    Color getColor(String color) {
        return null;
    }
}
public class AbstractFactoryPatternDemo {


}
