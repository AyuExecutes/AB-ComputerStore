public class Tablet extends ComputerBase implements Display {

    // instance variable that is special to Tablet on top of the common attributes of all Computer objects
    private double screenSize;

    /**
     * Constructor
     * @param category the category
     * @param type the type
     * @param id the id
     * @param brand the brand
     * @param cpuFamily the cpu family
     * @param price the price
     * @param screenSize the screen size
     */
    public Tablet(String category, String type, String id, String brand, String cpuFamily, double price, double screenSize){
        super(category, type, id, brand, cpuFamily, price);
        this.screenSize = screenSize;
    }

    /**
     * to set the screen size
     * @param screenSize the screen size
     */
    public void setScreenSize(double screenSize){
        this.screenSize = screenSize;
    }

    /**
     * to get the screen size
     * @return the screen size
     */
    public double getScreenSize(){
        return screenSize;
    }
}
