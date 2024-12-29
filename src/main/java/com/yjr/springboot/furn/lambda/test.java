package com.yjr.springboot.furn.lambda;

public class test {
    public static void main(String[] args) {
        HspFunction<Desk,String> f1 = Desk::getName;
        String val = f1.apply(new Desk());
        System.out.println(val);

    }
}
@FunctionalInterface
interface HspFunction<T,R>{
    R apply(T t);
}

class Desk{
    private String name="hhh";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
