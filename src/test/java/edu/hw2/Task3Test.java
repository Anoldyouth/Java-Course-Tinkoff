package edu.hw2;

import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.Interfaces.ConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.exceptions.ConnectionException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class Task3Test {
    @Test
    @DisplayName("Проверка того, что все упало при FaultyConnection")
    void everythingFellWithFaultyConnection() {
        FaultyConnection connection = mock(FaultyConnection.class);
        when(connection.generateNumber(Mockito.anyInt())).thenReturn(0);
        doCallRealMethod().when(connection).execute(Mockito.anyString());
        FaultyConnectionManager manager = mock(FaultyConnectionManager.class);
        when(manager.getConnection()).thenReturn(connection);

        ConnectionException thrown = assertThrows(
                ConnectionException.class,
                () ->  (new PopularCommandExecutor(manager, 3)).updatePackages()
        );
    }

    static Arguments[] managers() {
        return new Arguments[]{
                Arguments.of(new DefaultConnectionManager()),
                Arguments.of(new FaultyConnectionManager())
        };
    }

    @ParameterizedTest
    @DisplayName("Проверка, что все прошло успешно")
    @MethodSource("managers")
    void successful(ConnectionManager manager) {
        assertDoesNotThrow(() -> (new PopularCommandExecutor(manager, 3)).updatePackages());
    }
}
