package com.boss;
import com.atomic.Entity;
import com.game.RoomMap;
import com.game.Room;

public class SecondBoss implements CallBack{
    private final Entity boss=new Entity("Aurora",10,2,3);
    public boolean onCallback(Entity player, RoomMap map, Labirinth labirinth){
        Combat c=new Combat(this.boss,player);
        if(c.start()==0){
            Room r=map.get(3,3);
            r.setValid(true);
            map.set(3,3,r);
            return true;
        } else return false;
    }
}
