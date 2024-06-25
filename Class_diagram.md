
# Class diagram

![classDiagram](https://github.com/1smaa/progettoedids/assets/169902818/2c797e52-81dd-4405-a484-8ac404482aab)


```plantuml
@startuml
skinparam classAttributeIconSize 0

class Entity {
    + int health
    + int damage
    + int speed
    + String name
    + Entity(Stringm, int, int, int)
}

class Item {
    + int damage
    + int speed
    + int weight
    + String item
    + String longName
    + Item(int,int,String, int, String)
}

interface CallBack{
    boolean onCallBack(Entity, RoomMap, Labirinth)
}

class Combat {
    - Entity entity
    - Entity player
    + Combat(Entity, Entity)
    + int start()
    - void loghealth(int, int)
    - void clearConsole()
}

class FinalBoss {
    - {Static} Entity boss
}

class FirstBoss {
    - {static} Entity boss
}

class SecondBoss {
    - {static} Entity boss
}

class ThirdBoss {
    - {static} Entity boss
}

class LabNode {
    - LabNode left
    - LabNode right
    - LabNode back
    - Room room
    + boolean flag = false
    + LabNode(LabNode, LabNode, LabNode, Room)
    + LabNode getLeft()
    + LabNode getRight()
    + LabNode getBack()
    + String getView()
    + void toogleFlag()
    + boid setBack(LabNode)
}

class Labirinth {
    - LabNode head
    - LabNode curr
    + Labirinth(LabNode)
    + boolean move(char)
    - boolean moveBack()
    - boolean moveRight()
    - boolean moveLeft()
    + boolean win()
    + String getCurr()
}

class GameManager {
    - RoomMap map
    - Labirinth labirinth
    - Entity player
    - CallBack[] phases
    - int[] triggers
    - Item[] inventory
    - int ind = 0
    + GameManager(Entity, RoomMap, Labirinth)
    + int move(char)
    + String[] getVisInventory()
}

class Room {
    - String fileName
    - int id
    - boolean isValid
    + Room(String,int)
    + Room(Sring, int, boolean)
    + String load()
    + int getId()
    + boolean valid()
    + void setValid(boolean)
}

class RoomMap {
    - Room[][] rooms
    - Room current
    - String currentStr
    - Room last
    - int x,y
    + RoomMap(Room[][], int, int)
    - boolean checkBounds(int, int)
    + boolean hasLast()
    + String curr()
    + boolean move(char)
    + int currId()
    + Room get(int, int)
    + void set(int, int, Room)
}

class Overlay {
    - String overlay
    - int health
    - String[] inventory
    - int healthMax
    - int healthStart
    - int inventoryStart
    - {static} String HEALTHP=O
    - {static} String HEALTHN=-
    - {static} int I_ITEM_SIZE=5
    + Overlay(String, int, int, int, int, int, String[])
    - String inventoryFormat(String)
    + void setHealth(int)
    + void setInventory(String, int)
    + String getOverlay()
    + int getHealth()
    + String[] getInventory()
}

class ViewportManager {
    + String overlay
    - {static} char IGNORE=#
    + ViewportManager()
    + String format(String)
    + void setOverlay(String)
    + String getOverlay()
}

class tester{
    - {static} String MAPFILE="stdmap.config"
    - {static} String LABFILE="lab.config"
    - {static} String MENU="S - Start - Quit - Continue - Manual"
    + {static} void clearConsole()
    - {static} void startGame(RoomMap, Labirinth)
    - {static} void printManual()
    - {static} void loadAndPlay(RoomMap, Labirinth)
    + {static} void main(String[])
}

RoomMap "*" -right-* "*"  Room : Contiene ed istanzia
Combat "1" -right-* "*" Entity : Contiene ed istanzia
Overlay "1" -left-* "*" Item : Contiene ed istanzia
Overlay "1" -up-* "*" Entity : Contiene ed istanzia
LabNode "*" --* "*" Room : Contiene ed istanzia
Labirinth "1" -right-* "*" LabNode : Contiene ed istanzia
GameManager "1" -down-> "*" RoomMap : Utilizza
GameManager "1" -left-* "*" Entity : Contiene ed istanzia
GameManager "1" -up-> "1" Labirinth : Utilizza
GameManager "1" -down-> "1" ViewportManager : Utilizza
ViewportManager "1" -down-> "1" Overlay : Utilizza
tester "1" -up-> "1" GameManager : Lancia
FirstBoss "1" --* "*" Entity : Contiene ed istanzia
SecondBoss "1" --* "*" Entity : Contiene ed istanzia
ThirdBoss "1" --* "*" Entity : Contiene ed istanzia
FinalBoss "1" --* "*" Entity : Contiene ed istanzia
CallBack .down.> FinalBoss
CallBack .down.> FirstBoss
CallBack .down.> SecondBoss
CallBack .down.> ThirdBoss
CallBack ..> Labirinth

@enduml
```
