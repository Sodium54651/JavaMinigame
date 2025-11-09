package TrixieLulamoon;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sodium
 */
import java.util.Scanner;
public class RenameCalculator {
    public static void main(String[] args) {
        System.out.println("Do the thing: ");
        System.out.println("1. +");
        System.out.println("2. -");
        System.out.println("3. *");
        System.out.println("4. \\");

        Scanner scanner = new Scanner(System.in);
        int operation = scanner.nextInt();

        System.out.println("Your first num: ");
        int x = scanner.nextInt();
        System.out.println("Twice num: ");
        int y = scanner.nextInt();

        int repuper = 0;

        switch (operation) {
            case 1:
                repuper = x + y;
                break;
            case 2:
                repuper = x - y;
                break;
            case 3:
                repuper = x * y;
                break;
            case 4:
                repuper = x / y;
                break;
            default:
                break;
        }

        System.out.println("Answer " + repuper);
        System.out.println("NesterenkoRework");
}
}