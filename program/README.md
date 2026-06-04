# Scacchi ♟️

Implementazione di un gioco di scacchi in Java con interfaccia grafica.

> **⚠️ Versione Beta** — Il progetto è ancora in sviluppo. Alcune funzionalità non sono ancora complete o potrebbero non funzionare correttamente.

## Funzionalità presenti

- Scacchiera 8x8 con pezzi posizionati correttamente
- Movimento di tutti i pezzi secondo le regole base degli scacchi
- Interfaccia grafica con JFrame e MouseListener
- Evidenziazione delle mosse disponibili al click su un pezzo
- Gestione dei turni (bianco/nero)
- Cattura dei pezzi avversari

## Funzionalità mancanti o incomplete

- [ ] Scacco e scacco matto
- [ ] Arrocco (corto e lungo)
- [ ] En passant
- [ ] Promozione del pedone
- [ ] Rilevamento di stallo
- [ ] Cronometro / gestione del tempo
- [ ] Storico delle mosse
- [ ] Modalità multiplayer online

## Struttura del progetto

```
src/
├── backend/
│   ├── Model/       → pezzi, scacchiera, logica di gioco
│   ├── Enum/        → direzioni di movimento
│   └── Exception/   → eccezioni personalizzate
└── frontend/        → interfaccia grafica (JFrame, JPanel)
```

## Come avviare

Eseguire il metodo `main` nella classe `frontend.ChessFrame`.
