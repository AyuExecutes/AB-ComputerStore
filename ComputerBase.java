public class ComputerBase implements Computer {
    // the common attributes of all Computer objects
    private String category;
    private String type;
    private String id;
    private String brand;
    private String cpuFamily;
    private double price;

    /**
     * constructor
     * @param category the category of the computer
     * @param type the type of the computer
     * @param id the id of the computer
     * @param brand the brand of the computer
     * @param cpuFamily the CPU family of the computer
     * @param price the price of the computer
     */
    public ComputerBase(String category, String type, String id, String brand, String cpuFamily, double price){
        this.category = category;
        this.type = type;
        this.id = id;
        this.brand = brand;
        this.cpuFamily = cpuFamily;
        this.price = price;
    }

    /**
     * Set the category of the computer.
     * @param category the category of the computer
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
     * Get the category of the computer.
     * @return the category of the computer
     */
    public String getCategory(){
        return  category;
    }

    /**
     * Set the type of the computer
     * @param type the type of the computer
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * Get the type of the computer.
     * @return the type of the computer
     */
    public String getType(){
        return type;
    }

    /**
     * Set the id of the computer
     * @param id the id of the computer
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * Get the id of the computer.
     * @return the id of the computer
     */
    public String getId(){
        return id;
    }

    /**
     * Set the brand of the computer
     * @param brand the brand of the computer
     */
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * Get the brand of the computer.
     * @return the brand of the computer
     */
    public String getBrand(){
        return brand;
    }

    /**
     * Set the CPU family of the computer
     * @param cpuFamily the CPU family of the computer
     */
    public void setCpuFamily(String cpuFamily){
        this.cpuFamily = cpuFamily;
    }

    /**
     * Get the CPU family of the computer
     * @return the CPU family of the computer
     */
    public String getCpuFamily(){
        return cpuFamily;
    }

    /**
     * Set the price of the computer
     * @param price the price of the computer
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Get the price of the computer
     * @return the price of the computer
     */
    public double getPrice(){
        return price;
    }
}
