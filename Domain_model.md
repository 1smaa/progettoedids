
# Domain model

![domainModel]([https://www.plantuml.com/plantuml/png/bP5FImCn4CNl-HHXZq8BNZr8RK4jL2eAUfvsXsuqVqfcjgM8tzrqsB311i6zx4lcDs-Il1anHEwTLM7pWLlMpnPE6FMNqkdh42nt8bjmi4EHj4TirNVkc0UY__1BVq3F8Pw6fYMZ4t54i-iuWMi1fJ0m7XpMRMkDAzYOQ3ntEkyyfFwids2VvKi8Bkk53NqxxxUVo7dh_coR5-_6h-Cg42frTjFDTQCdaolvZmDlz29u1kFfaag3otfQ5kNfTDh8UUh8xSMSfT-6k0yH64cDR_hBKTwmS1pY7Ks5aeicJFwa9D6_aT4dNs_CDL6FG4h8qgOupzqHObivJID99U2L8NBnXhcSKn36x3pOgak9lHdgmHf2KaN6rQ5mID0CVTix-mC0])


```plantuml
@startuml
object Player {
    health
    damage
    speed
}
object Boss {
    health
    damage
    speed
}
object Inventory {
    Item
    weight
}
Object Item {
    name
    damage
    speed
    weight
}
Object Labirinth 
Object Game
Object Map
Object Room
Object CloudBucket
Object Fight
Object WinOrLose

Game "1" --* "1" Labirinth : Contains
Game "1" --* "1" Map : Contains
Map "1" --* "1..." Room : Contains
Map "1" --> "1" Labirinth : Incorporates
Inventory "1" --* "1..." Item : Contains
Player "1" --> "1..." Fight : Fights
Boss "1" --> "1..." Fight : Fights
Player "1" --* "1" Inventory : Has
Fight "1" --> "1..." Item : Awards
Player "1" --> "1" Game : Starts
Game "1" --> "1" WinOrLose : Signals
Player "1" --> "1" WinOrLose : Visualises
CloudBucket "1" --> "1" Game : Saves
@enduml
```
