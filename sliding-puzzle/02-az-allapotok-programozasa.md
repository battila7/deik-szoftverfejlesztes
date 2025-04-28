# Az állapotok programozása

Az állapottér repezrentációt nem véletlenül hívják úgy, ahogy, hiszen minden az állapotok körül forog. Ennek megfelelően mi is az állapotok kódba öntésével fogjuk kezdeni a feladat megoldását.

## Az állapot, mint fogalom megjelenítése

Ennek első lépése, hogy felismerjük: az állapot egy fontos szakterületi fogalom, melyet meg kell jelenítenünk a kódban.

Azaz, érdemes készítenünk egy `State` osztályt:

```java
class State {
    
}
```

Mi kerüljön ebbe  az osztályba? Vegyük elő a térképünket, az előzőleg készített reprezentációt. Ebben az áll, hogy egy állapot négy értékből áll:
- a blokk pozíciója,
- a kék vödör pozíciója,
- a piros vödör pozíciója,
- a fekete vödör pozíciója.

Az absztrakt állapottér reprezentációban ez nagyon egyszerű, ugyanakkor Java kódban e négy érték tárolására számos megoldás létezik. Vizsgáljunk meg ezek közül néhányat.

## A bábuk pozíciójának leírása

A következőkben áttekintünk néhány válogatott megoldást a bábuk pozíciójának tárolására. Fontos kiemelni, hogy egyik sem az egyetlen helyes megoldás, hanem mindegyik rendelkezik előnyökkel és hátrányokkal. Rajtunk áll, hogy ezeket figyelembe véve mely megoldást ítéljük a megfelelőnek a jelen problémához.

A választás nehézségéhez (és a válasz melletti kitartáshoz - *commitment*) kapcsolódó érdekes cikk és beszélgetés:
- https://www.reddit.com/r/programming/comments/1jyxu3p/engineers_who_wont_commit/

### A pozíciók tárolása listában (tömbben)

Az egyes bábuk pozícióját egy listában (esetleg tömbben) tároljuk, ahol az első elem a blokk pozíciója, a második elem a kék vödör pozíciója és így tovább.

```java
record Position(
        int x,
        int y
) {}

class State {
    private static final int BLOCK_INDEX = 0;
    private static final int BLUE_SHOE_INDEX = 1;
    private static final int RED_SHOE_INDEX = 2;
    private static final int BLACK_SHOE_INDEX = 3;
    
    private final List<Position> positions;
}
```

Előnyök:
- Nagyon egyszerű végigiterálni a pozíciókon.

Hátrányok:
- Az egyes bábuk helyzetéhez az indexeken keresztül férünk hozzá, ami hamar fáradságossá válik.
- Könnyű véletlenül elrontani a sorrendet, vagy több (esetleg kevesebb) elemet felvenni a listába.

Mikor lehet jó választás:
- Sok bábu és a bábukhoz képest nagy tábla esetén.

### A pozíciók tárolása Mapben

Az egyes bábuk pozícióját egy Mapben tároljuk, ahol a kulcsok a bábuk típusai, míg az értékek a pozíciók. 

```java
enum PieceType {
    BLOCK,
    BLUE_SHOE,
    RED_SHOE,
    BLACK_SHOE
}

record Position(
        int x,
        int y
) {}

class State {
    private final Map<PieceType, Position> positions;
}
```

Előnyök:
- Nagyon egyszerű végigiterálni a pozíciókon.
- A bábuk pozíciójához a típusukon keresztül férünk hozzá, nem indexeken keresztül, így nem tudjuk elrontani.

Hátrányok:
- Nem tudunk több, azonos típusú bábut tárolni (például sakkban a gyalogok).

Mikor lehet jó választás:
- Közepes számú (mondjuk 5-nél több), csupa különböző bábu és bábukhoz képest nagy játéktér esetén.

### A játéktér tárolása

Ahelyett, hogy külön-külön eltárolnánk a bábukat, mi lenne, ha inkább magát a játékteret tárolnánk el?

```java
enum PieceType {
    BLOCK,
    BLUE_SHOE,
    RED_SHOE,
    BLACK_SHOE
}

class State {
    private final PieceType[][] positions;
}
```

Előnyök:
- Nagyon egyszerű végigiterálni a pozíciókon.
- Könnyedén ellenőrizhetők akár bonyolultabb szomszédsági, elhelyezkedési szabályok is.
  - Gondoljunk például a sakkból a lóugrásra. 

Hátrányok:
- Nagyon könnyű elrontani az indexelést.
- A játéktér méretével növekszik a tárolt adat mennyisége.

Mikor lehet jó választás:
- Kis játéktér és a játéktérhez képest sok bábu esetén.

### A pozíciók tárolása mezőkben

Készítsünk minden bábuhoz egy mezőt a `State` osztályban.

```java
record Position(
        int x,
        int y
) {}

class State {
    private final Position block;
    private final Position redShoe;
    private final Position blueShoe;
    private final Position blackShoe;
}
```

Előnyök:
- Nem tudunk félreindexelni, illetve több/kevesebb bábut eltárolni.
- Rendkívül egyszerű hozzáférni bármely, választott bábu pozíciójához.

Hátrányok:
- Nehézkes végigiterálni a pozíciókon.
- A bábuk számával egyenesen arányosan növekszik a mezők száma.

Mikor lehet jó választás:
- Kevés bábu (mondjuk, legfeljebb öt) esetén.
