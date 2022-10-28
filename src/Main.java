import java.util.*;

public class Main {
    public static String secretCode;
    public static boolean runGame = true;

    public static String secretCodeHidden;
    public static String set = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static int possibleSymbols;

    public static int difficulty;

    public static void main(String[] args) {
        secretCodeGen();
        while (runGame) {
            System.out.print("The secret is prepared: ");
            for (int i = 0; i < difficulty; i++) {
                System.out.print("*");
            }
            if (possibleSymbols < 10) {
                System.out.printf(" (0-%c).", set.charAt(possibleSymbols - 1));
            } else {
                System.out.printf(" (0-9, a-%c).", set.charAt(possibleSymbols - 1));
            }
            //System.out.print();


            int count = 1;
            while (runGame) {
                System.out.println("Turn " + count + ":");
                count++;
                game();

            }
        }

        //System.out.println("The secret code is " + secretCode);


        /*
        String Sinput = Integer.toString(input);
        int secretCode = 9305;
        String StringCode = Integer.toString(input);
        check(Sinput, StringCode);
         */

    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public static void secretCodeGen() {


        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please, enter the secret code's length:");
            difficulty = scanner.nextInt();

            System.out.println("Input the number of possible symbols in the code:");
            possibleSymbols = scanner.nextInt();
        } catch (Exception InputMismatchException) {
            System.out.println("Error, you entered an invalid number");
            runGame = false;
            return;

        }

        if (difficulty <= 0) {
            System.out.println("Error, you cant choose the length of the secret code to " + difficulty);
            runGame = false;
        }

        if (possibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            runGame = false;
        } else if (difficulty > possibleSymbols) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d%n with %d%n unique symbols",
                    difficulty,possibleSymbols);
            runGame = false;
        } else {
            boolean run = true;
            while (run) {
                ArrayList<Character> shuffleList = new ArrayList<>();
                StringBuilder outputString = new StringBuilder();
                //fills an array with characters depending on possible symbols
                for (int i = 0; i < possibleSymbols; i++) {
                    shuffleList.add(set.charAt(i));
                }
                //shufles the array and adds characters to the output list, then deletes that char from the list
                for (int i = 0; i < difficulty; i++) {
                    Collections.shuffle(shuffleList);
                    outputString.append(shuffleList.get(0));
                    shuffleList.remove(shuffleList.get(0));
                }
                //System.out.println(String.valueOf(outputString));
                secretCode = String.valueOf(outputString);
                run = false;

            }

        }
    }
    public static boolean moreThenOnce(String input) {

        int count = 0;
        for (int i = 0; i < 10; i++) {
            //System.out.println("checking if " + i + " appears more then once");
            for (int y = 0; y < input.length(); y++) {
                //System.out.println("Running Y check " + input.charAt(y));
                if (Integer.parseInt(String.valueOf(input.charAt(y))) == i) {
                    //System.out.println("Number " + i + " found");
                    count++;
                }
            }
            if (count > 1) {
                return true;
            }
            count = 0;
        }
        return false;


    }
    public static void game (){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secretCode.length(); i++) {
            for (int y = 0; y < secretCode.length(); y++) {
                if (input.charAt(i) == secretCode.charAt(y)) {
                    cows++;
                }
            }
        }

        for (int i = 0; i < secretCode.length(); i++) {
            if (input.charAt(i) == secretCode.charAt(i)) {
                bulls++;
                cows--;
            } else {

            }
        }
        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None.");
        } else if (bulls == 0 && cows != 0) {
            System.out.println("Grade: " + cows + " cow(s).");
        } else if (cows == 0 && bulls != 0) {
            System.out.println("Grade: " + bulls + " bull(s).");
            if (bulls == difficulty) {
                System.out.println("Congratulations! You guessed the secret code.");
                runGame = false;
            }
        } else {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");
        }
    }

}

