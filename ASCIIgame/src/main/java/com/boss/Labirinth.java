package com.boss;

import com.atomic.Entity;
import com.atomic.Item;
import com.game.Room;
import com.game.RoomMap;
import com.tester;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Labirinth implements Serializable,CallBack {
    private LabNode head;
    private LabNode curr;
    public Labirinth(LabNode h){
        this.head=h;
        this.curr=this.head;
    }
    public boolean move(char comm){
        switch(comm) {
            case 's':
                return this.moveBack();
            case 'a':
                return this.moveLeft();
            case 'd':
                return this.moveRight();
            default:
                return false;
        }
    }
    private boolean moveBack(){
        LabNode back=this.curr.getBack();
        if(back==null) return false;
        else this.curr=back;
        return true;
    }
    private boolean moveLeft(){
        LabNode left=this.curr.getLeft();
        if(left==null) return false;
        else this.curr=left;
        return true;
    }
    private boolean moveRight(){
        LabNode right=this.curr.getRight();
        if(right==null) return false;
        else this.curr=right;
        return true;
    }
    public boolean win(){
        return this.curr.flag;
    }
    public String getCurr() throws FileNotFoundException {
        return this.curr.getView();
    }
    public boolean onCallback(Entity player, RoomMap map, Labirinth labirinth,Item[] inventory){
        Scanner scan=new Scanner(System.in);
        try{
            while(!this.curr.flag) {
                tester.clearConsole();
                System.out.println(this.getCurr());
                System.out.println("Q - Quit    A - Left    S - Back    D - Right\n");
                char b = scan.next().charAt(0);
                LabNode n = this.curr;
                switch (b) {
                    case 'q':
                        return false;
                    case 'a':
                        n = this.curr.getLeft();
                        if (n != null) {
                            this.curr = n;
                        }
                        break;
                    case 's':
                        n = this.curr.getBack();
                        if (n != null) {
                            this.curr = n;
                        }
                        break;
                    case 'd':
                        n = this.curr.getRight();
                        if (n != null) {
                            this.curr = n;
                        }
                        break;
                    default:
                        break;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
            scan.close();
            return false;
        }
        Room r=map.get(6,2);
        r.setValid(true);
        map.set(6,2,r);
        tester.clearConsole();
        return true;
    }

}
