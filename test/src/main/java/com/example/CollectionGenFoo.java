package com.example;

import java.util.Collection;

/**
 * 高级应用
 * 限制泛型
 * 在上面的例子中，由于没有限制class GenericsFoo类型持有者T的范围，实际上这里的限定类型相当于Object，
 * 这和“Object泛型”实质是一样的。限制比如我们要限制T为集合接口类型。
 * class GenericsFoo，这样类中的泛型T只能是Collection接口的实现类，
 * 传入非Collection接口编译会出错。
 * 注意：这里的限定使用关键字extends，后面可以是类也可以是接口。但这里的extends已经不是继承的含义了，
 * 应该理解为T类型是实现Collection接口的类型，或者T是继承了XX类的类型。
 */
public class CollectionGenFoo<T extends Collection> {

    private T x;

    public CollectionGenFoo(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }


}
