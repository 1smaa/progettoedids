import java.io.FileNotFoundException;
import java.io.Serializable;

public class LabNode implements Serializable {
    private LabNode left;
    private LabNode right;
    private LabNode back;
    private Room room;
    public boolean flag=false;
    public LabNode(LabNode l,LabNode r,LabNode b,Room rm){
        this.left=l;
        this.right=r;
        this.back=b;
        this.room=rm;
    }
    public LabNode getLeft(){ return this.left; }
    public LabNode getRight(){ return this.right; }
    public LabNode getBack(){ return this.back; }
    public String getView() throws FileNotFoundException { return this.room.load(); }
    public void toogleFlag() {this.flag=!this.flag;}
    public void setBack(LabNode b){ this.back=b; }
}
