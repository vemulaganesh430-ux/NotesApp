import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {

    static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {

            System.out.println("\n===== NOTES APP =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addNote(sc);
                        break;

                    case 2:
                        viewNotes();
                        break;

                    case 3:
                        System.out.println("Exiting Notes App...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
            }
        }

        sc.close();
    }

    static void addNote(Scanner sc) {

        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {

            fw.write(note);
            fw.write(System.lineSeparator());

            System.out.println("Note saved successfully!");

        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void viewNotes() {

        System.out.println("\n===== SAVED NOTES =====");

        try (BufferedReader br =
                 new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                found = true;
            }

            if (!found) {
                System.out.println("No notes available.");
            }

        } catch (IOException e) {
            System.out.println("Unable to read notes: " + e.getMessage());
        }
    }
}