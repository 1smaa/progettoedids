import java.io.Serializable;

//Container to store in a simple way the needed objects in aws bucket
public class CloudContainer implements Serializable {
    public RoomMap rm;
    public Overlay ov;
    public CloudContainer(RoomMap rm, Overlay ov) {
        this.rm=rm;
        this.ov=ov;
    }
}
