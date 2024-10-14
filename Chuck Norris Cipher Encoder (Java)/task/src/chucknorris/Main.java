package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputMaster = new Scanner(System.in);

        String option;
        for (;;) {
            System.out.print("Please input operation (encode/decode/exit): ");
            option = inputMaster.nextLine();

            String input;
            switch (option) {
                case "encode" -> {
                    System.out.print("Input string: ");
                    input = inputMaster.nextLine();

                    System.out.print("Encoded string:\n");
                    System.out.println(ChuckNorrisCipher.encode(input));
                }
                case "decode" -> {
                    System.out.print("Input encoded string: ");
                    input = inputMaster.nextLine();

                    String output;

                    try {
                        output = "Decoded string:\n" + ChuckNorrisCipher.decode(input);
                    } catch (IllegalArgumentException illegalArgumentException) {
                        output = illegalArgumentException.getMessage();
                    }

                    System.out.println(output);
                }
                case "exit" -> {
                    System.out.print("Bye!\n");
                    inputMaster.close();
                    return;
                }
                default -> System.out.printf("There is no '%s' operation\n", option);
            }
        }
    }
}