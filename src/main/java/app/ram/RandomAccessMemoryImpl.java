package app.ram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomAccessMemoryImpl implements RandomAccessMemory {

    private final int maxMemoryAmount;

    private final List<Boolean> memory;

    @Override
    public void setMemory(int memoryAmount) {
        if (memoryAmount > getFreeMemoryAmount()) {
            throw new RuntimeException("Недостаточно свободной памяти!");
        }
        for (int i = 0; i < memory.size(); i++) {
            if (isEmpty(memory.subList(i, i + memoryAmount))) {
                for (int j = i; j < i + memoryAmount; j++) {
                   memory.set(j, true);
                }
                break;
            }
            throw new RuntimeException("Требуется дефрагментация памяти!");
        }
    }

    private int getFreeMemoryAmount() {
        var usefulMemory =  memory.stream()
                .filter(element -> element)
                .toList()
                .size();
        return maxMemoryAmount - usefulMemory;
    }

    private boolean isEmpty(List<Boolean> memory) {
        var resultList = memory.stream()
                .filter(element -> element)
                .toList();
        return resultList.isEmpty();
    }

    @Override
    public void setMemory(int from, int to) {
        if (!isEmpty(memory.subList(from, to + 1))) {
            throw new RuntimeException("Недостаточно свободной памяти");
        }
        for (int i = from; i < to + 1; i++) {
            memory.set(i, true);
        }
    }

    @Override
    public void cleanMemory(int from, int to) {
        for (int i = from; i < to + 1; i++) {
            memory.set(i, false);
        }
    }

    @Override
    public MemoryAmountInformation getMemoryAmountInformation() {
        var freeMemoryAmount = getFreeMemoryAmount();
        return MemoryAmountInformation.builder()
                .freeMemoryAmount(freeMemoryAmount)
                .fullMemoryAmount(maxMemoryAmount - freeMemoryAmount)
                .build();
    }

    @Override
    public void cleanMemory() {
        Collections.fill(memory, Boolean.FALSE);
    }

    public RandomAccessMemoryImpl(int maxMemoryAmount) {
        this.maxMemoryAmount = maxMemoryAmount;
        this.memory = new ArrayList<>(Collections.nCopies(maxMemoryAmount, false));
    }
}
