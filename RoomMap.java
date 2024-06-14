import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class RoomMap {
    private Room[][] rooms;
    private Room current;
    private String currentStr;
    private Room last;
    private int x,y;
    public RoomMap(Room[][] roomArray,int startX,int startY) throws FileNotFoundException,IllegalArgumentException{
        //Check for file existence
        this.rooms=roomArray.clone();
        if((roomArray.length==0||(startX<0||startX>roomArray[0].length))||(startY<0||startY>roomArray.length)) throw new IllegalArgumentException();
        this.current=this.rooms[startY][startX];
        this.currentStr=this.current.load();
        this.last=new Room("",-1,false);
        this.x=startX;
        this.y=startY;
    }
    //Checks for bounds in room matrix
    private boolean checkBounds(int nX,int nY){
        return (nX>0&&nX<this.rooms[0].length)&&(nY>0&&nY<this.rooms.length);
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
    public boolean move(int code) throws FileNotFoundException{
        int newX=x,newY=y;
        switch(code){
            case 1:
                newY--;
                break;
            case 2:
                newY++;
                break;
            case 3:
                newX--;
                break;
            case 4:
                newX++;
                break;
            default:
                return false;
        }
        if(!checkBounds(newX, newY)) return false;
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
}
