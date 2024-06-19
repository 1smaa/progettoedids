

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Combat {
    private Entity entity;
    private Entity player;
    public Combat(Entity entity,Entity player){
        this.entity=entity;
        this.player=player;
    }
    public int start(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Scanner scanner = new Scanner(System.in);
        Random random=new Random();
        String[] keys={"w","a","s","d"};
        int maxHealthPlayer=this.player.health;
        int maxHeathEntity=this.player.entity;
        while(this.player.health>0&&this.entity.health>0){
            String match=keys[random.nextInt(4)];
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() {
                    return scanner.nextLine();
                }
            };
            
            Future<String> future = executor.submit(task);
            System.out.println(match);
            try {
                String result = future.get(this.player.speed+this.entity.speed, TimeUnit.SECONDS);
                if(!result.equals(match)) throw new TimeoutException();
                this.entity.health-=this.player.damage;
            } catch (TimeoutException e) {
                this.player.health-=this.entity.damage;
            } catch (InterruptedException | ExecutionException e ){
                e.printStackTrace();
            } 
            String playerHealth="";
            for(int i=0;i<maxHealthPlayer;i++){
                if(i<this.player.health) playerHealth+="O";
                else playerHealth+="-";
            }
            String entityHealth="";
            for(int i=0;i<maxHeathEntity;i++){
                if(i<this.entity.health) entityHealth+="O";
                else entityHealth+="-";
            }
            System.out.println(playerHealth);
            System.out.println(entityHealth);
            this.clearConsole();
        }
        if(this.entity.health>0) return 1;
        else return 0;
    }
    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based systems
                System.out.print("\033[H\033[2J");  
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
