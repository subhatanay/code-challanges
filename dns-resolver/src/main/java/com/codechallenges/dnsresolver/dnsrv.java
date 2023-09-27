package com.codechallenges.dnsresolver;

import com.codechallenges.dnsresolver.dto.DNSMessage;
import com.codechallenges.dnsresolver.resolver.DNSClient;
import com.codechallenges.dnsresolver.resolver.DNSResolver;

public class dnsrv {
    public static void main(String[] args) throws Exception {
        try {
            if (args.length > 2) throw new IllegalArgumentException("Maximum two argument needs to provided");

            if (args.length ==0 ) throw new IllegalArgumentException("Domain name needs to be provided");

            String domain = "";
            String[] options = new String[1];
            if (args.length == 1) {
                domain = args[0];
                options[0] = "true";
            }
            if (args.length == 2) {
                domain = args[0];
                options[0] = "false";
            }

            DNSResolver resolver = new DNSClient();

            DNSMessage response = resolver.resolveDomain(domain, options);
            System.out.println(response);
        } catch (Exception exception) {
            System.err.println("ERR :: " + exception.getMessage());
            System.exit(1);
        }
    }
}
