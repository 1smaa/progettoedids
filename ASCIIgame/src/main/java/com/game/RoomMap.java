package com.game;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class RoomMap implements Serializable {
    private Room[][] rooms;
    private Room current;
    private String currentStr;
    private Room last;
    private int x,y;
    public RoomMap(Room[][] roomArray,int startX,int startY) throws FileNotFoundException,IllegalArgumentException{
        //Check for file existence
        this.rooms=roomArray;
        if((roomArray.length==0||(startX<0||startX>roomArray[0].length))||(startY<0||startY>roomArray.length)) throw new IllegalArgumentException();
        this.current=this.rooms[startY][startX];
        this.currentStr=this.current.load();
        this.last=new Room("src/main/resources/Rooms/1.txt",-1,false);
        this.x=startX;
        this.y=startY;
    }
    //Checks for bounds in room matrix
    private boolean checkBounds(int nX,int nY){
        return ((nY>=0&&nY<this.rooms.length)&&(nX>=0&&nX<this.rooms[nY].length));
    }
    //returns true if there was a room before the current one
    public boolean hasLast(){
        return this.last.valid();
    }
    //returns current room string representation
    public String curr(){
        return this.currentStr;
    }
    //moves the player to the chosen room, true if possible, false otherwise
    public boolean move(char code) throws FileNotFoundException{
        int newX=x,newY=y;
        switch(code){
            case 'w':
                newY--;
                break;
            case 's':
                newY++;
                break;
            case 'a':
                newX--;
                break;
            case 'd':
                newX++;
                break;
            default:
                return false;
        }
        if((this.rooms[newY][newX] == null||!checkBounds(newX, newY))||!this.rooms[newY][newX].valid()) return false;
        this.last=this.current;
        this.current=this.rooms[newY][newX];
        this.currentStr=this.current.load();
        this.x=newX;
        this.y=newY;
        return true;
    }
    //returns current room id
    public int currId(){
        return this.current.getId();
    }
    public Room get(int x,int y){
        return this.rooms[y][x];
    }
    public void set(int x,int y,Room r){
        this.rooms[y][x]=r;
    }
}
