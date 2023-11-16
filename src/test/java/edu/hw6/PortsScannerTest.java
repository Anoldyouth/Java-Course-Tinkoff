package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class PortsScannerTest {
    @Test
    void portIsBusy() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080); DatagramSocket datagramSocket = new DatagramSocket(8080)) {
            ServicePort tcp = new ServicePort(Protocol.TCP, 8080, null);
            ServicePort udp = new ServicePort(Protocol.UDP, 8080, null);

            assertThat(PortsScanner.busyPorts()).contains(tcp, udp);
        }
    }
}
