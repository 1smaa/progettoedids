package com.boss;
import com.atomic.Entity;
import com.game.RoomMap;
import com.atomic.Item;

import java.io.Serializable;

public interface CallBack extends Serializable {
    boolean onCallback(Entity player, RoomMap map, Labirinth labirinth,Item[] inventory);
}
