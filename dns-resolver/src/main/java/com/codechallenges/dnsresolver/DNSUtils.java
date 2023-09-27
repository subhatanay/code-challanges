package com.codechallenges.dnsresolver;

import com.codechallenges.dnsresolver.dto.RecordTypeEnum;
import java.io.IOException;
import java.nio.ByteBuffer;

public class DNSUtils {

    public final static String DEFAULT_DNS_SERVER_IP = "8.8.8.8";
    public final static String DEFAULT_DNS_SERVER_PORT = "53";

     public static String getAddressByRecordType(RecordTypeEnum recordTypeEnum, ByteBuffer buffer, int len) throws IOException {
         if (recordTypeEnum == null) return "";
         switch (recordTypeEnum) {
             case A:
                 return parseIpAddress(buffer, len);
             case NS, CNAME:
                 return readDomainName(buffer);
             case AAAA:
                 return parseIpV6Address(buffer, len);
             default:
                 throw new IOException("Address Type not supported");
         }
     }

    private static String parseIpAddress(ByteBuffer buffer, int len) {
        String ip = "";
        for (int i=0;i<len;i++) {
            ip = ip + "." + Byte.toUnsignedInt(buffer.get());
        }
        return ip.substring(1);
    }
    private static String parseIpV6Address(ByteBuffer buffer, int len) {
        String ip = "";

        for (int i=0;i<len;i+=2) {
            ip = ip + ":" + Integer.toHexString(Byte.toUnsignedInt(buffer.get()) )+ Integer.toHexString(Byte.toUnsignedInt(buffer.get()));
        }
        return ip.substring(1);
    }

    public static String readDomainName(ByteBuffer buffer) throws IOException {
        int value = Byte.toUnsignedInt(buffer.get());
        if (value == 0) {
            return "";
        }

        if (ByteUtils.isCompressedByte(value)) {
            buffer.position(buffer.position()-1);
            int addr = ByteUtils.getPointerIfCompressedByte(buffer);
            int paddr = buffer.position();
            buffer.position(addr);
            String str =  readDomainName(buffer);
            buffer.position(paddr);
            return str;
        } else {
            return ByteUtils.readString(buffer, value) + "." + readDomainName(buffer);
        }

    }

}
