package com.laoshiren.app.aspect.match;

import com.laoshiren.app.service.IService;
import com.laoshiren.app.service.impl.MyService;
import lombok.ToString;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.core.annotation.MergedAnnotations;

import java.lang.reflect.Method;

/**
 * @Author laoshiren
 * @Date 14:50 2023/2/2
 */
public class AspectMatchMain {

    public static void main(String[] args) throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.laoshiren.*..*.foo())");
        Method methodFoo = MyService.class.getMethod("foo");
        boolean matches = pointcut.matches(methodFoo, MyService.class);
        System.out.println(matches);

        AspectJExpressionPointcut pointcut2 = new AspectJExpressionPointcut();
        pointcut2.setExpression("@annotation(com.laoshiren.app.aspect.match.Ass)");
        boolean matches2 = pointcut2.matches(methodFoo, MyService.class);
        System.out.println(matches2);


        AnnotationMatchingPointcut annotationPointcut = new AnnotationMatchingPointcut(Ass.class);
        boolean matches1 = annotationPointcut
                .getMethodMatcher()
                .matches(methodFoo, MyService.class);
        System.out.println(matches1);

        StaticMethodMatcherPointcut staticPointcut = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 检查方法上是否加了对应注解
                boolean present = MergedAnnotations
                        .from(method)
                        .isPresent(Ass.class);
                // 查看类上是否加了对应注解
                boolean present1 = MergedAnnotations
                        .from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY)
                        .isPresent(Ass.class);
                return present || present1;
            }
        };
        System.out.println(staticPointcut.matches(A.class.getMethod("a"), A.class));
    }

}
class A {

    @Ass
    public void a(){

    }

}