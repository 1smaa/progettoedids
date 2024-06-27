package com.boss;

import com.atomic.Entity;
import com.atomic.Item;
import com.game.Room;
import com.game.RoomMap;
import com.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SecondBoss implements CallBack{
    private Entity boss=new Entity("Aurora",10,2,3);
    public boolean onCallback(Entity player, RoomMap map, Labirinth labirinth,Item[] inventory){
        /*Where Galileo once taught among old canals,
Europe's second-oldest, where knowledge sprawls.*/
        File baseOver=new File("src/main/resources/voidover.txt");
        String overlay="";
        try{
            Scanner scan=new Scanner(baseOver);
            while(scan.hasNextLine()) overlay+=scan.nextLine()+"\n";
        }catch(FileNotFoundException e){ return false; }
        String nOver=overlay;
        nOver=nOver.replace("1","You just got caught in a trap!                      ");
        nOver=nOver.replace("2","Solve the following riddle to continue.             ");
        tester.clearConsole();
        System.out.println(nOver);
        try{ Thread.sleep(5000); } catch(InterruptedException e){}
        tester.clearConsole();
        File multin=new File("src/main/resources/mult.txt");
        String mult="";
        try{
            Scanner scan=new Scanner(multin);
            while(scan.hasNextLine()) mult+=scan.nextLine()+"\n";
        }catch(FileNotFoundException e){ return false; }
        System.out.println(mult);
        Scanner scan=new Scanner(System.in);
        String response=scan.nextLine().toLowerCase();
        if(!response.toLowerCase().equals("a")) return false;
        nOver=overlay;
        nOver=nOver.replace("1","Choose your weapon                                  ");
        String nln="1- #1  2- #2  3- #3  4- #4";
        for(int i=0;i<inventory.length;i++){
            nln=nln.replace("#"+(i+1),inventory[i].longName);
        }
        for(int i=nln.length();i<52;i++) nln+=" ";
        nOver=nOver.replace("2",nln);
        tester.clearConsole();
        System.out.println(nOver);
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
        File fin=new File("src/main/resources/Boss/SecondBoss.txt");
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
            try{
                Room hall=new Room("src/main/resources/Rooms/3a.txt",14,true);
                map.set(3,2,hall);
            }catch(FileNotFoundException e){ return false; }
            Room r=map.get(3,3);
            r.setValid(true);
            map.set(3,3,r);
            return true;
        } else return false;
    }
}
