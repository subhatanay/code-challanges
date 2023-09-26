package com.codechanllenges.dnsresolver.resolver;

import com.codechanllenges.dnsresolver.dto.DNSAddressRecord;
import com.codechanllenges.dnsresolver.dto.DNSHeader;
import com.codechanllenges.dnsresolver.dto.DNSMessage;
import com.codechanllenges.dnsresolver.dto.DNSQuestion;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class DNSResolver {
    private Socket clientSocket;

    private final String dnsServer = "204.74.101.1";
    private final String dnsPort = "53";


    public DNSResolver() throws IOException {
    }

    private void initSocket(String ip) throws IOException {
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(ip, Integer.parseInt(dnsPort)));
    }

    public DNSMessage queryDnsServer(String addrs, DNSMessage dnsMessage) throws IOException {
        initSocket(addrs);
        if (clientSocket != null && clientSocket.isConnected()) {
            System.out.println("Sending dns request to google dns (192.12.94.30) for domain => " + dnsMessage.getQuestions().get(0).getQuestionName() + "\n");

            clientSocket.getOutputStream().write(dnsMessage.getBytes());
            clientSocket.getOutputStream().flush();
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());


            int responseSize = (dataInputStream.readShort());
            byte[] buffer = new byte[responseSize];
            dataInputStream.read(buffer, 0, responseSize);
            ByteBuffer buff = ByteBuffer.wrap(buffer);

            clientSocket.close();

            return DNSMessage.fromResponseBuffer(buff);

        }
        return null;
    }


}
