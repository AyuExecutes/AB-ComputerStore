import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Database class stores the list of computers, staff members, and the categories and types of computers.
 */
public class Database {

    // A list to store all the computers
    private final ArrayList<Computer> computers = new ArrayList<>();

    // A map to store computers by their id
    private final HashMap<String, Computer> computersById = new HashMap<>();

    // A list of categories of computers
    private final ArrayList<String> computerCategories = new ArrayList<>();

    // A map to store types of computers by their category
    private final HashMap<String, ArrayList<String>> computerTypesByCategory = new HashMap<>();

    // A map to store staff members by their username
    private final HashMap<String, Staff> staffMembers = new HashMap<>();

    /**
     * Add a staff member to the database.
     * @param staffMember the staff member to add
     */
    public void addStaffMember(Staff staffMember){
        staffMembers.put(staffMember.getUsername(), staffMember);
    }

    /**
     * Get a staff member by their username.
     * @param username the username of the staff member
     * @return the staff member with the given username
     */
    public Staff getUserByUsername(String username){
        return staffMembers.get(username);
    }

    /**
     * To add a computer to the database, also add the computer by its id, category, and type.
     * @param computer the computer to add
     */
    public void addComputer(Computer computer){
        computers.add(computer);
        computersById.put(computer.getId(), computer);
        addComputerCategory(computer);
        addComputerTypeByCategory(computer);
    }

    /**
     * To load data from a file (.txt) in this case, and add the computers to the database.
     * @param filename the name of the file to load data from
     */
    public void loadFromFile(String filename){
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null){

                String[] currentLine = line.split(",");

                // the first 5 elements are common to all computers, therefore, we can extract them this way.
                String category = currentLine[0];
                String type = currentLine[1];
                String id = currentLine[2];
                String brand = currentLine[3];
                String cpuFamily = currentLine[4];

                // the rest of the elements are specific to each computer type, therefore, extract them based on the category.
                int memorySize;
                int ssdCapacity;
                double price;
                double screenSize;

                switch (category){
                    case "Desktop PC":
                        memorySize = Integer.parseInt(currentLine[5]);
                        ssdCapacity = Integer.parseInt(currentLine[6]);
                        price = Double.parseDouble(currentLine[7]);

                        DesktopPC desktopPC = new DesktopPC(category, type, id, brand, cpuFamily, price, memorySize, ssdCapacity);
                        addComputer(desktopPC);
                        break;

                    case "Laptop":
                        memorySize = Integer.parseInt(currentLine[5]);
                        ssdCapacity = Integer.parseInt(currentLine[6]);
                        screenSize = Double.parseDouble(currentLine[7]);
                        price = Double.parseDouble(currentLine[8]);

                        Laptop laptop = new Laptop(category, type, id, brand, cpuFamily, price, memorySize, ssdCapacity, screenSize);
                        addComputer(laptop);
                        break;

                    case "Tablet":
                        screenSize = Double.parseDouble(currentLine[5]);
                        price = Double.parseDouble(currentLine[6]);

                        Tablet tablet = new Tablet(category,  type, id, brand, cpuFamily, price, screenSize);
                        addComputer(tablet);
                        break;

                    default:
                        throw new Exception("Invalid Category");
                }
            }
        } catch (Exception e){
            System.err.println("Error reading from file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * To add categories of computers to the list of computer categories.
     * @param computer the computer category to add
     */
    private void addComputerCategory(Computer computer){
        // making sure that the category is not already in the list of computer categories.
        if (!computerCategories.contains(computer.getCategory())){
            computerCategories.add(computer.getCategory());
        }
    }

    /**
     * To add computer types by their category to the map of computer types by category.
     * @param computer the computer type to add
     */
    private void addComputerTypeByCategory(Computer computer){
        // check if the hash map has the category of the computer already
        if (computerTypesByCategory.containsKey(computer.getCategory())){
            // if it does, get the list for that category.
            ArrayList<String> list = computerTypesByCategory.get(computer.getCategory());
            // if the list does not already contain the type, then add it.
            if (!list.contains(computer.getType())){
                list.add(computer.getType());
            }

        } else {
            // when category is not in the hash map, create a new list to hold the types
            ArrayList<String> list = new ArrayList<>();
            // add the type to the list
            list.add(computer.getType());
            // store the list in the hashmap
            computerTypesByCategory.put(computer.getCategory(), list);
        }
    }

    /**
     * To get the list of categories of computers.
     * @return the list of categories of computers
     */
    public ArrayList<String> getCategories(){
        return computerCategories;
    }

    /**
     * To get the list of types of computers by their category.
     * @param category the category of the computer
     * @return the list of types of computers by their category
     */
    public ArrayList<String> getTypesByCategory(String category){
        return computerTypesByCategory.get(category);
    }

    /**
     * To get the list of computers using the category and type.
     * @param category the computer category
     * @param type the computer type
     * @return  the list of computers
     */
    public ArrayList<Computer> getComputers(String category, String type){
        // create a list to store the result
        ArrayList<Computer> result = new ArrayList<>();

        for (Computer computer : computers){
            // check if category is null, "All", or the category is the same as the computer category
            boolean matchingCategory = category == null || category.equals("All") || computer.getCategory().equals(category);
            // check if the type is null or the type is the same as the computer type
            boolean matchingType = type == null || computer.getType().equals(type);
            // if both category and type match, add the computer to the result list
            if (matchingCategory && matchingType){
                result.add(computer);
            }
        }
        return result;
    }

    /**
     * To get a computer by its id.
     * @param id the id of the computer
     * @return the computer with the given id
     */
    public Computer getComputerById(String id){
        return computersById.get(id);
    }

    /**
     * To delete a computer
     * @param computer the computer to delete
     */
    public void deleteComputer(Computer computer){
        // remove this computer from the computer's list
        computers.remove(computer);
        // since the computer is deleted, remove it from the map of computers by their id as well.
        computersById.remove(computer.getId());
    }
}
