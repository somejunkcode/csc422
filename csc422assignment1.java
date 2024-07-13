import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class csc422assignment1 {
    public static void main(String[] args) throws FileNotFoundException {
        int userSelection = 0;  // Control variable to determine what user wants to do and when to close the program
        ArrayList<Pet> petList = new ArrayList<>();  // ArrayList to store Pet objects
        petList = loadDatabase(petList);
        System.out.println("\n\nPet Database Program");

        do {  // Do while loop to continue presenting and executing menu selections until the user decides to exit
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            //System.out.println("3) Update an existing pet");
            System.out.println("3) Remove an existing pet");
            //System.out.println("5) Search pets by name");
            //System.out.println("6) Search pets by age");
            System.out.println("4) Exit program");
            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            userSelection = input.nextInt();
            switch(userSelection) {
                case 1: viewAllPets(petList); break;
                case 2: petList.addAll(addNewPet()); break;
                //case 3: petList = updatePet(petList); break;
                case 3: petList = removePet(petList); break;
                //case 5: searchPetByName(petList); break;
                //case 6: searchPetByAge(petList); break;
                case 4: saveDatabase(petList); System.out.print("\nGoodbye!\n"); break;
                default: System.out.print("Invalid selection, please choose between 1-7\n\n");
            }
        } while(userSelection != 4);
    }

    /**
     Checks for file named petDb.txt.  If petDb.txt exists, this will parse each line lines, and creates Pet objects.
     If petDb.txt does not exist, a blank file is created.
     */
    public static ArrayList<Pet> loadDatabase(ArrayList<Pet> pets) throws FileNotFoundException {
        try {
            File dbFile = new File("petDb.txt");
            if (dbFile.createNewFile()) {
                System.out.println("File " + dbFile.getName() + " not found, creating a new file");
            } else {
                Scanner processDbInput = new Scanner(new File("petDb.txt"));  // Read petDb.txt file
                while(processDbInput.hasNext()) {  // While loop to create pet objects and store them in pets ArrayList
                    String currentPetName = processDbInput.next();
                    int currentPetAge = processDbInput.nextInt();
                    pets.add(new Pet(currentPetName, currentPetAge));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return pets;
    }
    public static void saveDatabase(ArrayList<Pet> pets) {  // Writes each object in petList to petDb.txt file
        try {
            File dbFile = new File("petDb.txt");
            FileWriter writer = new FileWriter(dbFile);
            for (int x = 0; x < pets.size(); x++) {
                writer.write(pets.get(x).getPetName() + " " + pets.get(x).getPetAge() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
    /* public static ArrayList<Pet> updatePet(ArrayList<Pet> pets) {  // Facilitates editing of pet objects
        viewAllPets(pets);
        System.out.print("Enter the pet ID you want to update: ");
        Scanner petIdToUpdate = new Scanner(System.in);
        int petId = Integer.parseInt(petIdToUpdate.next());
        System.out.print("Enter new name and new age: ");
        Scanner newPetData = new Scanner(System.in);
        String newPetName = newPetData.next();
        int newPetAge = Integer.parseInt(newPetData.next());
        pets.set(petId, new Pet(newPetName,newPetAge));
        pets.get(petId).setPetName(newPetName);
        pets.get(petId).setPetAge(newPetAge);
        return pets;
    } */
    public static ArrayList<Pet> removePet(ArrayList<Pet> pets) {  // Displays all grid of pet objects and lets user
        // choose which one to remove based on index number
        viewAllPets(pets);
        System.out.print("Enter the pet ID to remove: ");
        Scanner petIdToRemove = new Scanner(System.in);
        int petId = Integer.parseInt(petIdToRemove.next());
        System.out.println(pets.get(petId).petName + " " + pets.get(petId).petAge + " is removed.");
        pets.remove(petId);
        return pets;
    }
    /* public static void searchPetByName(ArrayList<Pet> pets) {  // Searches list of pets by name and lists all of them
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
    } */
}