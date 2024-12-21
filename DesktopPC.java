public class DesktopPC extends ComputerBase implements Storage {
    // instance variables that is special to Desktop PC on top of the common attributes of all Computer objects
    private int memorySize;
    private int ssdCapacity;

    /**
     * constructor
     * @param category the category
     * @param type the type
     * @param id the id
     * @param brand the brand
     * @param cpuFamily the cpu family
     * @param price the price
     * @param memorySize the memory size
     * @param ssdCapacity the ssd capacity
     */
    public DesktopPC(String category, String type, String id, String brand, String cpuFamily, double price, int memorySize, int ssdCapacity){
        super(category, type, id, brand, cpuFamily, price);
        this.memorySize = memorySize;
        this.ssdCapacity = ssdCapacity;
    }

    /**
     * set the memory size
     * @param memorySize the memory size
     */
    public void setMemorySize(int memorySize){
        this.memorySize = memorySize;
    }

    /**
     * get the memory size
     * @return the memory size
     */
    public int getMemorySize(){
        return memorySize;
    }

    /**
     * set the ssd capacity
     * @param ssdCapacity the ssd capacity
     */
    public void setSsdCapacity(int ssdCapacity){
        this.ssdCapacity = ssdCapacity;
    }

    /**
     * get the ssd capacity
     * @return the ssd capacity
     */
    public int getSsdCapacity(){
        return ssdCapacity;
    }
}
