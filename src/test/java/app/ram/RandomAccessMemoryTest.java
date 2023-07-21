package app.ram;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomAccessMemoryTest {

    @Test
    public void givenRandomAccessMemory_whenSetMemory_thenCorrectBitsAreFilled() {
        var randomAccessMemory = new RandomAccessMemoryImpl(5);

        randomAccessMemory.setMemory(2);

        assertThat(randomAccessMemory.getMemory().get(0)).isTrue();
        assertThat(randomAccessMemory.getMemory().get(1)).isTrue();
        assertThat(randomAccessMemory.getMemory().get(2)).isFalse();
    }

    @Test
    public void givenRandomAccessMemory_whenSetMemoryWithBorders_thenCorrectBitsAreFilled() {
        var randomAccessMemory = new RandomAccessMemoryImpl(5);

        randomAccessMemory.setMemory(2, 3);

        assertThat(randomAccessMemory.getMemory().get(2)).isTrue();
        assertThat(randomAccessMemory.getMemory().get(3)).isTrue();
    }

    @Test
    public void givenRandomAccessMemory_whenFreeMemory_thenAllBitsAreFree() {
        var randomAccessMemory = new RandomAccessMemoryImpl(3);
        randomAccessMemory.setMemory(3); // так делать нельзя, но сегодня можно

        randomAccessMemory.freeMemory();

        assertThat(randomAccessMemory.getMemory()).containsOnly(false);
    }

    @Test
    public void givenRandomAccessMemory_whenFreeMemoryWithBorders_thenCorrectBitsAreFree() {
        var randomAccessMemory = new RandomAccessMemoryImpl(5);
        randomAccessMemory.setMemory(3); // так делать нельзя, но сегодня можно

        randomAccessMemory.freeMemory(0, 1);

        assertThat(randomAccessMemory.getMemory().get(0)).isFalse();
        assertThat(randomAccessMemory.getMemory().get(1)).isFalse();
        assertThat(randomAccessMemory.getMemory().get(2)).isTrue();
    }

    @Test
    public void givenRandomAccessMemory_whenGetMemoryAmountInformation_thenCorrectInformationReturns() {
        var randomAccessMemory = new RandomAccessMemoryImpl(5);

        var information = randomAccessMemory.getMemoryAmountInformation();

        assertThat(information.getFreeMemoryAmount()).isEqualTo(5);
        assertThat(information.getFullMemoryAmount()).isEqualTo(0);
    }
}