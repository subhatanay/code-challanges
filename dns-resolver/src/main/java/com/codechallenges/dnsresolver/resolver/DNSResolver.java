package com.codechallenges.dnsresolver.resolver;


import com.codechallenges.dnsresolver.dto.DNSMessage;

public interface DNSResolver {

    DNSMessage resolveDomain(String domain, String ... options) throws Exception;


}
