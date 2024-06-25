package com.boss;
import com.atomic.Entity;
import com.game.RoomMap;
import com.atomic.Item;
public interface CallBack{
    boolean onCallback(Entity player, RoomMap map, Labirinth labirinth,Item[] inventory);
}
