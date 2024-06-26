package test;

import com.atomic.Entity;
import com.boss.Labirinth;
import com.cloud.CloudContainer;
import com.cloud.CloudUploader;
import com.game.GameManager;
import com.game.RoomMap;
import com.viewport.ViewportManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class tester {
    private static final String MAPFILE="stdmap.config";
    private static final String LABFILE="lab.config";
    private static final String MENU="S - Start game\nQ - Quit\nC - Continue\nM- Manual\n";
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    private static void cloudSave(Entity player,RoomMap map,GameManager gm){
        CloudContainer cc=new CloudContainer();
        cc.player=player;
        cc.rm=map;
        cc.gm=gm;
        CloudUploader cu=new CloudUploader(cc);
        boolean res=cu.upload();
        if(res){
            System.out.println("Saved successfully.");
        } else System.out.println("Game not saved. Error while uploading.");
    }
    private static void startGame(RoomMap map, Labirinth labirinth){
        clearConsole();
        Entity player=new Entity("Player",10,1,1);
        GameManager gm=new GameManager(player,map,labirinth);
        int result;
        Scanner sc=new Scanner(System.in);
        String overlay="";
        try{
            File fin=new File("src/main/resources/overlay.txt");
            Scanner scan=new Scanner(fin);
            while(scan.hasNextLine()) overlay+=scan.nextLine()+"\n";
            scan.close();
        }catch(FileNotFoundException e){ overlay=""; }
        ViewportManager vp=new ViewportManager();
        String OGoverlay=overlay;
        vp.setOverlay(overlay.replace("%",Integer.toString(gm.getInventoryWeight())));
        String visInv[]=gm.getVisInventory();
        for(int i=0;i<visInv.length;i++){
            //System.out.println(visInv[i]);
            //try{ Thread.sleep(3000); } catch(Exception e){ e.printStackTrace(); }
            overlay=overlay.replaceFirst("£",visInv[i]);
        }
        vp.setOverlay(overlay.replace("%",Integer.toString(gm.getInventoryWeight())));
        do{
            System.out.println(vp.format(map.curr()));
            char c=sc.next().charAt(0);
            if(c=='q') return;
            if(c=='p') cloudSave(player,map,gm);
            clearConsole();
            try{
                result=gm.move(c);
            }catch(FileNotFoundException e){
                result=-1;
            }
            visInv=gm.getVisInventory();
            String nOverlay=OGoverlay;
            for(int i=0;i<visInv.length;i++){
                //System.out.println(visInv[i]);
                //try{ Thread.sleep(3000); } catch(Exception e){ e.printStackTrace(); }
                nOverlay=nOverlay.replaceFirst("£",visInv[i]);
            }
            String g="";
            for(int i=0;i<player.health;i++) g+="O";
            for(int i=player.health;i<10;i++) g+="-";
            nOverlay=nOverlay.replace("OOOOOOOOOO",g);
            nOverlay=nOverlay.replace("%",Integer.toString(gm.getInventoryWeight()));
            vp.setOverlay(nOverlay);
        }while(result==0);
        sc.close();
        if(result==1) System.out.println("YOU WIN!");
        else System.out.println("GAME OVER");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){}
    }
    private static void printManual(){
        clearConsole();
        System.out.println("manuale");
    }
    private static void loadAndPlay(RoomMap map,Labirinth labirinth){
        clearConsole();
        CloudUploader cu=new CloudUploader(new CloudContainer());
        CloudContainer cc=cu.download();
        GameManager gm=cc.gm;
        Entity player=cc.player;
        map=cc.rm;
        int result;
        Scanner sc=new Scanner(System.in);
        String overlay="";
        try{
            File fin=new File("src/main/resources/overlay.txt");
            Scanner scan=new Scanner(fin);
            while(scan.hasNextLine()) overlay+=scan.nextLine()+"\n";
            scan.close();
        }catch(FileNotFoundException e){ overlay=""; }
        ViewportManager vp=new ViewportManager();
        String OGoverlay=overlay;
        vp.setOverlay(overlay.replace("%",Integer.toString(gm.getInventoryWeight())));
        String visInv[]=gm.getVisInventory();
        for(int i=0;i<visInv.length;i++){
            //System.out.println(visInv[i]);
            //try{ Thread.sleep(3000); } catch(Exception e){ e.printStackTrace(); }
            overlay=overlay.replaceFirst("£",visInv[i]);
        }
        vp.setOverlay(overlay.replace("%",Integer.toString(gm.getInventoryWeight())));
        do{
            System.out.println(vp.format(map.curr()));
            char c=sc.next().charAt(0);
            if(c=='q') return;
            if(c=='p') cloudSave(player,map,gm);
            clearConsole();
            try{
                result=gm.move(c);
            }catch(FileNotFoundException e){
                result=-1;
            }
            visInv=gm.getVisInventory();
            String nOverlay=OGoverlay;
            for(int i=0;i<visInv.length;i++){
                //System.out.println(visInv[i]);
                //try{ Thread.sleep(3000); } catch(Exception e){ e.printStackTrace(); }
                nOverlay=nOverlay.replaceFirst("£",visInv[i]);
            }
            String g="";
            for(int i=0;i<player.health;i++) g+="O";
            for(int i=player.health;i<10;i++) g+="-";
            nOverlay=nOverlay.replace("OOOOOOOOOO",g);
            nOverlay=nOverlay.replace("%",Integer.toString(gm.getInventoryWeight()));
            vp.setOverlay(nOverlay);
        }while(result==0);
        sc.close();
        if(result==1) System.out.println("YOU WIN!");
        else System.out.println("GAME OVER");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){}
    }
    public static void main(String[] args){
        RoomMap map;
        Labirinth labirinth;
        try(FileInputStream fin=new FileInputStream(MAPFILE);
            ObjectInputStream oin=new ObjectInputStream(fin)){
            map=(RoomMap)oin.readObject();
        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        try(FileInputStream fin=new FileInputStream(LABFILE);
            ObjectInputStream oin=new ObjectInputStream(fin)){
            labirinth=(Labirinth)oin.readObject();
        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        boolean broken=false;
        Scanner scan=new Scanner(System.in);
        clearConsole();
        System.out.println(MENU);
        while(!broken&&scan.hasNext()){
            switch(Character.toLowerCase(scan.next().charAt(0))){
                case 'q':
                    broken=true;
                    break;
                case 's':
                    startGame(map,labirinth);
                    break;
                case 'm':
                    printManual();
                    break;
                case 'c':
                    loadAndPlay(map,labirinth);
                    break;
                default:
                    break;
            }
            clearConsole();
            System.out.println(MENU);
        }
        scan.close();
    }
}
