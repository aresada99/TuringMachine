package package1;

public class TuringMachine {

	String[] states;
	String startState;
	String acceptState;
	String rejectState;
	String blankSymbol;
	String[] tapeAlph;
	String[] inputAlph;
	String[][] paths;
	String[][] givenStrings;
	
	
	public TuringMachine(String[] states, String startState, String acceptState, String rejectState,
			String blankSymbol, String[] tapeAlph, String[] inputAlph, String[][] paths, String[][] givenStrings) {
		
		this.states = states;
		this.startState = startState;
		this.acceptState = acceptState;
		this.rejectState = rejectState;
		this.blankSymbol = blankSymbol;
		this.tapeAlph = tapeAlph;
		this.inputAlph = inputAlph;
		this.paths = paths;
		this.givenStrings = givenStrings;
	}
	
	
	
	
}
