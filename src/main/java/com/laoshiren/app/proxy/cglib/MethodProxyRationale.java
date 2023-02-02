package com.laoshiren.app.proxy.cglib;

import org.springframework.cglib.core.Signature;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author laoshiren
 * @Date 13:09 2023/2/2
 */
public class MethodProxyRationale {

    public static void main(String[] args) {
        Target target = new Target();
        TargetFastClass targetFastClass = new TargetFastClass();
        int saveIndex = targetFastClass.getIndex(new Signature("save", "()V"));
        targetFastClass.invoke(saveIndex,target,new Object[]{});
    }

}


class TargetFastClass {

    static Signature s0 = new Signature("save", "()V");
    static Signature s1 = new Signature("save", "(I)V");
    static Signature s2 = new Signature("save", "(J)V");

    // 获取一个目标方法的编号
    public int getIndex(Signature signature) {
        if (signature.equals(s0)) {
            return 0;
        } else if (signature.equals(s1)) {
            return 1;
        } else if (signature.equals(s2)) {
            return 2;
        }
        return -1;
    }

    // 根据方法编号正常调用对象方法
    public Object invoke(int index, Object target, Object[] args) {
        if (index == 0) {
            ((Target) target).save();
            return null;
        } else if (index == 1) {
            ((Target) target).save((int) args[0]);
            return null;
        } else if (index == 2) {
            ((Target) target).save((long) args[0]);
            return null;
        }
        throw new RuntimeException("no such method");
    }

}
