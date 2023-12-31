package edu.hw2;

import edu.hw2.Task4.CallStackHelper;
import edu.hw2.Task4.CallingInfo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Task4Test {
    @Test
    @DisplayName("Проверка возврата")
    void successful() {
        CallingInfo answer = CallStackHelper.callingInfo();

        assertThat(answer.className()).isEqualTo(this.getClass().getName());
        assertThat(answer.methodName()).isEqualTo("successful");
    }
}
