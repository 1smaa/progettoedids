package com.cloud;

import java.io.Serializable;

import com.game.GameManager;
import com.game.RoomMap;
import com.atomic.Entity;
//Container to store in a simple way the needed objects in aws bucket
public class CloudContainer implements Serializable {
    public RoomMap rm;
    public GameManager gm;
    public Entity player;
}
