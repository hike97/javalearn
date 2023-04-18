package mianshi.javabasic.javase.c_002_override;

/**
 * @author: hike97
 * @createTime: 2023/04/16 19:30
 * @description:
 * 如果⽅法的返回类型是 void 和基本数据类型，则返回值重写时不可修改。
 * 但是如果⽅法的返回值是引⽤类型，重写时是可以返回该引⽤类型的⼦类的。
 */
public class Hero {
    public String name() {
        return "超级英雄";
    }
}
class SuperMan extends Hero{
    @Override
    public String name() {
        return "超⼈";
    }
    public Hero hero() {
        return new Hero();
    }
}

class SuperSuperMan extends SuperMan {

    @Override
    public String name() {
        return "超级超级英雄";
    }
    @Override
    public SuperMan hero() {
        return new SuperMan();
    }
}
