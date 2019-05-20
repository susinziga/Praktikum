# Priprava okolja
1. Razvijalno okolje Eclipse ali IntelliJ s strežnikom Wildfly(JBOSS)
2. V razvijalnem okolju File > Import > GIT > URI "https://github.com/zigalepi/Praktikum" > Clone
3. V Eclipsu uporaba git bash terminala:  
a) Setting > Tools > Terminal   
b) Shell Path, navedemo pot do bash.exe datoteke, ki jo najdemo v */Git/bin/bash.exe
4. Konfiguriranje strežnika Wildfly:
a) Prenos paketa strežnika Wildfly  
b) V razvijalnem okolju odpremo zavihek Servers > New > Izberemo prenešeno datoteko in poimenujemo strežnik > Add - dodamo projekt > Run  
c) Uporabnika dodamo v AddUser.bat v datoteki strežnika Wildfly   
5. Lokalna podatkovna baza v MySQL, v Persistence poimenovana "PraktikumDS"
6. Aplikacija je dostopna na Localhost:8080
7. Za posodobitev sprememb ponovno zaženemo kot aplikacijo na strežniku

# Funkcionalnosti
- Pregled knjig v bazi knjižnice,
- Rezervacija knjig,
- Izposoja knjig na knjigomatu,
- Pregled knjig v knjigomatu,
- Vračanje knjig v knjigomat,
- Naročanje knjig za prevzem na knjigomatu
