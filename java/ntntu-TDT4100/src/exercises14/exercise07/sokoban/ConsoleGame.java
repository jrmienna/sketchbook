package exercises14.exercise07.sokoban;

public interface ConsoleGame {

	/* metoden fjerner all informasjon om et evt. spill som er igang
	 * og initialiserer det med informasjon fra String-argumentet. 
	 * Du avgjør selv hvordan argumentet tolkes, men bør støtte filnavn.
	 * Denne må kalles før run()-metoden.
	 */
	public void init(String arg);
	/*
	 * metoden starter et nytt spill, i den forstand at det er klar 
	 * til å ta imot kommandoer fra brukeren. 
	 * Kommandoene vil komme gjennom kall til doLine-metoden.
	 * All output fra programmet til brukeren skal formidles med kall
	 * til ConsoleOutput-argumentet sine message- og error-metoder,
	 * så dette objektet må lagres unna til siden.
	 */
	public void run(ConsoleOutput output);
	/*
	 * metoden utfører en kommandolinje, som om den ble skrevet inn av brukeren.
	 * Returnverdien angir om spillet er ferdig og hva resultatet ble. 
	 * Dersom returverdien er null, så er spillet fortsatt igang, 
	 * dersom verdien er positiv så vant spilleren (evt. spiller 1), 
	 * dersom verdien er negativ så tapte spilleren (evt. spiller 1) 
	 * og dersom den er 0 så er resultatet udefinert evt. uavgjort. 
	 * I løpet av kallet til doLine, så skal informasjon om (den nye) 
	 * tilstanden til spillet formidles til ConsoleOutput-objektet sin 
	 * message-metode.
	 */
	public Integer doLine(String cmd);
}
