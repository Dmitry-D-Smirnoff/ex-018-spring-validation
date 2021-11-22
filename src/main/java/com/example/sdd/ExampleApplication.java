package com.example.sdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        listBeans(SpringApplication.run(ExampleApplication.class, args));
    }

    private static void listBeans(ConfigurableApplicationContext applicationContext){

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        System.out.println("BEANLIST:");
        for (String name : beanNames) {
            System.out.print(name + "; ");
        }
        System.out.println("\n" + beanNames.length + " beans listed");
    }

}
