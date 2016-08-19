package com.lip.model;

import java.util.Random;

/**
 * Created by Lip on 2016/8/15 0015.
 * 麻将中的骰子
 */
public class Dice {
    private  int value;
    private Random random=new Random();
    public Dice()
    {
        init();
    }
    private void init()
    {
        this.value=random.nextInt(7);
        while(this.value==0)
        {
            this.value=random.nextInt(7);
        }
    }
    public int getValue() {
        return value;
    }
    public void doDice()
    {
        init();
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++) {
            Dice dice=new Dice();
            System.out.println(dice.getValue());
        }
    }
}
