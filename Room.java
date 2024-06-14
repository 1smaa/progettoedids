import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Room {
    private String fileName;
    private int id;
    private boolean isValid;
    public Room(String fn,int id) throws FileNotFoundException{
        //check for file existence
        new File(fn);
        this.fileName=fn;
        this.id=id;
        this.isValid=false;
    }
    public Room(String fn,int id,boolean valid) throws FileNotFoundException{
        new File(fn);
        this.fileName=fn;
        this.id=id;
        this.isValid=valid;
    }
    public String load() throws FileNotFoundException{
        Scanner scan=new Scanner(new File(this.fileName));
        String res="";
        while(scan.hasNextLine()){
            res+=scan.nextLine()+"\n";
        }
        scan.close();
        return res;
    }
    public int getId(){
        return this.id;
    }
    public boolean valid(){
        return this.isValid;
    }
    public void setValid(boolean v){
        this.isValid=v;
    }
}
