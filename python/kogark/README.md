Øving 3
=======
Av John R. Mienna, MTDT


Oppgave a og b)
-------------------

AND:

	[0.3, 0.5]
	[0.2, 0.4]
	[0.1, 0.3]
	[0.1, 0.2]
	[0.1, 0.1]

OR:

	[-0.5, 0.1]
	[-0.3, 0.3]
	[-0.1, 0.4]
	[0.0, 0.4]
	[0.1, 0.4]
	[0.2, 0.4]

Nei, vektene blir ikke de samme når man endrer startverdiene på vektene og terskelen.

Oppgave c)
-------------

Jeg benyttet LinearLayer både på input- og output-laget.

### Hva er det laveste antall hidden-noder som gir et godt resultat?

Med på 10 kjøringer og 8 hidden-noder fikk jeg følgende sum av error^2:

	- 2.21807341552
	- 4.30909323073
	- 3.02683227037
	- 4.53643175993
	- 2.85340692995
	- 4.80989652803
	- 2.17919323223
	- 3.48630961902
	- 5.06618106949
	- 2.19489337137

Gjennomsnitt: 34,6803114266 / 10 = 3,4680311427

Tilsvarende for 5 hidden-noder:

	- 3.61467347092
	- 3.80097479427
	- 4.77310194509
	- 2.47791067963
	- 6.88490012213
	- 3.31243582443
	- 4.74548818824
	- 2.64779238996
	- 3.38568798011
	- 5.06157088502

Gjennomsnitt: 40,7045362798 / 10 = 4,070453628

4 hidden-noder:

	- 8.85373363916
	- 11.0353906479
	- 4.5757261818
	- 2.21354975867
	- 3.03656733529
	- 3.04348511523
	- 3.63195508289
	- 3.58341908953
	- 7.10259533518
	- 8.05115520256

Gjennomsnitt: 55,1275773882 / 10 = 5,5127577388

3 hidden-noder:

	- 4.80650802677
	- 3.63018444411
	- 15.0908002614
	- 85.4322769639
	- 4.26294864854
	- 10.5645170896
	- 3.95065205277
	- 113.157498111
	- 4.67287817584
	- 6.41620891726

Gjennomsnitt: 251,9844726912 / 10 = 25,198447269

Ser alså ut til at 4 hidden-noder er det laveste man kan gå før resultatet blir dårlig

### Det nevrale nettet og hidden-laget?

"Hva er det det nevrale nettet har gjenskapt gjennom hidden-laget for å produsere et bra resultat med dette minste antallet noder i hidden-laget?"

### Ny input-data.

"Aktiver nettverket med tall du ikke har trent med, som desimaltall, negative tall og tall over 8. Kommenter på hvor godt nettverket håndterer disse"

Når jeg endret på dataen til

	ds.addSample((1.2,), (2.2,))
	ds.addSample((2.1,), (10.2,))
	ds.addSample((3.5,), (-1.2,))
	ds.addSample((4.1,), (-12.2,))
	ds.addSample((5.1,), (2.9,))
	ds.addSample((-6.9,), (6.2,))
	ds.addSample((-7.2,), (1.0,))
	ds.addSample((8.1,), (0.1,))

Håndterte nettverket det veldig dårlig og summen av error^2 lå godt over 100.
