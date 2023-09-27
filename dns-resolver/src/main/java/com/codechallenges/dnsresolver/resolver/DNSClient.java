package com.codechallenges.dnsresolver.resolver;

import com.codechallenges.dnsresolver.DNSUtils;
import com.codechallenges.dnsresolver.dto.DNSAddressRecord;
import com.codechallenges.dnsresolver.dto.DNSMessage;
import com.codechallenges.dnsresolver.dto.RecordTypeEnum;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DNSClient implements DNSResolver {

    private Socket clientSocket;
    private void initSocket(String ip) throws IOException {
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(ip, Integer.parseInt(DNSUtils.DEFAULT_DNS_SERVER_PORT)));

    }
    public DNSMessage queryDnsServer(String adder, DNSMessage dnsMessage) throws IOException {
        initSocket(adder);
        if (clientSocket != null && clientSocket.isConnected()) {
            System.out.println("Querying (" + adder + ") for domain => " + dnsMessage.getQuestions().get(0).getQuestionName() + " .... ");

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

    @Override
    public DNSMessage resolveDomain(String domain, String... options) throws Exception {
        boolean recursive = true;
        if (options != null && options.length ==1 ) {
            recursive = Boolean.valueOf(options[0]);
        }

        DNSMessage query = DNSMessage.buildDNSMessage(domain, recursive);
        DNSMessage response = null;
        if (!recursive) {
            response = recursiveDomainResolve(DNSUtils.DEFAULT_DNS_SERVER_IP, query, new HashSet<>());
        } else {
            response = queryDnsServer(DNSUtils.DEFAULT_DNS_SERVER_IP, query);
        }
        return response;
    }

    private  DNSMessage recursiveDomainResolve(String ip, DNSMessage query, Set<String> visited) throws IOException, InterruptedException {
        DNSMessage response = queryDnsServer(ip,query);

        if (response.getAnswers().size() > 0) return response;
        List<String> ips =  response.getAdditionals().stream().filter(dns -> dns.getType() == RecordTypeEnum.A).map(DNSAddressRecord::getRdData).collect(Collectors.toList());
        for (String ip1 : ips) {
            if (visited.contains(ip1)) continue;
            visited.add(ip1);
            response =  recursiveDomainResolve(ip1, query,visited);
            Thread.sleep(2000);
        }
        return response;
    }
}
