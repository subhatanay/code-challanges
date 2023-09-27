package com.codechallenges.dnsresolver.dto;

import com.codechallenges.dnsresolver.DNSUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DNSAddressRecord {

    private String name;

    private RecordTypeEnum type;

    private short classType;

    private int ttl;

    private short rdLength;

    private String rdData;

    public DNSAddressRecord(ByteBuffer buffer) throws IOException {
        name = DNSUtils.readDomainName(buffer);

        type = RecordTypeEnum.getTypeByValue(buffer.getShort());
        classType = buffer.getShort();
        ttl = buffer.getInt();
        rdLength = buffer.getShort();

        rdData = DNSUtils.getAddressByRecordType(type, buffer, rdLength);
    }
}
