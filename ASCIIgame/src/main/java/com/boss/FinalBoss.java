package com.boss;
import com.atomic.Entity;
import com.game.Room;
import com.game.RoomMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.atomic.Item;
import test.tester;

public class FinalBoss implements CallBack{
    private final Entity boss=new Entity("Pier",20,4,1);
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
        File fin=new File("src/main/resources/Boss/FinalBoss.txt");
        String s="";
        try{
            Scanner sc=new Scanner(fin);
            while(sc.hasNextLine()) s+=sc.nextLine()+"\n";
        }catch(FileNotFoundException e){ return false; }
        Combat c=new Combat(this.boss,player);
        return c.start(s)==0;
    }
}
