# TE18Pad

### Inledning
Syftet med projektet var att lära mig om GUI och filhantering i Java. Detta gjordes genom en simpel Notepad-klon. Jag bestämde mig för att utöka projektet för att även lära mig om Java Crypto och kryptering av text.

### Bakgrund
Projektet är byggt på en Maven-grund för extra hjälp med dependencies och använder 
Lombok för hantering av getters och setters. För GUI används IntelliJ's FormBuilder 
samt Java Swing. För filhantering används generellt File, BufferedWriter och 
BufferedReader, men detta kan förenklas med Apache Commons-IO som automatiskt 
skapar writers och readers.

För att planera projektet ritades en skiss av interfacen som sedan 
projektet designades efter.

### Positiva erfarenheter
Apaches Commons-IO gjorde filhanteringen mycket lättare, även om jag vet 
hur man gör för att läsa och skriva filer i Java är det onödigt att 
återuppfinna hjulet när det finns bättre alternativ.

### Negativa erfarenheter
Då jag aldrig använt Java Crypto var det svårt att verkligen få till systemet, 
specifikt då filhanteringen vägrade fungera. Det slutade med att jag insåg att 
min konvertering från byte[] till String förstörde del av innehållet och inte 
tillät programmet at dekryptera informationen. Därför valde jag att spara filer 
som bytearrays snarare än strängar.

### Sammanfattning
Projektet har varit en intressant introduktion till kryptografi med Java, jag 
har lärt mig väldigt mycket om ämnet och utökat mina kunskaper. Jag valde att 
använda bibliotek till de delar av projektet jag redan visste hur man gjorde.

En förbättring skulle vara att kunna faktiskt se vilken text som skrivs, 
snarare än att vara begränsad till den krypterade informationen.