package com.cloud;

import com.atomic.Entity;
import com.game.GameManager;
import com.game.RoomMap;

import java.io.Serializable;
//Container to store in a simple way the needed objects in aws bucket
public class CloudContainer implements Serializable {
    public RoomMap rm;
    public GameManager gm;
    public Entity player;
}
