package com.example.storm.sensortest.BuilderPattern;

/**
 * 建造者模式(Builder Pattern)：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。建造者模式是一种对象创建型模式。
 */


class Product {
    private String partA; //定义部件，部件可以是任意类型，包括值类型和引用类型
    private String partB;
    private String partC;

}

abstract class Builder {
    protected Product product = new Product();  //创建产品对象

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    //返回产品对象
    public Product getResult() {
        return product;
    }
}

/**
 * 在抽象类Builder中声明了一系列抽象的buildPartX()方法用于创建复杂产品的各个部件，具体建造过程在ConcreteBuilder中实现，
 * 此外还提供了工厂方法getResult()，用于返回一个建造好的完整产品。
 * 在ConcreteBuilder中实现了buildPartX()方法，通过调用Product的setPartX()方法可以给产品对象的成员属性设值。
 * 不同的具体建造者在实现buildPartX()方法时将有所区别，如setPartX()方法的参数可能不一样，
 * 在有些具体建造者类中某些setPartX()方法无须实现（提供一个空实现）。而这些对于客户端来说都无须关心，客户端只需知道具体建造者类型即可。
 * 在建造者模式的结构中还引入了一个指挥者类Director，该类主要有两个作用：一方面它隔离了客户与创建过程；另一方面它控制产品的创建过程，
 * 包括某个buildPartX()方法是否被调用以及多个buildPartX()方法调用的先后次序等。指挥者针对抽象建造者编程，客户端只需要知道具体建造者的类型，
 * 即可通过指挥者类调用建造者的相关方法，返回一个完整的产品对象。
 * 在实际生活中也存在类似指挥者一样的角色，如一个客户去购买电脑，电脑销售人员相当于指挥者，只要客户确定电脑的类型，
 * 电脑销售人员可以通知电脑组装人员给客户组装一台电脑。指挥者类的代码示例如下
 */
class Director {


    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    //产品构建与组装方法
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }

}

abstract class ConcreteBuilder extends Builder {

}

public class BuilderPattern {


    public static void main(String[] args) {
        Builder builder = new Builder() {
            @Override
            public void buildPartA() {

            }

            @Override
            public void buildPartB() {

            }

            @Override
            public void buildPartC() {

            }
        };
        Director director = new Director(builder);
        Product product = director.construct();
    }



}
