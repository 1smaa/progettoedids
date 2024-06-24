package test;

import com.boss.LabNode;
import com.boss.Labirinth;
import com.game.Room;
import com.game.RoomMap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class setter{
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(System.getProperty("user.dir"));
        Room r1=new Room("src/main/resources/Rooms/1.txt",1,true);
        Room inv=new Room("src/main/resources/Rooms/1.txt",-1,false);
        Room r2=new Room("src/main/resources/Rooms/2.txt",2,true);
        Room r3=new Room("src/main/resources/Rooms/3.txt",3,true);
        Room r4=new Room("src/main/resources/Rooms/4.txt",4,true);
        Room r5=new Room("src/main/resources/Rooms/5.txt",5,false);
        Room r6=new Room("src/main/resources/Rooms/6.txt",6,false);
        Room r7=new Room("src/main/resources/Rooms/7.txt",7,true);
        Room r8=new Room("src/main/resources/Rooms/8.txt",8,false);
        Room r9=new Room("src/main/resources/Rooms/9.txt",9,true);
        Room r10=new Room("src/main/resources/Rooms/10.txt",10,true);
        Room r11=new Room("src/main/resources/Rooms/11.txt",11,true);
        Room r12=new Room("src/main/resources/Rooms/12.txt",12,false);
        Path dirPath=Paths.get("src/main/resources/Rooms");
        Room[][] rm={{inv,inv,inv,inv,inv,inv,inv,inv,inv},
                {inv,inv,inv,r4,inv,inv,inv,inv,inv},
                {inv,r1,r2,r3,r6,r7,r12,inv,inv},
                {inv,inv,inv,r5,inv,inv,inv,inv,inv},
                {inv,inv,inv,inv,inv,inv,inv,inv,inv}};
        RoomMap rmMap=new RoomMap(rm,1,2);
        LabNode f=new LabNode(null,null,null,r9);
        f.toogleFlag();
        LabNode n16=new LabNode(null,f,null,r11);
        f.setBack(n16);
        LabNode n15=new LabNode(null,null,null,r9);
        LabNode n14=new LabNode(n16,n15,null,r8);
        n15.setBack(n14);
        LabNode n13=new LabNode(null,null,null,r9);
        LabNode n12=new LabNode(n13,n16,null,r8);
        n13.setBack(n12);
        LabNode n11=new LabNode(null,n12,null,r11);
        n12.setBack(n11);
        LabNode n10=new LabNode(n11,null,null,r10);
        n11.setBack(n10);
        LabNode n9=new LabNode(n14,null,null,r10);
        LabNode n8=new LabNode(null,null,null,r9);
        LabNode n7=new LabNode(n9,n8,null,r8);
        n9.setBack(n7);
        n8.setBack(n7);
        LabNode n6=new LabNode(null,null,null,r9);
        LabNode n5=new LabNode(n10,n6,null,r8);
        n6.setBack(n5);
        LabNode n4=new LabNode(null,n7,null,r11);
        n7.setBack(n4);
        LabNode n3=new LabNode(n5,n4,null,r8);
        n4.setBack(n3);
        n5.setBack(n3);
        LabNode n2=new LabNode(null,null,null,r9);
        LabNode head=new LabNode(n3,n2,null,r8);
        n3.setBack(head);
        n2.setBack(head);
        Labirinth l=new Labirinth(head);
        try(FileOutputStream fout=new FileOutputStream("stdmap.config");
            ObjectOutputStream oos=new ObjectOutputStream(fout)){
            oos.writeObject(rmMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        try(FileOutputStream fout=new FileOutputStream("lab.config");
        ObjectOutputStream oos=new ObjectOutputStream(fout)){
            oos.writeObject(l);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}