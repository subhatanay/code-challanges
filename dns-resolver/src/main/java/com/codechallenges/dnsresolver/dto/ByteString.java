package com.codechallenges.dnsresolver.dto;

import java.util.stream.IntStream;

public interface ByteString {
    String toHexByteString();

    byte[] getBytes();

    default String paddedHex(String hexString, Class type) {
        int len = hexString.length();
        int rem = 0;
        if (type == Short.class) {
            rem = 4 - len;
        } else if (type == Byte.class) {
            rem = 2 - len;
        }
        for (int i = 0;i < rem;i++) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
