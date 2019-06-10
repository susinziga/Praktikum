# Knjigomat
Projekt Knjigomat omogoča izposojo knjig na knjigomatih, ki se nahajajo na večih lokacijah po mestu Maribor. Uporabnikom je omogočeno naročevanje knjig na knjigomat, ki jim je najbližji ali kar direktna izposoja knjig, ki so na voljo v knjigomatu - okoli 50 knjig. Za prevzem ima vsak uporabnik svojo QR kodo na telefonu in vsak knjigomat ima bralnik QR kod. Knjige (tudi iz knjižnice) lahko uporabnik vrne na knjigomat. Na vsaki napravi beležimo katere knjige so najbolj izposojene in s tem žanrom nato avtomat napolnimo.

## Kaj omogoča
```
Knjižnica omogoča:
- dodajanje, iskanje, brisanje, urejanje, naročanje in pregled vseh knjig;
- dodajanje, brisanje, upravljanje uporabnikov in njihovih pravic;
- pošiljanje potrditev naročil uporabniku z njegovo QR za dostop do naročila na knjigomatu.
```
```
Knjigomat omogoča:
- pregled vseh knjig na voljo v knjigomatu;
- vračanje knjig v knjigomat (s QR kodo - vsaka knjiga ima unikatno);
- prevzem naročenih knjig na izbranem knjigomatu;
- prijava uporabnika z uporabo QR kode;
```
## Tehnološki sklad

### Grajeno z:
* [PHP](https://www.php.net/docs.php) - UI knjigomat, UI uporabnik
* [Bootstrap](https://getbootstrap.com/docs/4.3/getting-started/introduction/) - UI knjigomata na mobilni napravi, tablićnem računalniku in vključen pri PHP
* [Jsp](https://docs.oracle.com/javaee/5/tutorial/doc/bnajo.html) - vnos, brisanje, urejanje knjig in uporabnikov v knjižnici
* [Java](https://docs.oracle.com/javase/7/docs/api/) - Hranjenje podatkov v ozadju
* [MySQL](https://dev.mysql.com/doc/) - Podatkovna baza
* [Wildfly](https://docs.wildfly.org/16/) - Strežnik za JPA - hranjenje podatkov in slik  <i>(uporabljeno pri razvoju!)</i>
* [WAMP](https://docs.bitnami.com/installer/infrastructure/wamp/) - Strežnik za UI knjigomata   <i>(uporabljeno pri razvoju!)</i>

### Arhitektura:
![Arhitektura žal ni na voljo](https://i.imgur.com/qkEnle8.png)

## Priprava razvojnega okolja knjižnice
1. Razvijalno okolje Eclipse ali IntelliJ s strežnikom Wildfly(JBOSS)
2. V razvojnem okolju Datoteka > Uvozi > GIT > URI "https://github.com/zigalepi/Praktikum.git" > Kloniraj
3. V Eclipsu uporaba git bash terminala:  
a) Nastavitve > Orodja > Terminal   
b) Shell Path, navedemo pot do bash.exe datoteke, ki jo najdemo v */Git/bin/bash.exe
4.Uporabimo lahko tudi "Ekipa"
a) Desni klik na projekt > Ekipa > Git
5. Konfiguriranje strežnika Wildfly:
a) Prenos paketa strežnika Wildfly  
b) V razvijalnem okolju odpremo zavihek Strežniki > Novo > Izberemo prenešeno datoteko in poimenujemo strežnik > Dodaj projekt > Zaženi 
c) Uporabnika dodamo v AddUser.bat v datoteki strežnika Wildfly   
6. Lokalna podatkovna baza v MySQL, v Persistence.xml poimenovana "PraktikumDS"
7. Aplikacija je dostopna na Localhost:8080
8. Za posodobitev sprememb ponovno zaženemo kot aplikacijo na strežniku

## Priprava okolja knjigomat
1. Razvijalno okolje ATOM z XAMPP / WAMP strežniško rešitvijo
2. V razavijalnem okolju New project > Desni klik na ustvarjen projekt > GIT > URI "https://github.com/GasperReher/Klient_php.git" > Clone / Pull
3. Več dostopno na: https://github.com/GasperReher/Klient_php

## Uporaba
