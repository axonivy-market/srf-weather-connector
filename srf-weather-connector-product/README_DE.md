# srf-Wetter-Anschluss

Mit #Axon Efeus [SRF](https://developer.srgssr.ch/api-catalog/srf-weather)
verwitter #Daten API, du kannst Wetterberichte für #Schweiz integrieren hinein
#eure Arbeitsgang #Daten.

Der Anschluss:

* Versieht #verschiedene Wetter #Daten
* Veranschaulich #der Wetter #Daten da sieben Tag Prognose
* Unterstützt du mit eine Demo Ausführung zu heruntersetzen eure Integration
  Anstrengung.

## Demo

Installier das SRF Wetter Anschluss und rufen ihm da einen Ersatz-verarbeite.
Pass an demzufolge die Vermessung. ![Apparat Anschluss da
subprocess](images/demo1.png)

Für testen Zwecke, #ein spotten API können sein benutzt in der Demo statt das
offizielles SRF API. Für diesen Zweck, das variables "Url" kann sein kommentiert
aus unter 'Variablen.SrfWeatherConnectors. Hier kannst du vermeiden die
Notwendigkeit zu generieren eine Zugang Automatenmünze via die SRF Entwickler
Website zuerst. In dies spottet Verfahren, nur der REIßVERSCHLUSS Code "6300"
muss sein betreten in das "Reißverschluss" Feld.

In der Demo, du kannst jetzt präzisieren #jeder den Namen von dem Drehort oder
seinen REIßVERSCHLUSS Code und benutzen den "Bekommen Wetter" Knopf zu ausgeben
das Wetter für das nächstes sieben Tage an diesem Drehort. ![Betritt #Daten
Namen oder Reißverschluss](images/demo2.png)

Ob der Drehort ist gültig, die #entsprechend Ausgabe #aussehen dies:
![Endgültige Ausgabe](images/demo3.png)

## Einrichtung

Die Nutzung von die SRF Wetter API ist nicht gratis. Für Entwicklung Zwecke sind
dort eine freie Version mit minimal API Anrufe. Zu benutzen den Anschluss, du
müssen ein passendes auswählen API Päckchen via das [SRF API Entwickler
Website](https://developer.srgssr.ch/api-catalog/srf-weather) und generieren
eine "Inhaber Automatenmünze". Dies ist beschrieben in den folgenden Weisungen:
[SRF
Weisung](https://developer.srgssr.ch/getting-started/easy-description-get-accesstoken)

Nach eine Inhaber Automatenmünze ist verfügbar, du kannst lagern es in eurem
Projekt in den Variablen.yaml Da das variables
"srfWeatherConnector.Automatenmünze" (da du kannst in der Demo sehen).
1. Melde an weiter ein Konto
   [Entwickler.srgssr.ch/](https://developer.srgssr.ch/).
2. Einmal #loggen herein, Klick #App auf dem #oberste #Rechter und zufügen ein
   neues #App mit das "+ ZUFÜGEN #APP" Knopf.
3. Nach dem #App ist geschafft, du willst einen Konsumenten Schlüssel und
   Konsumenten Geheimnis empfangen mit #welche du kannst die Inhaber
   Automatenmünze generieren.
4. Lager das API-Schlüssel/Automatenmünze in euren Variablen.yaml Unter
   `Variablen.SrfWeatherConnector.Automatenmünze`

```
@variables.yaml@
```
