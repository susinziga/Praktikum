# Priprava okolja knjižnica
1. Razvijalno okolje Eclipse ali IntelliJ s strežnikom Wildfly(JBOSS)
2. V razvijalnem okolju File > Import > GIT > URI "https://github.com/zigalepi/Praktikum.git" > Clone
3. V Eclipsu uporaba git bash terminala:  
a) Setting > Tools > Terminal   
b) Shell Path, navedemo pot do bash.exe datoteke, ki jo najdemo v */Git/bin/bash.exe
4.Uporabimo lahko tudi "team"
a) Desni klik na projekt > Team > Git
5. Konfiguriranje strežnika Wildfly:
a) Prenos paketa strežnika Wildfly  
b) V razvijalnem okolju odpremo zavihek Servers > New > Izberemo prenešeno datoteko in poimenujemo strežnik > Add - dodamo projekt > Run 
c) Uporabnika dodamo v AddUser.bat v datoteki strežnika Wildfly   
6. Lokalna podatkovna baza v MySQL, v Persistence poimenovana "PraktikumDS"
7. Aplikacija je dostopna na Localhost:8080
8. Za posodobitev sprememb ponovno zaženemo kot aplikacijo na strežniku

# Priprava okolja knjigomat
1. Razvijalno okolje ATOM z XAMPP / WAMP strežniško rešitvijo
2. V razavijalnem okolju New project > Desni klik na ustvarjen projekt > GIT > URI "https://github.com/GasperReher/Klient_php.git" > Clone / Pull
3. Več dostopno na: https://github.com/GasperReher/Klient_php

# Kloniranje
Kloniranje brez razvojnega okolja z GitBash preko povezav:
Repozitorij knjižnice z "git clone https://github.com/zigalepi/Praktikum.git"

Repozitorija knjigomata z "git clone https://github.com/GasperReher/Klient_php.git"

# Kaj omogoča
Knjižnica omogoča:
- dodajanje, iskanje, brisanje, urejanje, naročanje in pregled vseh knjig;
- dodajanje, brisanje, upravljanje uporabnikov in njihovih pravic;
- pošiljanje potrditev naročil uporabniku z njegovo QR za dostop do naročila na knjigomatu.

Knjigomat omogoča:
- pregled vseh knjig na voljo v knjigomatu;
- vračanje knjig v knjigomat (s QR kodo - vsaka knjiga ima unikatno);
- prevzem naročenih knjig na izbranem knjigomatu;
- prijava uporabnika z uporabo QR kode;

# Uporaba
