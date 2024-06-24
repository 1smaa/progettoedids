package test;

import com.atomic.Entity;
import com.boss.Labirinth;
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
    private static final String MENU="S - Start com.game\nQ - Quit\nC - Continue\nM- Manual\n";
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
    private static void startGame(RoomMap map, Labirinth labirinth){
        clearConsole();
        GameManager gm=new GameManager(new Entity("Player",10,1,1),map,labirinth);
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
        vp.setOverlay(overlay);
        do{
            System.out.println(vp.format(map.curr()));
            char c=sc.next().charAt(0);
            clearConsole();
            try{
                result=gm.move(c);
            }catch(FileNotFoundException e){
                result=-1;
            }
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
    private static void loadAndPlay(RoomMap map,Labirinth labirinth){}
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
        System.out.println(MENU);
        boolean broken=false;
        while(!broken){
            Scanner scan=new Scanner(System.in);
            switch(scan.next().charAt(0)){
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
            scan.close();
        }
    }
}
