package com.atomic;

public class Item {
    public int damage;
    public int speed;
    public int weight;
    public String item;
    public String longName;
    public Item(int damage,int speed,String item,int weight,String longName){
        this.damage=damage;
        this.speed=speed;
        this.item=item;
        this.weight=weight;
        this.longName=item;
    }
}
