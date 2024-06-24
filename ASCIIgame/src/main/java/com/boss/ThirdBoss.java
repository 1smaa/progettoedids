package com.boss;

import com.atomic.Entity;
import com.game.RoomMap;
import com.game.Room;

public class ThirdBoss implements CallBack {
    private final Entity boss=new Entity("Ismaele",15,3,1);
    public boolean onCallback(Entity player, RoomMap map, Labirinth labirinth){
        Combat c=new Combat(this.boss,player);
        if(c.start()==0){
            Room r=map.get(4,2);
            r.setValid(true);
            map.set(4,2,r);
            return true;
        } else return false;
    }
}
