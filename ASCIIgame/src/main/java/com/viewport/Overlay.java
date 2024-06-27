package com.viewport;

public class Overlay {
    private String overlay;
    private int health;
    private String[] inventory;
    private int healthMax;
    private int healthStart;
    private int inventoryStart;
    private final String HEALTHP="O";
    private final String HEALTHN="-";
    private final int I_ITEM_SIZE=5; // |  -  |
    public Overlay(String template, int health,int healthMax,int healthStart,int inventoryMax, int inventoryStart,String[] inventory){
        this.overlay=template;
        this.health=health;
        this.healthMax=healthMax;
        this.healthStart=healthStart;
        this.inventoryStart=inventoryStart;
        for(int i=0;i<inventory.length;i++){
            this.inventory[i]=this.inventoryFormat(inventory[i]);
        }
    }
    private String inventoryFormat(String s) throws IllegalArgumentException{
        if(s.length()>I_ITEM_SIZE){
            throw new IllegalArgumentException("Inventory item out of bounds.");
        }
        else if(s.length()==I_ITEM_SIZE) return s;
        else {
            int diff=I_ITEM_SIZE-s.length();
            for(int i=0;i<Math.floor(diff/2);i++) s=" "+s;
            for(int i=0;i<Math.ceil(diff/2);i++) s+=" ";
            return s;
        }
    }
    public void setHealth(int healthN) throws IllegalArgumentException{
        if(healthN>this.healthMax||healthN<0) throw new IllegalArgumentException("Health must stay between bounds.");
        String n="";
        for(int i=0;i<healthN;i++){
            n+=HEALTHP;
        }
        for(int i=healthN;i<this.healthMax;i++){
            n+=HEALTHN;
        }
        StringBuilder sb=new StringBuilder(this.overlay);
        sb.replace(this.healthStart,this.healthStart+this.healthMax,n);
        this.overlay=sb.toString();
    }
    public void setInventory(String item,int index){
        int strIndex=1+(index*(I_ITEM_SIZE+1));
        String strItem=this.inventoryFormat(item);
        StringBuilder sb=new StringBuilder(this.overlay);
        sb.replace(this.inventoryStart+strIndex,this.inventoryStart+strIndex+I_ITEM_SIZE,strItem);
        this.overlay=sb.toString();
    }
    public String getOverlay(){
        return this.overlay;
    }
    public int getHealth(){
        return this.health;
    }
    public String[] getInventory(){
        return this.inventory;
    }
}
