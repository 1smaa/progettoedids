package com.cloud;

import java.io.Serializable;
import com.game.RoomMap;
import com.viewport.Overlay;

//Container to store in a simple way the needed objects in aws bucket
public class CloudContainer implements Serializable {
    public RoomMap rm;
    public Overlay ov;
    public CloudContainer(RoomMap rm, Overlay ov) {
        this.rm=rm;
        this.ov=ov;
    }
}
