// Name&Surname: Ares Ada Dora   StudentID:S014479
package package1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class main {

	
	
	public static void main(String[] args) throws IOException {
		
		String fp = new String(getFileName());
		File file = new File(fp);	
		Scanner scan1 = new Scanner(file);
		Scanner scan = new Scanner(file);
		Scanner scan2 = new Scanner(file);
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--Input File--------------------------------------------------------");
		while(scan1.hasNextLine()) {
			System.out.println(scan1.nextLine());
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------\r");
		
		
		int varNumInput = Integer.parseInt(scan.nextLine());
		int varNumTape = Integer.parseInt(scan.nextLine());
		int stateNum = Integer.parseInt(scan.nextLine());

		
		String statesStr = scan.nextLine();
		String[] states = new String[stateNum];
		states = statesStr.split(" ");

		String startState = scan.nextLine();
		String acceptState = scan.nextLine();
		String rejectState = scan.nextLine();
		String blankSymbol = scan.nextLine();

		
		
		String tapeAlphabetStr = scan.nextLine();
		String[] tapeAlphabet = new String[varNumInput + varNumTape];
		tapeAlphabet = tapeAlphabetStr.split(" ");

		
		
		String inputAlphabetStr = scan.nextLine();
		String[] inputAlphabet = new String[varNumInput];
		inputAlphabet = inputAlphabetStr.split(" ");

		
		
		
		ArrayList<String> paths1d = new ArrayList<String>();
		//////
		ArrayList<String> variables = new ArrayList<String>();
		for(int i =0;i<varNumInput;i++) {
			variables.add(inputAlphabet[i]);
			variables.get(i);
		}
		for(int i =0;i<varNumTape;i++) {
			variables.add(tapeAlphabet[i]);
			variables.get(i);
		}
		
		int inputSize = 0;
		for(int i=0;i<10;i++) {scan2.nextLine();}
		String str = "";
		char charA;
		String strA;
		while(scan2.hasNextLine()) {
			str = scan2.nextLine();
			charA = str.charAt(0);
			strA = String.valueOf(charA);

			if(variables.contains(strA)) {
				break;
			}
			inputSize++;

		}
		///////
		for(int i=0;i<inputSize;i++) {
			paths1d.add(scan.nextLine());
		}
		
		String[][] paths2d = new String[inputSize][5];
		for(int i=0;i<inputSize;i++) {
			for(int j=0;j<5;j++) {
				String[] string_parts = paths1d.get(i).split(" ");
				paths2d[i][j] = string_parts[j];			
			}
		}
		
		ArrayList<String> inputsListForOutput = new ArrayList<String>();
		ArrayList<String> inputsList = new ArrayList<String>();
		while(scan.hasNextLine()) {
			inputsList.add(scan.nextLine());
		}
		for(int i=0;i<inputsList.size();i++) {
			inputsListForOutput.add(inputsList.get(i));
			inputsList.set(i,inputsList.get(i)+blankSymbol);
		}


		String[] outputsArray = new String[inputsList.size()];
		
		//TURING MACHINE

		for(int i=0;i<inputsList.size();i++) {
			String outputPath = "";
			Boolean operationDone = false;
			Boolean stateAccepted = false;
			Boolean stateRejected = false;
			String currentInput = inputsList.get(i);
			String[] currentString = currentInput.split("");
			String currentState = startState;
			String currentInputForOutput = inputsListForOutput.get(i);
			
			int tapeHead = 0;
			
			while(operationDone==false) {
				for(int j=0;j<inputSize;j++) {
					if(paths2d[j][0].equals(currentState)) {

						if(paths2d[j][1].equals(currentString[tapeHead])) {

							currentString[tapeHead] = paths2d[j][2];
							if(paths2d[j][3].equals("L") && tapeHead == 0 ) {}
							else {
								if(paths2d[j][3].equals("R")) {
									tapeHead++;
								}else {
									tapeHead--;
								}
							}
							
							if(paths2d[j][4].equals(acceptState)) {
								operationDone = true;
								stateAccepted = true;
							}
							if(paths2d[j][4].equals(rejectState)) {
								operationDone = true;
								stateRejected = true;
							}
							currentState = paths2d[j][4];
							outputPath = outputPath + " " + currentState;
							j=0;
							
						}
					}
					
					
				}
				operationDone = true;
			
			}
			
			String finalOutput = "";
			finalOutput = "String: "+currentInputForOutput+" -----> ";
			
			if(stateAccepted == true) {
				finalOutput = finalOutput+"Accepted\rRoute Taken: "+startState+outputPath+"\r";
				outputsArray[i] = finalOutput;
				System.out.println(finalOutput);
			}
			if(stateRejected == true) {
				finalOutput = finalOutput+"Rejected\rRoute Taken: "+startState+outputPath+"\r";
				outputsArray[i] = finalOutput;
				System.out.println(finalOutput);
			}
			
			
		}
		
		File outFile = new File("TM_Output.txt");
		FileWriter fw = new FileWriter(outFile);
		PrintWriter pw = new PrintWriter(fw);
		
		for(int i=0;i<outputsArray.length;i++) {
			pw.println(outputsArray[i]);
			pw.println();
		}
		
		pw.close();
		scan.close();
		scan1.close();
		scan2.close();

	}
	
	
	public static String getFileName(){
		 JFileChooser chooser = new JFileChooser();
		 FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt file", "txt");
		 chooser.setFileFilter(filter);
		 int returnVal = chooser.showOpenDialog(null);
		 if(returnVal != JFileChooser.APPROVE_OPTION) {
		 throw new RuntimeException("Failed to choose file");
		 }
		 return chooser.getSelectedFile().getAbsolutePath();
		}
	
	
	
}
