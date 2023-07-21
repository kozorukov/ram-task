package app.ram;

public interface RandomAccessMemory {
    void setMemory(int memoryAmount);

    void setMemory(int from,  int to);

    void cleanMemory(int from, int to);

    MemoryAmountInformation getMemoryAmountInformation();

    void cleanMemory();

}
