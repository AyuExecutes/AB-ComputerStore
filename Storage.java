/**
 * Storage Interface provides common methods for Computers such as Desktop PCs and Laptops which have additional attributes related to storage.
 * They are memory size and SSD capacity.
 */
public interface Storage {
    /**
     * get the SSD capacity of the storage.
     * @return the SSD capacity.
     */
    int getSsdCapacity();

    /**
     * to get the memory size of the storage.
     * @return the memory size.
     */
    int getMemorySize();
}
