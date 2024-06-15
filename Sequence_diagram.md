
# Internal Sequence Diagrams

## move(int code)

![image](https://github.com/1smaa/progettoedids/assets/74701801/f4bbf748-605e-460f-947b-5a8f40294b95)

```plantuml
@startuml
!theme materia-outline
autonumber
skinparam ArrowColor #00B4D8
skinparam ActorBorderColor #03045E
skinparam ActorFontColor #03045E
skinparam ActorBackgroundColor #CAF0F8
skinparam ParticipantFontColor #03045E
skinparam ParticipantBorderColor #03045E
skinparam ParticipantBackgroundColor #90E0EF
skinparam DatabaseBorderColor #03045E
skinparam DatabaseBackgroundColor #00B4D8
skinparam DatabaseFontColor #03045E
skinparam BackgroundColor #FFFFFF

actor Giocatore
participant RoomMap
participant Room
database File



Giocatore -> RoomMap : move(int code)
activate RoomMap


alt code == 1
RoomMap -> RoomMap : newY--

else code == 2
RoomMap -> RoomMap : newY++

else code == 3
RoomMap -> RoomMap : newX--

else code == 4
RoomMap -> RoomMap : newX++

end

RoomMap -> RoomMap : checkBounds(newX, newY)
RoomMap -> Room : current = rooms[newX][newY]
activate Room
Room --> RoomMap : room
RoomMap -> Room : load()

Room -> File : scanner(filename)
activate File
File --> Room
Room --> RoomMap : String result
alt 
File --> Room : FileNotFoundException()
Room --> RoomMap : FileNotFoundException()
end
deactivate File
deactivate Room
deactivate RoomMap

@enduml

```

## Save(string saveName, int saveType

![image](https://github.com/1smaa/progettoedids/assets/74701801/289272f3-6894-4fbe-b722-91131f76553b)



```
@startuml
!theme materia-outline
autonumber
skinparam ArrowColor #00B4D8
skinparam ActorBorderColor #03045E
skinparam ActorFontColor #03045E
skinparam ActorBackgroundColor #CAF0F8
skinparam ParticipantFontColor #03045E
skinparam ParticipantBorderColor #03045E
skinparam ParticipantBackgroundColor #90E0EF
skinparam DatabaseBorderColor #03045E
skinparam DatabaseBackgroundColor #00B4D8
skinparam DatabaseFontColor #03045E
skinparam BackgroundColor #FFFFFF

actor Giocatore
participant Main
participant RoomMap
participant Overlay
database File
database AWS

Giocatore -> Main : Save(string saveName, int saveType)

activate Main

Main -> RoomMap : getCurr()
activate RoomMap
RoomMap --> Main :
deactivate RoomMap


Main -> Overlay : getOverlay()
activate Overlay
Overlay --> Main :

Main -> Overlay : getHealth()
Overlay --> Main :

Main -> Overlay : getInventory()
Overlay --> Main :

deactivate Overlay

alt saveType == 1

alt connessione internet funzionante

Main -> Main : checkFileName()

alt Valid name

Main -> AWS : Salvataggio Online (saveName)
activate AWS
Main --> Giocatore : Alert "Salvataggio completato!"

else Invalid name
Main --> Giocatore : Alert "Salvataggio già presente"

loop invalid
Main -> Giocatore : Alert "Inserire nuovo nome salvataggio"
Giocatore --> Main
Main -> AWS : Salvataggio Online (saveName)
deactivate AWS

Main -> Giocatore : Alert "Salvataggio completato!"

end
end


else connessione internet non funzionante

Main --> Giocatore : alert "Internet non funzionante"

end

else saveType == 2
Main -> Main : checkFileName()

alt Valid name

Main -> File : Salvataggio Offline(saveName)
activate File
Main --> Giocatore : Alert "Salvataggio completato!"

else Invalid name
Main --> Giocatore : Alert "Salvataggio già presente"

loop invalid
Main -> Giocatore : Alert "Inserire nuovo nome salvataggio"
Giocatore --> Main
Main -> File : Salvataggio Offline(saveName)
deactivate File

Main -> Giocatore : Alert "Salvataggio completato!"

end
end
Main -> File : Salvataggio Offline (saveName)
activate File
File --> Main
deactivate File
Main --> Giocatore : Alert "Salvataggio completato!"

deactivate Main
end

@enduml
```
