package com.boss;

import com.atomic.Entity;
import com.game.RoomMap;
import com.game.Room;
import com.atomic.Item;
import test.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ThirdBoss implements CallBack {
    private final Entity boss=new Entity("Ismaele",15,3,1);
    public boolean onCallback(Entity player, RoomMap map, Labirinth labirinth,Item[] inventory){
        File baseOver=new File("src/main/resources/voidover.txt");
        String overlay="";
        try{
            Scanner scan=new Scanner(baseOver);
            while(scan.hasNextLine()) overlay+=scan.nextLine()+"\n";
        }catch(FileNotFoundException e){ return false; }
        String nOver=overlay;
        nOver=nOver.replace("1","Choose your weapon                                  ");
        String nln="1- #1  2- #2  3- #3  4-#4";
        for(int i=0;i<inventory.length;i++){
            nln=nln.replace("#"+(i+1),inventory[i].longName);
        }
        for(int i=nln.length();i<52;i++) nln+=" ";
        nOver=nOver.replace("2",nln);
        tester.clearConsole();
        System.out.println(nOver);
        Scanner scan=new Scanner(System.in);
        char weapon=scan.next().charAt(0);
        Item w=null;
        switch(weapon){
            case '1':
                w=inventory[0];
                break;
            case '2':
                w=inventory[1];
                break;
            case '3':
                w=inventory[2];
                break;
            case '4':
                w=inventory[4];
                break;
            default: break;
        }
        if(w!=null&&w.item.equals("-")) w=null;
        if(w!=null){
            player.damage+=w.damage;
            player.speed+=w.speed;
        }
        File fin=new File("src/main/resources/Boss/ThirdBoss.txt");
        String s="";
        try{
            Scanner sc=new Scanner(fin);
            while(sc.hasNextLine()) s+=sc.nextLine()+"\n";
        }catch(FileNotFoundException e){ return false; }
        tester.clearConsole();
        Combat c=new Combat(this.boss,player);
        Boolean b=c.start(s)==0;
        if(w!=null){
            player.damage-=w.damage;
            player.speed-=w.speed;
        }
        if(b){
            String message="Ismaele left his sword, pick it up? (Y/N)           ";
            String desc="+3 Damage +2 Speed                                  ";
            overlay=overlay.replace("1",message);
            overlay=overlay.replace("2",desc);
            tester.clearConsole();
            System.out.println(overlay);
            char res=scan.next().charAt(0);
            if(res=='y'){
                for(int i=0;i<inventory.length;i++){
                    if(inventory[i].item.equals("-")){
                        inventory[i]=new Item(3,2,"S",1,"Sword");
                        break;
                    }
                }
            }
            tester.clearConsole();
            try{
                Room hall=new Room("src/main/resources/Rooms/3b.txt",15,true);
                map.set(3,2,hall);
            }catch(FileNotFoundException e){ return false; }
            Room r=map.get(4,2);
            r.setValid(true);
            map.set(4,2,r);
            return true;
        } else return false;
    }
}
