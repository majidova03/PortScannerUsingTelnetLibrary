package org.example;

import org.apache.commons.net.telnet.TelnetClient;
import java.io.IOException;
import java.util.Scanner;

public class TelnetPortChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter IP address, IP range (in CIDR notation), or domain: ");
        String target = scanner.nextLine();

        System.out.print("Enter port to check: ");
        int port = scanner.nextInt();

        String result = checkPort(target, port);
        System.out.println("Port " + port + " on " + target + ": " + result);

        scanner.close();
    }

    public static String checkPort(String target, int port) {
        try {
            TelnetClient client = new TelnetClient();
            client.setConnectTimeout(2);
            client.connect(target, port);

            client.disconnect();
            return "Open";
        } catch (IOException e) {
            if (e instanceof java.net.ConnectException) {
                return "Closed";
            } else {
                return "Filtered";
            }
        }
    }
}
