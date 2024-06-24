package com.game;

import com.atomic.Entity;
import com.boss.*;

import java.io.FileNotFoundException;

public class GameManager {
    //Class attributes
    private RoomMap map;
    private Labirinth labirinth;
    private Entity player;
    //Save callback functions in sequence that correspond to triggers
    CallBack[] phases={new FirstBoss(),new SecondBoss(),new ThirdBoss(),new Labirinth(null),new FinalBoss()};
    //com.game.Room ID at which the correspondent callback should be triggered
    int[] triggers={2,4,5,7,12};
    //Index or phase of the com.game, to indicate next callback
    int ind=0;
    public GameManager(Entity player,RoomMap map,Labirinth lab){
        this.player=player;
        this.map=map;
        this.labirinth=lab;
        //Set the third phase to the labirinth passed as function
        this.phases[3]=this.labirinth;
    }
    //Move in the direction provided, returns 1 if the com.game has been beaten, 0 for normal activity, -1 for gameover
    public int move(char direction) throws FileNotFoundException {
        this.map.move(direction);
        //If the player is in the next trigger room
        if(this.map.currId()==this.triggers[this.ind]){
            //Call for boss fight, and await result
            boolean res=this.phases[this.ind].onCallback(this.player,this.map,this.labirinth);
            //If the fight has been won increment the phase index
            if(res) this.ind++;
            //Otherwise return gameover
            else return -1;
            //If the current index is over the limit of com.game phases, the com.game has been beaten and the player won
            if(this.ind==this.phases.length) return 1;
        }
        //Otherwise normal activity, return 0
        return 0;
    }
}
