package app.ram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoryAmountInformation {
    private final Integer freeMemoryAmount;

    private final Integer fullMemoryAmount;
}
