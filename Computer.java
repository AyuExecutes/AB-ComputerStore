public interface Computer {
    /**
     * Get the category of the computer.
     * @return the category of the computer
     */
    String getCategory();

    /**
     * Get the type of the computer.
     * @return the type of the computer
     */
    String getType();

    /**
     * Get the id of the computer.
     * @return the id of the computer
     */
    String getId();

    /**
     * Get the brand of the computer.
     * @return the brand of the computer
     */
    String getBrand();

    /**
     * Get the CPU family of the computer.
     * @return the CPU family of the computer
     */
    String getCpuFamily();

    /**
     * Get the price of the computer.
     * @return the price of the computer
     */
    double getPrice();

    /**
     * Set the category of the computer.
     * @param category the category of the computer
     */
    void setCategory(String category);

    /**
     * Set the type of the computer.
     * @param type the type of the computer
     */
    void setType(String type);

    /**
     * Set the id of the computer.
     * @param id the id of the computer
     */
    void setId(String id);

    /**
     * Set the brand of the computer.
     * @param brand the brand of the computer
     */
    void setBrand(String brand);

    /**
     * Set the CPU family of the computer.
     * @param cpuFamily the CPU family of the computer
     */
    void setCpuFamily(String cpuFamily);

    /**
     * Set the price of the computer.
     * @param price the price of the computer
     */
    void setPrice(double price);
}
