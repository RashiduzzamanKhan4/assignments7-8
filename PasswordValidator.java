import java.util.Scanner;

public class PasswordValidator {

    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        String specialChars = "~!@#$%^&*()-=+_";

        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (specialChars.indexOf(ch) != -1) {
                hasSpecial = true;
            }
        }

        int categoryCount = 0;
        if (hasLower) categoryCount++;
        if (hasUpper) categoryCount++;
        if (hasDigit) categoryCount++;
        if (hasSpecial) categoryCount++;

        return categoryCount >= 3;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password to validate: ");
        String input = scanner.nextLine();

        if (isValidPassword(input)) {
            System.out.println("Password is valid");
        } else {
            System.out.println("Password is invalid X");
            System.out.println("Make sure it:");
            System.out.println("- Has a length between 8 and 16 characters");
            System.out.println("- Contains at least 3 of the following: lowercase, uppercase, digits, special symbols (~!@#$%^&*()-=+_)");
        }
    }
}
