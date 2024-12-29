# Etapa 1 - J. POO Morgan Chase & Co.
Cucu Viorel-Cosmin 324CA

## Feedback initial

Primul lucru pe care vreau sa il mentionez este un feedback legat de tema. Nu mi-a placut absolut deloc faptul ca am fost fortat sa implementez aceasta tema bazandu-ma exclusiv pe referinte. Acest lucru m-a limitat pe parcursul implementarii, deoarece nu stiam cum arata fiecare comanda in caz de eroare, unde se afiseaza eroarea respectiva, cand o tranzactie trebuie adaugata in istoric sau cum arata fiecare tranzactie (ce campuri contine). Toate aceste informatii lipsa te obliga sa urmaresti referintele si sa finalizezi tema asa cum a fost gandita de voi, iar abia apoi sa revin si sa refac o parte din cod!

Cerintele de la tema trecuta mi s-au parut net superioare acesteia. Eu consider ca o tema ar trebui sa fie bazata pe cerinte clare, astfel incat sa iti poti evalua capacitatea de a intelege si implementa o anumita functionalitate.

In concluzie, cerinta a fost extraordinar de nesugestiva! Pe viitor, ar fi bine sa specificati cum arata o eroare (daca se afiseaza in tranzactii sau pe ecran), cum arata tranzactia respectiva (ce campuri are, astfel incat sa o pot afisa corect in format JSON).

---

## Arhitectura programului

### Entitati

- **bankAccounts**  
  Contine o clasa abstracta `Account` din care deriva doua clase: `ClassicAccount` si `SavingAccount`. Am facut acest lucru pentru a suprascrie anumite metode caracteristice unui anumit tip de cont, cum ar fi `AddInterest`, `ChangeInterest` si `SpendingReport`. Mi s-a parut o tehnica proasta sa folosesc `if` pentru a verifica tipul contului; astfel, suprascriu metoda corespunzatoare si o execut fara verificari suplimentare. (Daca pe viitor se extind tipurile de conturi, aceasta abordare este foarte buna.)

- **card**  
  Contine o clasa abstracta `Card` din care deriva `ClassicCard` si `OneTimePayCard`. Am realizat acest design pentru a suprascrie metoda `pay()` pentru fiecare tip de card.

- **transaction**  
  Reprezinta un pachet ce contine toate corpurile tranzactiilor prezente in program.

- **User**  
  Clasa reprezinta un utilizator. Am folosit un `LinkedHashMap` pentru a tine conturile dupa IBAN si pentru a pastra ordinea inserarii.

- **ExchangeRates**  
  Clasa in care tin intr-un `Map` fiecare pereche de valuta cu valoarea schimbului. Pentru fiecare valuta, in alt `Map`, tin valutele in care se poate face schimbul. Am implementat acest lucru deoarece mi-a usurat DFS-ul, iar toate cautarile sunt in O(1) folosind dictionare.

- **Bank**  
  Este o clasa Singleton, deoarece exista o singura banca pe parcursul programului si am considerat util acest design. Contine `Map`-uri pentru useri, carduri, conturi, istoricul tranzactiilor fiecarui user si istoricul cardurilor sterse. Implementarea aceasta este foarte eficienta, deoarece fiecare cautare se face in O(1). Totodata, banca contine toate schimburile valutare disponibile.

---

### Servicii

- **Initializarea datelor**  
  Serviciile pentru initializarea datelor implementate folosesc **Visitor**, deoarece nu am vrut sa poluez clasa `Bank` cu metode precum `ExchangeRatesInitialize` si `UserInitialize`.

- **BankServices**  
  Serviciul specific bancii. Contine metode ce ma ajuta sa execut comenzile astfel incat fiecare clasa ce implementeaza o comanda sa aiba cod cat mai modular si scurt.

- **CurrencyExchangeService**  
  Serviciul pentru schimb valutar. Realizeaza un DFS pentru a cauta o pereche de valute si o introduce in `Map`, astfel incat o cerere viitoare sa fie gasita in O(1). Adaug si inversul fiecarui schimb valutar, pentru a evita recalcularea.

- **Payment Services**  
  Se gaseste in pachetul `payment` si contine clasa `PaymentMethod`, ce reprezinta un **Visitor** pentru banca. Aceasta aplica o strategie de plata:  
    - **BankTransferStrategy**  
    - **CardPaymentStrategy**  
    - **SplitPaymentStrategy**

  Am folosit acest design deoarece fiecare plata reprezinta in sine o strategie, iar adaugarea unor noi tipuri de plati este extrem de usoara, fara a modifica codul existent.

---

### Utils

- **CommandManager**  
  Reprezinta un **Factory** pentru fiecare comanda data la input. Comenzile sunt definite in pachetul `command`. Am combinat **Factory Design** cu **Command Pattern** pentru a crea si a executa comenzile, facilitand astfel extensibilitatea (se poate adauga foarte usor o noua comanda).

- **TransactionManager**  
  Este un factory ce creeaza tranzactii primind datele necesare prin clasa `DatesForTransaction`, ce foloseste **Builder Pattern**. Apoi adaug tranzactia in istoricul corespunzator (istoricul userului, al contului, sau ambele).  
  Stiu ca puteam folosi direct un builder pentru a construi tranzactia si a o adauga, insa abordarea actuala pastreaza codul mai simplu, atat vizual cat si conceptual.

---

### Comenzi

In acest pachet sunt toate comenzile ce implementeaza interfata **Command** pentru a executa comanda respectiva.

---

## Concluzie

Tema mi s-a parut foarte buna, cu exceptia cerintei. Eu consider ca am facut o treaba buna si mi-am dat interesul. Daca sunt lucruri ce pot fi imbunatatite, inclusiv dincolo de POO, chiar vreau sa le aud!
