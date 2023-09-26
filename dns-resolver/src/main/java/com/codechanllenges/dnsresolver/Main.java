package com.codechanllenges.dnsresolver;

import com.codechanllenges.dnsresolver.dto.DNSAddressRecord;
import com.codechanllenges.dnsresolver.dto.DNSMessage;
import com.codechanllenges.dnsresolver.resolver.DNSResolver;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        DNSMessage message = DNSMessage
                .buildDNSMessage("dns.google.com");


        DNSMessage dnsMessage= null;
        String ip = "198.41.0.4";
        DNSResolver resolver = new DNSResolver();
        do {
            dnsMessage = resolver.queryDnsServer(ip , message);
            if (dnsMessage !=null) {
                if (dnsMessage.getAnswers().size() > 0) break;

                ip = dnsMessage.getAdditionals().stream().filter(dns -> dns.getType() == 1).map(DNSAddressRecord::getRdData).collect(Collectors.toList()).get(0);
                Thread.sleep(500);
            }
        } while(true);

        System.out.println(dnsMessage);

    }
}
