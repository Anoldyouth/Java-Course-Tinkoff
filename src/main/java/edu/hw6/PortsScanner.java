package edu.hw6;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PortsScanner {
    private final static String PORTS_PROPERTIES = "src/main/resources/ports.properties";
    private final static int START_PORT = 0;
    private final static int FINAL_PORT = 49151;

    private PortsScanner() {}

    public static List<ServicePort> busyPorts() throws IOException {
        Properties portsProps = new Properties();
        portsProps.load(new FileInputStream(PORTS_PROPERTIES));

        List<ServicePort> servicesPorts = new ArrayList<>();

        for (int port = START_PORT; port <= FINAL_PORT; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                serverSocket.close();
            } catch (IOException e) {
                servicesPorts.add(new ServicePort(Protocol.TCP, port, portsProps.getProperty(String.valueOf(port))));
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                datagramSocket.close();
            } catch (IOException e) {
                servicesPorts.add(new ServicePort(Protocol.UDP, port, portsProps.getProperty(String.valueOf(port))));
            }
        }

        return servicesPorts;
    }
}
