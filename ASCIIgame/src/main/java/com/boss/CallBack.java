package com.boss;
import com.atomic.Entity;
import com.game.RoomMap;

public interface CallBack{
    boolean onCallback(Entity player, RoomMap map, Labirinth labirinth);
}
