package app;

import app.ram.RandomAccessMemoryImpl;

public class App 
{
    public static void main( String[] args )
    {
        var randomAccessMemory = new RandomAccessMemoryImpl(5);
        randomAccessMemory.setMemory(5);
        randomAccessMemory.cleanMemory(2, 3);
        var info = randomAccessMemory.getMemoryAmountInformation();
        randomAccessMemory.setMemory(2);
        var a = 1;
    }
}
