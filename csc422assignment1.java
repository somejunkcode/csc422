import java.util.ArrayList;
import java.util.Scanner;
public class csc422assignment1 {

    public static void main(String[] args) {
        System.out.println("Pet Database Program");
        int userSelection = 0;  // Control variable to determine what user wants to do and when to close the program
        ArrayList<Pet> petList = new ArrayList<>();  // ArrayList to store Pet objects

        do {  // Do while loop to continue presenting and executing menu selections until the user decides to exit
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            userSelection = input.nextInt();
            switch(userSelection) {
                case 1: viewAllPets(petList); break;
                case 2: petList.addAll(addNewPet()); break;
                case 5: searchPetByName(petList); break;
                case 6: searchPetByAge(petList); break;
                case 7: System.out.print("\nGoodbye!\n"); break;
                default: System.out.print("Invalid selection, please choose between 1-7\n\n");
            }
        } while(userSelection != 7);
    }

    static class Pet {  // Pet class with one constructor and related getters and setters for age and name operations
        private String petName;
        private int petAge;

        public Pet(String name, int age) {
            this.petName = name;
            this.petAge = age;
        }
        public String getPetName() {
            return this.petName;
        }
        public void setPetName(String name) {
            this.petName = name;
        }
        public int getPetAge() {
            return this.petAge;
        }
        public void setPetAge(int age) {
            this.petAge = age;
        }
    }

    public static void viewAllPets(ArrayList<Pet> pets) {  // viewAllPets displays grid of all pets with names and ages
        System.out.println("+----------------------+\n| ID | NAME      | AGE |\n+----------------------+");
        for (int x = 0; x < pets.size(); x++) {
            System.out.printf("|%3d |%10s |%4d |\n", x, pets.get(x).getPetName(), pets.get(x).getPetAge());
        }
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set\n");
    }
    public static ArrayList<Pet> addNewPet() {  // Allows for creation of new pet objects and sends them back to be
        // added to original list
        ArrayList<Pet> newPetList = new ArrayList<>();  // ArrayList to store newly added pets
        String newPetName;
        int newPetAge;
        int petCount = 0;
        do {
            System.out.print("\nadd pet (name, age): ");
            Scanner newPetInput = new Scanner(System.in);
            newPetName = newPetInput.next();
            if (newPetName.contains("done")) {
                System.out.println(petCount + " pets added");
            } else {
                newPetAge = Integer.parseInt(newPetInput.next());
                newPetList.add(new Pet(newPetName, newPetAge));
                petCount++;
            }
        } while (newPetName.compareToIgnoreCase("done") != 0);

        return newPetList;
    }
    public static void searchPetByName(ArrayList<Pet> pets) {  // Searches list of pets by name and lists all of them
        // out in grid similar to viewAllPets
        System.out.print("\nEnter a name to search: ");
        Scanner petName = new Scanner(System.in);
        String petNameToSearch = petName.next();
        int searchMatchCount = 0;
        System.out.println("+----------------------+\n| ID | NAME      | AGE |\n+----------------------+");
        for (int x = 0; x < pets.size(); x++) {
            if (pets.get(x).getPetName().equalsIgnoreCase(petNameToSearch)) {
                System.out.printf("|%3d |%10s |%4d |\n", x, pets.get(x).getPetName(), pets.get(x).getPetAge());
                searchMatchCount++;
            }
        }
        System.out.println("+----------------------+");
        System.out.println(searchMatchCount + " pet name(s) match search term\n");
    }
    public static void searchPetByAge(ArrayList<Pet> pets) {  // Searches list of pets by age and lists all of them
        // out in grid similar to viewAllPets
        System.out.print("\nEnter age to search: ");
        Scanner petName = new Scanner(System.in);
        int petAgeToSearch = petName.nextInt();
        int searchMatchCount = 0;
        System.out.println("+----------------------+\n| ID | NAME      | AGE |\n+----------------------+");
        for (int x = 0; x < pets.size(); x++) {
            if (pets.get(x).getPetAge() == petAgeToSearch) {
                System.out.printf("|%3d |%10s |%4d |\n", x, pets.get(x).getPetName(), pets.get(x).getPetAge());
                searchMatchCount++;
            }
        }
        System.out.println("+----------------------+");
        System.out.println(searchMatchCount + " pet age(s) match search term\n");
    }
}