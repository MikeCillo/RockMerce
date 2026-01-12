Aggiungi i tuoi file di test JMeter (*.jmx) in questa cartella.

Come usare:
- Inserisci i .jmx qui e committa.
- Esegui `mvn verify` per eseguire i test JMeter automaticamente (il plugin scaricherà JMeter se necessario).
- I risultati saranno in `target/jmeter-results` e il report HTML (se configurato) in `target/site/jmeter`.

Nota: Il plugin è configurato per fallire il build se uno o più test JMeter falliscono (`failBuildOnError=true`).

Consigli per CI: esegui i test in modalità non-GUI e limita il numero di thread o usa un ambiente dedicato per test di carico pesanti.

Esempio incluso:
- `sample-test.jmx` — test semplice che esegue una GET su `${TARGET_PROTOCOL}://${TARGET_HOST}:${TARGET_PORT}${TARGET_PATH}`.

Eseguire l'esempio:
- Per usare i valori di default (localhost:8080):

```bash
mvn verify -DskipTests=false
```

- Per eseguire contro un host diverso, passa le proprietà Maven che sovrascrivono le variabili JMeter:

```bash
mvn verify -Djmeter.jvmargs="-DTARGET_HOST=example.com -DTARGET_PORT=80 -DTARGET_PATH=/" -DskipTests=false
```

Personalizzare il file JMX:
- Apri `sample-test.jmx` con JMeter GUI o un editor di testo e modifica il numero di thread in `Thread Group` o il path dell'HTTP Request Defaults.
