package com.boss;
import com.atomic.Entity;
import com.game.RoomMap;
import com.game.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.atomic.Item;
import test.tester;

public class FirstBoss implements CallBack{
    private final Entity boss=new Entity("Elvis",10,1,4);
    public boolean onCallback(Entity player, RoomMap map, Labirinth labirinth,Item[] inventory){
        File fin=new File("src/main/resources/Boss/FirstBoss.txt");
        String s="";
        try{
            Scanner scan=new Scanner(fin);
            while(scan.hasNextLine()) s+=scan.nextLine()+"\n";
        }catch(FileNotFoundException e){ return false; }
        Combat c=new Combat(this.boss,player);
        Boolean b=c.start(s)==0;
        if(b){
            File ov=new File("src/main/resources/voidover.txt");
            String f="";
            try{
                Scanner scan=new Scanner(ov);
                while(scan.hasNextLine()) f+=scan.nextLine()+"\n";
            }catch(FileNotFoundException e) { return false; }
            String message="Elvis dropped a dagger, pick it up? (Y/N)           ";
            String desc="+1 Damage +1 Speed                                  ";
            f=f.replace("1",message);
            f=f.replace("2",desc);
            tester.clearConsole();
            System.out.println(f);
            Scanner scan=new Scanner(System.in);
            char res=scan.next().charAt(0);
            if(res=='y'){
                for(int i=0;i<inventory.length;i++){
                    if(inventory[i].item.equals("-")){
                        inventory[i]=new Item(1,1,"D",1,"Dagger");
                        break;
                    }
                }
            }
            tester.clearConsole();
            Room r=map.get(1,3);
            r.setValid(true);
            map.set(1,3,r);
            return true;
        }else return false;
    }
}
