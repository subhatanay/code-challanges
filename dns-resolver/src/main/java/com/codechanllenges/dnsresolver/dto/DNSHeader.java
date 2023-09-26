package com.codechanllenges.dnsresolver.dto;

import com.codechanllenges.dnsresolver.ByteUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class DNSHeader implements ByteString {
    private short id;
    private short flags;
    private short questionCount;
    private short answerCount;
    private short authorityCount;
    private short additionalCount;

    public DNSHeader(ByteBuffer buffer) throws IOException {
        id = buffer.getShort();
        flags = buffer.getShort();
        questionCount = buffer.getShort();
        answerCount = buffer.getShort();
        authorityCount = buffer.getShort();
        additionalCount = buffer.getShort();
    }

    public static DNSHeader buildHeader() {
        return DNSHeader.builder()
//                .id((short) (Math.abs( new Random().nextInt(Short.MAX_VALUE-1) ) % Short.MAX_VALUE))
                .id((short) 100)
//                .flags((short) (1 << 8))
                .flags((short) 0)
                .questionCount((short) 1)
                .additionalCount((short) 0)
                .authorityCount((short) 0)
                .build();
    }

    public String toHexByteString() {
        String idHex = paddedHex(Long.toHexString(Short.toUnsignedInt(id)), Short.class);
        String flagsHex = paddedHex(Long.toHexString(Short.toUnsignedInt(flags)), Short.class);
        String questionCountHex = paddedHex(Long.toHexString(Short.toUnsignedInt(questionCount)), Short.class);
        String answerCountHex = paddedHex(Long.toHexString(Short.toUnsignedInt(answerCount)), Short.class);
        String authorityCountHex = paddedHex(Long.toHexString(Short.toUnsignedInt(authorityCount)), Short.class);
        String additionalCountHex = paddedHex(Long.toHexString(Short.toUnsignedInt(additionalCount)), Short.class);

        StringBuilder builder = new StringBuilder("");
        builder.append(idHex).append(flagsHex).append(questionCountHex).append(answerCountHex).append(authorityCountHex).append(additionalCountHex);

        return builder.toString();
    }

    @Override
    public byte[] getBytes() {
        StringBuilder  stringBuilder = new StringBuilder("");
        stringBuilder.append(ByteUtils.shortToByteString(id));
        stringBuilder.append(ByteUtils.shortToByteString(flags));
        stringBuilder.append(ByteUtils.shortToByteString(questionCount));
        stringBuilder.append(ByteUtils.shortToByteString(answerCount));
        stringBuilder.append(ByteUtils.shortToByteString(authorityCount));
        stringBuilder.append(ByteUtils.shortToByteString(additionalCount));

        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

}
