package com.codechanllenges.dnsresolver.dto;

import com.codechanllenges.dnsresolver.ByteUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DNSAddressRecord {

    private String name;

    private short type;

    private short classType;

    private int ttl;

    private short rdLength;

    private String rdData;

    public DNSAddressRecord(ByteBuffer buffer) throws IOException {
        name = ByteUtils.readDomainName(buffer);

        type = buffer.getShort();
        classType = buffer.getShort();
        ttl = buffer.getInt();
        rdLength = buffer.getShort();
        if (type == 1 ) {
            rdData = parseIpAddress(buffer, rdLength);
        } else if (type == 2 ) {
            rdData = ByteUtils.readDomainName(buffer);
        } else if (type == 28) {
            rdData = parseIpV6Address(buffer, rdLength);
        } else if (type == 5) {
            rdData = ByteUtils.readDomainName(buffer);
        }
    }

    private String parseIpAddress(ByteBuffer buffer, int len) {
        String ip = "";
        for (int i=0;i<len;i++) {
            ip = ip + "." + Byte.toUnsignedInt(buffer.get());
        }
        return ip.substring(1);
    }
    private String parseIpV6Address(ByteBuffer buffer, int len) {
        String ip = "";

        for (int i=0;i<len;i+=2) {
            ip = ip + ":" + Integer.toHexString(Byte.toUnsignedInt(buffer.get()) )+ Integer.toHexString(Byte.toUnsignedInt(buffer.get()));
        }
        return ip.substring(1);
    }

}
