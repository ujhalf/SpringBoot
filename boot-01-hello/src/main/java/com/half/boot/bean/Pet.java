package com.half.boot.bean;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/24 11:29
 * @Version 1.0
 * @Description
 */
public class Pet {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}
