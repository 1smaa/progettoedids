Ciao!

Sei pronto ad immergerti in un gioco fatto di *indovinelli*, *combattimenti* e *velocità*?

Allora questo è il gioco giusto per **te!**

---

## Come avviare il gioco?

Prima di partire, ecco a te le **istruzioni** per poter avviare il gioco:

<aside>
❗ Ricorda, il gioco si basa sul linguaggio Java, presta attenzione che sia installata la versione 8.0 o successiva!

</aside>

1. Come prima cosa, scarica il progetto collegandoti a Github, ecco a te il link della repository: https://github.com/1smaa/progettoedids
2. Scarica la cartella del gioco, cliccando su “*Download ZIP”*:



1. Estrai la cartella
2. Avvia il terminale
3. Tramite i comandi ***cd*** e ***ls*** spostati all’interno della cartella estratta
4. Portati all'interno della cartella *ASCIIgame*
5. Avvia il gioco tramite il comando 

```markdown
java -jar target/ASCIIgame-1.0.jar
```

---

## Come si gioca? 🎮

Il gioco è un ***text-based game:***


💡 *A **text game** or **text-based game** is an electronic game that uses a text-based user interface, that is, the user interface employs a set of encodable characters, such as ASCII, instead of bitmap or vector graphics.*


Lo scopo del gioco è quello di recuperare il cristallo perduto, sappiamo che è custodito da un tuo perfetto duplicato! 💠

Il gioco è composto da diverse stanze, ci si può muovere all’interno delle diverse stanze tramite i comandi:

- **W**: accedi alla stanza sopra a te
- **A**: accedi alla stanza alla tua sinistra
- **S**: accedi alla stanza sotto di te
- **D**: accedi alla stanza alla tua destra

### La stanza degli artefatti

All’interno della prima stanza potresti incontrare un ***boss*** che dovrai affrontare.

Sarai abbastanza veloce e coraggioso?

### Stanza degli indovinelli

Nella seconda stanza potresti incontrare degli indovinelli; presta attenzione però, **se risponderai in modo errato** non potrai proseguire e dovrai ricominciare da capo!

Mi raccomando: attento a dove cammini, ci sono sempre dei boss che ti stanno aspettando! ⚔️

### Il labirinto

Sarai in grado di raggiungere la terza stanza?

Se sì, ti aspetterà un **fitto labirinto** che ti condurrà direttamente nella ***stanza del cristallo***.

### Stanza del cristallo

Ce l’hai fatta! Bravo!

Ora ti aspetta la sfida più ostica: dovrai combattere contro un tuo **perfetto duplicato**! ⚔️

Sarai in grado di sconfiggerlo? Scopriamolo!

Alcuni boss potrebbero rilasciare degli ***item:*** puoi decidere se raccoglierli e portarli con te.

Attento a non superare il limite di 5kg!

Bene, ora che è tutto chiaro, **possiamo cominciare.**

**Buona fortuna** 🍀, confidiamo in te per riportare in salvo il cristallo perduto 💠! 

> **Ricorda**: finché sarai in vita, potrai salvare i progressi fatti fino ad ora in qualsiasi momento.
Se vuoi salvare i dati, assicurati di essere connesso ad internet.

---

## Principali package utilizzati nel gioco

Al di fuori dei package (*java.io.**, *java.util.Random*, …) più utilizzati e conosciuti nel linguaggio Java, è stato utilizzato l’**sdk AWS.**

L’utilizzo di tale *sdk* semplifica le chiamate ai servizi AWS tramite *API* per il salvataggio online sul ***bucket*** S3 dei progressi effettuati dal giocatore.


> ⚠️ Per salvare i tuoi progressi, è necessario settare le variabili ***AWS_ACCESS_KEY_ID*** e ***AWS_SECRET_ACCESS_KEY*** del proprio bucket AWS.
