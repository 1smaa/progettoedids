package com.atomic;

import java.io.Serializable;

public class Entity implements Serializable {
    public int health;
    public int damage;
    public int speed;
    public String name;
    public Entity(String name,int health,int damage,int speed){
        this.name=name;
        this.health=health;
        this.damage=damage;
        this.speed=speed;
    }
}
