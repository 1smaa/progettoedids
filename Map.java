import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Map {
    private File f;
    private String fn;
    private int Xcoord;
    private int Ycoord;
    private int VPwidth;
    private int VPheight;
    private String vCache;

    private void load(){
        try{
            Scanner scan=new Scanner(this.f);
            for(int j=0;scan.hasNextLine()&&j<this.Ycoord;j++) scan.nextLine();
            String newCache="";
            for(int i=0;scan.hasNextLine()&&i<this.VPheight;i++){
                String line=scan.nextLine();
                newCache+=line.substring(this.Xcoord,Math.min(line.length(),this.Xcoord+this.VPwidth))+(i<this.VPheight-1 ? "\n" : "");
            }
            scan.close();
            this.vCache=newCache;
        }catch(FileNotFoundException e){
            System.out.println("File not found.\n");
            e.printStackTrace();
        }
    }

    public Map(String filename,int VPw,int VPh,int x,int y) {
        this.fn =filename;
        this.f=new File(this.fn);
        this.VPheight =VPh;
        this.VPwidth =VPw;
        this.Xcoord =x;
        this.Ycoord =y;
        this.load();
    }

    public Map(String filename,int VPw,int VPh) {
        this.fn =filename;
        this.f=new File(this.fn);
        this.VPheight =VPh;
        this.VPwidth =VPw;
        this.Xcoord =0;
        this.Ycoord =0;
        this.load();
    }

    public String getViewport(){
        return this.vCache;
    }

    public void move(int newX,int newY){
        this.Xcoord=newX;
        this.Ycoord=newY;
        this.load();
    }

    public int getMaxX(){
        int min=-1;
        try{
            Scanner scan=new Scanner(this.f);
            if(!scan.hasNextLine()) return 0;
            min=scan.nextLine().length();
            while(scan.hasNextLine()){
                int n=scan.nextLine().length();
                if(n<min) min=n;
            }
            scan.close();
        }catch(FileNotFoundException e) {
            System.out.println("File not found.\n");
            e.printStackTrace();
        }
        return min;
    }

    public int getMaxY(){
        int n=0;
        try{
            Scanner scan=new Scanner(this.f);
            while(scan.hasNextLine()) {
                scan.nextLine();
                n++;
            }
            scan.close();
        }catch(FileNotFoundException e) {
            System.out.println("File not found.\n");
            e.printStackTrace();
        }
        return n;
    }

    public int getX(){
        return this.Xcoord;
    };

    public int getY(){
        return this.Ycoord;
    };
}
