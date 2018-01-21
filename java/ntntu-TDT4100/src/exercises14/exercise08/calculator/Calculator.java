package exercises14.exercise08.calculator;

/*At en klasse er abstrakt betyr at du ikke kan instansisere den. 
 * Med andre ord: du kan ikke si new Calculator(). En klasse gjøres 
 * abstrakt så den kan inneholde abstrakte metoder: metoder som ikke 
 * er definert. Dette gjøres ganske enkelt ved å ikke ha med 
 * klammeparentesene med definisjonen av metoden etter deklarasjonen, 
 * men heller bare et semikolon. For eksempel:
 * 		abstract class MyAbstractClass {
 * 			abstract void myAbstractMethod(int parameter);
 * 		}
 */

/*
 * På den måten sier du at objekter av MyAbstractClass har en 
 * myAbstractMethod, uten å ha bestemt hva den egentlig gjør. 
 * takeInputNumber, takeInputOperator, hasOutput og getOutput 
 * skal altså ikke defineres i superklassen Calculator.
 */

public abstract class Calculator {

	protected void setLeftOperand(double operand) {
		// skal sette venstre operand
	}
	protected void setRightOperand(double operand) {
		// skal sette høyre operand
	}
	protected void setOperator(char operator) {
		// skal sette operatoren til en av '+', '-', '*', '/'		
	}
	protected double getResult() {
		//skal returnere resultatet av utregningen
		return 0;
	}
	// brukes av underklassen til å ta inn et tall skrevet inn
	abstract public void takeInputNumber(double number);
	
	// brukes av underklassen til å ta inn en operator skrevet inn
	abstract public void takeInputOperator(char operator);
	
	// returnerer om det er på tide å lese av et svar
	abstract public boolean hasOutput();
	
	//returenerer svartet
	abstract public double getOutput();
	
	
}
