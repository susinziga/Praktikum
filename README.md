# Knjigomat
Projekt Knjigomat omogoča izposojo knjig na knjigomatih, ki se nahajajo na večih lokacijah po mestu Maribor. Uporabnikom je omogočeno naročevanje knjig na knjigomat, ki jim je najbližji ali kar direktna izposoja knjig, ki so na voljo v knjigomatu - okoli 50 knjig. Za prevzem ima vsak uporabnik svojo QR kodo na telefonu in vsak knjigomat ima bralnik QR kod. Knjige (tudi iz knjižnice) lahko uporabnik vrne na knjigomat. Na vsaki napravi beležimo katere knjige so najbolj izposojene in s tem žanrom nato avtomat napolnimo.

## Kazalo
- [Kaj omogoča](#kaj-omogoča)
- [Tehnološki sklad](#tehnološki-sklad)
- [Nastavitev virtualnega stroja](#nastavitev-virtualnega-stroja)
- [Priprava razvojnega okolja knjižnice](#priprava-razvojnega-okolja-knjižnice)
- [Priprava okolja knjigomat](#priprava-okolja-knjigomat)
- [Avtorji](#avtorji)
- [Napake in težave](#napake-in-težave)
- [Licence](#licence)

## Kaj omogoča
**Knjižnica omogoča:**
```
- dodajanje, iskanje, brisanje, urejanje, naročanje in pregled vseh knjig;
- dodajanje, brisanje, upravljanje uporabnikov in spreminjanje njihovih pravic;
- pošiljanje potrditev naročil uporabniku na mail, ki vključuje QR za dostop do naročila na knjigomatu.
```
**Knjigomat omogoča:**
```
- pregled vseh knjig na voljo v knjigomatu;
- vračanje knjig v knjigomat s QR kodo, ki je unikatna za vsako knjigo (vrnemo lahko tudi knjige sposojene v knjižnici);
- prevzem naročenih knjig na izbranem knjigomatu;
- prijava uporabnika z uporabo QR kode;
```
## Tehnološki sklad

### Grajeno z:
* [PHP](https://www.php.net/docs.php) - UI knjigomat, UI uporabnik
* [Bootstrap](https://getbootstrap.com/docs/4.3/getting-started/introduction/) - UI knjigomata na mobilni napravi, tablićnem računalniku in vključen pri PHP
* [JSF](https://www.oracle.com/technetwork/java/javaee/documentation/index-137726.html) - vnos, brisanje, urejanje knjig in uporabnikov v knjižnici
* [JQuery](https://api.jquery.com/) - Vključeno pri JSF
* [HTML](https://devdocs.io/html/) - Vključeno pri knjigomatu
* [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference) - vključeno pri knjigomatu
* [CSS](https://developer.mozilla.org/en-US/docs/Web/CSS/Reference) - Vključeno pri knjigomatu
* [Java](https://docs.oracle.com/javase/7/docs/api/) - Hranjenje podatkov v ozadju
* [MySQL](https://dev.mysql.com/doc/) - Podatkovna baza
* [Wildfly](https://docs.wildfly.org/16/) - Strežnik za JPA - hranjenje podatkov in slik  <i>(uporabljeno pri razvoju!)</i>
* [WAMP](https://docs.bitnami.com/installer/infrastructure/wamp/) - Strežnik za UI knjigomata   <i>(uporabljeno pri razvoju!)</i>

### Arhitektura:
![Arhitektura žal ni na voljo](https://i.imgur.com/qkEnle8.png)

## Nastavitev Virtualnega stroja
Prenos paketa virtualnega stroja dostopno na: https://drive.google.com/file/d/12ucvdLA_tGxi0pue3N29grWOugGdah4G/view?usp=sharing

___

> Nastavitev strežnika Wildfly:
```
https://developer.jboss.org/wiki/BasicTutorialToSetUpWildFly
```

___

> Dodajanje uporabnika v Wildfly:
```
https://www.ibm.com/support/knowledgecenter/en/SSMKFH/com.ibm.apmaas.doc/install/jboss_config_agent_prereq_add_management_user.htm
```
ali
```
1. Poiščemo Bin direktorij v datoteki strežnika WIldfly
2. Zaženemo skripto za dodajanje uporabnika: Linux./add-user.sh
                                             Windowsadd-user.bat
3. Dodamo uporabnika z uporabniškim imenom in geslom z željenimi pravicami
```

___

> Deployment projekta:
```
https://docs.jboss.org/author/display/WFLY10/Application+deployment
```
ali
```
1. Nastavljen in zagnan strežnik WIldfly
2. V privzetem brskalniku odpremo lokacijo Localhost:8080 (priveta lokacija za Wildfly ali tisto na katero smo spremenili)
3. Odpremo administrativno konzolo
4. Pod Deployments projekt
5. Zaženemo "bin/standalone.bat"
6. Projekt je dostopen na: ip/projekt/faces (ip preverimo s cmd > ipconfig)
```

![Pogled IP](https://i.imgur.com/rlND3Dh.png)
___

### Dostop:
> VM
```
Geslo: Passw0rd!
```
> Wildfly
```
Uporabnik: user
Geslo: user
```

___

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
2. V razavijalnem okolju New project > Desni klik na ustvarjen projekt > GIT > URI "https://github.com/GasperReher/Klient_php.git" > Kloniraj / Pull
3. Knjigomat dostopen na: https://github.com/GasperReher/Klient_php in https://github.com/zigalepi/knjigomat_masina

## Napake in težave
Zasledite napako ali težavo v pri delovanju? [Prijavite](https://github.com/zigalepi/Praktikum/issues) napako ali težavo na našem GitHub projektu.

## Avtorji
| <a href="https://www.instagram.com/zigasusin/" target="_blank">**Žiga Sušin**</a> | <a href="https://www.instagram.com/gasperreher98/?hl=en" target="_blank">**Gašper Reher**</a> | <a href="https://www.instagram.com/toniharamija/" target="_blank">**Toni Haramija I.**</a> |<a href="https://www.instagram.com/bard.grujic/" target="_blank">**Bard Grujič**</a> |
| :---: |:---:| :---:|:---:|
| [![Žiga Sušin](https://avatars0.githubusercontent.com/u/39264729?s=200&v=3)](https://github.com/zigalepi)    | [![Gašper Reher](https://avatars0.githubusercontent.com/u/33724905?s=200&v=3)](https://github.com/GasperReher) | [![Toni Haramija I.](https://avatars1.githubusercontent.com/u/39265596?s=200&u=846fdedda24fc881866a1a98402a6e33c06c61f3&v=3)](https://github.com/Tonskii)  |[![Bard Grujič](https://avatars3.githubusercontent.com/u/33715866?s=200&v=3)](https://github.com/GrujicBard)  |
| <a href="https://github.com/zigalepi" target="_blank">`https://github.com/zigalepi`</a> | <a href="https://github.com/GasperReher" target="_blank">`https://github.com/GasperReher`</a> | <a href="https://github.com/Tonskii" target="_blank">`https://github.com/Tonskii`</a> |<a href="https://github.com/GrujicBard" target="_blank">`https://github.com/GrujicBard`</a> |
| <p> Šefe </p> | <p> Glavni razvijalec </p> | <p> RN - PR - razvijalec <p> | <p> Bard </p> |

## Licence
[MIT licenca](https://choosealicense.com/licenses/mit/)
