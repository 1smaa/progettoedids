public class FirstBoss implements CallBack{
    private final Entity boss=new Entity("Elvis",10,1,4);
    public boolean onCallback(Entity player,RoomMap map,Labirinth labirinth){
        Combat c=new Combat(this.boss,player);
        if(c.start()==0){
            Room r=map.get(1,3);
            r.setValid(true);
            map.set(1,3,r);
            return true;
        }else return false;
    }
}
