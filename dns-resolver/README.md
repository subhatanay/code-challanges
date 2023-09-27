## dns resolver

### basic tcp socket based client to convert or resolve a domain name to ip address

### Build
```
mvn clean build
alias dnsrv='java -jar target/dns-resolver-1.0-SNAPSHOT.jar'
```

### Execute
```
dnsrv github.com 
QR :: (0 - Request), (1 - Response) : 1
Opcode :: 0
Authoritative Answer :: 0
Truncated :: 0
Recursion Desired :: 1
Recursion Available :: 1
Response Code :: 0

Question :: 
        github.com.

Answer ::  
        20.207.73.82

Authorities :: 
        dns2.p08.nsone.net.
        ns-1283.awsdns-32.org.
        dns3.p08.nsone.net.
        ns-1707.awsdns-21.co.uk.
        dns1.p08.nsone.net.
        ns-520.awsdns-01.net.
        ns-421.awsdns-52.com.
        dns4.p08.nsone.net.

Additionals :: 
        205.251.193.165
        198.51.44.8
        198.51.45.8
        198.51.44.72
        198.51.45.72
        2620:04d:400:6259:07:08:00:01
        2a0:edc0:6259:07:08:00:00:02
        2620:04d:400:6259:07:08:00:03
        2a0:edc0:6259:07:08:00:00:04


```

### Reference
https://codingchallenges.fyi/challenges/challenge-dns-resolver
https://datatracker.ietf.org/doc/html/rfc1035#section-4.1.1