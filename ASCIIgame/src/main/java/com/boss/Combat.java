package com.boss;

import com.atomic.Entity;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Combat {
    private Entity entity;
    private Entity player;
    public Combat(Entity entity,Entity player){
        //Constructor with two entities, the players and the boss to defeat
        this.entity=entity;
        this.player=player;
    }
    public int start(){
        //Start executor for time restrictions on input
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Create Scanner
        Scanner scanner = new Scanner(System.in);
        //Create random number generator
        Random random=new Random();
        //Create array of possible keys
        final String[] keys={"w","a","s","d"};
        //Starting health of the player
        int maxHealthPlayer=this.player.health;
        //Starting health of the entity
        int maxHeathEntity=this.entity.health;
        //While both are alive
        while(this.player.health>0&&this.entity.health>0){
            //Generate a random key
            String match=keys[random.nextInt(4)];
            //Create a new task
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() {
                    return scanner.nextLine();
                }
            };
            //Print both health of player and boss
            this.logHealth(maxHealthPlayer,maxHeathEntity);
            //Start task
            Future<String> future = executor.submit(task);
            //Print char to match
            System.out.println(match);
            try {
                //Get the result with timeout, otherwise TimeoutException is thrown
                String result = future.get(this.player.speed+this.entity.speed, TimeUnit.SECONDS);
                //If the result does not match the expected one a TimeoutException is thrown, as it would if the input was not typed in time
                if(!result.equals(match)) throw new TimeoutException();
                //If the player successfully pressed the key, do damage
                this.entity.health-=this.player.damage;
            } catch (TimeoutException e) {
                //Otherwise the player receives damage
                this.player.health-=this.entity.damage;
            } catch (InterruptedException | ExecutionException e ){
                //In other types of exceptions simply print the stacktrace and continue
                e.printStackTrace();
            }
            this.clearConsole();
        }
        //shutdown the executor
        executor.shutdown();
        //Define who has won, return 1 if the boss has won, 0 otherwise
        if(this.entity.health>0) return 1;
        else return 0;
    }
    //Function to log both health of player and boss
    private void logHealth(int maxHealthPlayer,int maxHeathEntity){
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
    }
    //Function to clear the console (simulate frames)
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
