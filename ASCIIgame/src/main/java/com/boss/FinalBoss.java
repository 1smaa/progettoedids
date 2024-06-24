package com.boss;
import com.atomic.Entity;
import com.game.RoomMap;
public class FinalBoss implements CallBack{
    private final Entity boss=new Entity("Pier",20,4,1);
    public boolean onCallback(Entity player, RoomMap map, Labirinth labirinth){
        Combat c=new Combat(this.boss,player);
        return c.start()==0;
    }
}
