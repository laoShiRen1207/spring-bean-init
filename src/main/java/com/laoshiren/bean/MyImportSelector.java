package com.laoshiren.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author laoshiren
 * @Date 13:04 2023/1/5
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata metadata) {
        System.out.println("metadata: "+metadata.getClassName());
        System.out.println("metadata: "+metadata.hasAnnotation("org.springframework.context.annotation.Configuration"));
        System.out.println("metadata: "+metadata.getAllAnnotationAttributes("org.springframework.context.annotation.ComponentScan"));

        return new String[]{"com.laoshiren.bean.Dog"};
    }

}
