package exceptions;

import java.util.*;

public class TestExceptions {
	static ArrayList<String> exceptions = new ArrayList<String>();
	static ArrayList<String> Hintss = new ArrayList<String>();
	static final String[] Exceptions= {"ExceptionInInitializerError","NoClassDefFoundError","StackOverflowError","OutOfMemoryError","IndexOutOfBoundsException","ArrayIndexOutOfBoundsException","ClassCastException","IllegalArgumentException","NumberFormatException","IllegalStateException","NullPointerException","IOException","FileNotFoundException","ClassNotFoundException"};
	static final String[] FirstParent = {"LinkageError","LinkageError","VirtualMachineError","VirtualMachineError","RuntimeException","IndexOutOfBoundsException","RuntimeException","RuntimeException","IllegalArgumentException","RuntimeException","RuntimeException","Exception","IOException","ReflectiveOperationException"};
	static final String[] SecondParent = {"Error","Error","Error","Error","Exception","RuntimeException","Exception","Exception","RuntimeException","Exception","Exception","Throwable","Exception","Exception"};
	static final String[] ThirdParent = {"Throwable","Throwable","Throwable","Throwable","Throwable","Exception","Throwable","Throwable","Exception","Throwable","Throwable","","Throwable","Throwable"};
	static final String[] FourthParent = {"","","","","","Throwable","","","Throwable","","","","",""};
	static String[] Hints=new String[14];
	static int punten;
	static int bonuspunten;


	public static void main(String[] args) {
		initializeHints();
		initializeArrayList();
		Scanner scanner=new Scanner(System.in);
		System.out.println("\nKan jij alle OCA required exceptions/errors opnoemen?");
		System.out.println("Per exception/error die je goed hebt krijg je een punt, mits je ook de bijbehorende superclasses kan benoemen!");
		int ronde;
		boolean bools=true;
		for(ronde=1;bools;ronde++) {
			bools=vraag(scanner);
			if(punten==14) {
				break;
			}
		}
		
		System.out.println("\n**************************************************\nTotaal gespeelde rondes: "+ronde+"\nBehaalde punten (exception+parents correct) : "+punten+"\nBonuspunten: "+bonuspunten+"\n\nThnx voor het spelen! ");
		scanner.close();
	}
	
	static boolean vraag(Scanner scanner) {
		System.out.println("\n\n\t   ====== Nog "+exceptions.size()+" te gaan! ======");
		System.out.println("Voer een exception/error in of toets S om te stoppen: ");
		return antwoord(scanner);
	}
	
	static boolean antwoord(Scanner scanner) {
		boolean boolie=true;
		int k=15;
		do {
			String input = scanner.next();
			for(int i=0;i<Exceptions.length;i++) {
				if(input.equals(Exceptions[i])) {
					k=i;
				}
			}
			if(input.equalsIgnoreCase("S")) {
				return false;
			} else if(k<14 && exceptions.indexOf(input)<0) {
				System.out.println("Die had je al eens opgegeven! Probeer het opnieuw: ");
				continue;
			} else if (k>14 && caseCheck(input)) {
				System.out.println("So close! Maar remember, Java is case sensitive...\nProbeer het opnieuw:");
				continue; 
			} else if (k<14 && exceptions.indexOf(input)>-1) {
				System.out.print("Correct! ");
				boolie=false;
				parent1(scanner, k);
			} else {
				boolie=false;
				hint(scanner);
			}
		} while(boolie);
		return true;
	}
	

	
	static void parent1(Scanner scanner, int i) {
		System.out.println("En hoe heet de superclass van "+Exceptions[i]+"?");
		String input=scanner.next();
		if(input.equals(FirstParent[i])) {
			System.out.print("Correct! ");
			parent2(scanner,i);
		} else {
			System.out.println("Helaas, dat is niet correct: de superclass van "+Exceptions[i]+" is "+FirstParent[i]+".");
		}
	}
	
	static void parent2(Scanner scanner, int i) {
		System.out.println("En hoe heet de superclass van "+FirstParent[i]+"?");
		String input=scanner.next();
		if(input.equals(SecondParent[i])) {
			System.out.print("Correct! ");
			if(!ThirdParent[i].equals("")) {
				parent3(scanner,i);
			} else {
				System.out.println("Dus de stamboom van "+Exceptions[i]+" is: \n\t"+SecondParent[i]+"\n\t  "+FirstParent[i]+"\n\t    "+Exceptions[i]);
				bonus(scanner,i);
			}
		} else {
			System.out.println("Helaas, dat is niet correct: de superclass van "+FirstParent[i]+" is "+SecondParent[i]+".");
		}
	}
	
	static void parent3(Scanner scanner, int i) {
		System.out.println("En hoe heet de superclass van "+SecondParent[i]+"?");
		String input=scanner.next();
		if(input.equals(ThirdParent[i])) {
			System.out.print("Correct! ");
			if(!FourthParent[i].equals("")) {
				parent4(scanner,i);
			} else {
				System.out.println("Dus de stamboom van "+Exceptions[i]+" is: \n\t"+ThirdParent[i]+"\n\t  "+SecondParent[i]+"\n\t    "+FirstParent[i]+"\n\t      "+Exceptions[i]);
				bonus(scanner,i);
			}
		} else {
			System.out.println("Helaas, dat is niet correct: de superclass van "+SecondParent[i]+" is "+ThirdParent[i]+".");
		}
	}	
	
	static void parent4(Scanner scanner, int i) {
		System.out.println("En hoe heet de superclass van "+ThirdParent[i]+"?");
		String input=scanner.next();
		if(input.equals(FourthParent[i])) {
			System.out.print("Correct! ");
			System.out.println("Dus de stamboom van "+Exceptions[i]+" is: \n\t  "+FourthParent[i]+"\n\t    "+ThirdParent[i]+"\n\t      "+SecondParent[i]+"\n\t        "+FirstParent[i]+"\n\t          "+Exceptions[i]);
			bonus(scanner,i);
		} else {
			System.out.println("Helaas, dat is niet correct: de superclass van "+FirstParent[i]+" is "+SecondParent[i]+".");
		}
	}
	
	static void bonus(Scanner scanner, int i) {
		punten++;
		exceptions.remove(Exceptions[i]);
		System.out.println("Bonuspunten als je kan zeggen of "+Exceptions[i]+" een checked of unchecked exception is: ");
		while(true) {
			String input=scanner.next();
			if(input.equalsIgnoreCase("checked") && i>10) {
				System.out.println("Klopt als een bus: "+Exceptions[i]+" is inderdaad CHECKED.");
				bonuspunten++;
				break;
			} else if (input.equals("unchecked") && i<11) {
				System.out.println("Klopt als een bus: "+Exceptions[i]+" is inderdaad UNCHECKED.");
				bonuspunten++;
				break;
			} else if(!input.equals("checked") && !input.equals("unchecked")){
				System.out.println("Dit is geen geldige invoer, probeer het opnieuw: ");
				continue;
			} else {
				System.out.println("Helaas, verkeerd gegokt!");
				break;
			}
		}
	}
	
	static void hint(Scanner scanner) {
		String f = exceptions.get(0);
		int index=0;
		for(int i=0;i<Exceptions.length;i++) {
			if(f.equals(Exceptions[i])) {
				index=i;
			}
		}
		System.out.println("Dat is geen exception die je voor het OCA moet weten.");
		System.out.println("Hint: "+Hintss.get(index));
	}
		
	static void initializeArrayList() {
		for(int i=0;i<Exceptions.length;i++) {
			exceptions.add(Exceptions[i]);
		}
	}
	
	static boolean caseCheck(String input) {
		for(String s:Exceptions) {
			if(s.equalsIgnoreCase(input)) {
				return true;
			}
		}
		return false;
	}
	
	static void initializeHints() {
		Hintss.add("This signals that an unexpected exception has occurred in a static initializer. ");
		Hintss.add("This is thrown if the Java Virtual Machine or a ClassLoader instance tries to load in the definition of a class \n(as part of a normal method call or as part of creating a new instance using the new expression) \nand no definition of the class could be found.");
		Hintss.add("This is thrown when a stack overflow occurs because an application recurses too deeply.");
		Hintss.add("This is thrown when the Java Virtual Machine cannot allocate an object because it is out of memory, \nand no more memory could be made available by the garbage collector.");
		Hintss.add("This is thrown to indicate that an index of some sort (such as to an array, to a string, or to a vector) is out of range.");
		Hintss.add("This is thrown to indicate that an array has been accessed with an illegal index. \nThe index is either negative or greater than or equal to the size of the array.");
		Hintss.add("This is thrown to indicate that the code has attempted to cast an object to a subclass of which it is not an instance.");
		Hintss.add("This is thrown to indicate that a method has been passed an illegal or inappropriate argument.");
		Hintss.add("This is thrown to indicate that the application has attempted to convert a string to one of the numeric types, \nbut that the string does not have the appropriate format.");
		Hintss.add("This signals that a method has been invoked at an illegal or inappropriate time. \nIn other words, the Java environment or Java application is not in an appropriate state for the requested operation.");
		Hintss.add("Applications should throw instances of this class to indicate other illegal uses of the null object.");
		Hintss.add("This signals that an input/output exception of some sort has occurred. \nThis class is the general class of exceptions produced by failed or interrupted input/output operations.");
		Hintss.add("This signals that an attempt to open the file denoted by a specified pathname has failed.");
		Hintss.add("This is thrown when an application tries to load in a class through its string name using: \n - The forName method in class Class\n - The findSystemClass method in class ClassLoader \n - The loadClass method in class ClassLoader \nbut no definition for the class with the specified name could be found.");
	}

}
