package com.codechallenges.dnsresolver.dto;

import com.codechallenges.dnsresolver.ByteUtils;
import java.io.IOException;
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
    private FlagOptions flags;
    private short questionCount;
    private short answerCount;
    private short authorityCount;
    private short additionalCount;

    public DNSHeader(ByteBuffer buffer) throws IOException {
        id = buffer.getShort();
        flags = new FlagOptions(buffer.getShort());
        questionCount = buffer.getShort();
        answerCount = buffer.getShort();
        authorityCount = buffer.getShort();
        additionalCount = buffer.getShort();
    }

    public static DNSHeader buildHeaderWithRecursion() {
        return DNSHeader.builder()
                .id((short) (Math.abs( new Random().nextInt(1000) ) % Short.MAX_VALUE))
                .flags(new FlagOptions((short) (1 << 8)))
                .questionCount((short) 1)
                .additionalCount((short) 0)
                .authorityCount((short) 0)
                .build();
    }
    public static DNSHeader buildHeaderWithoutRecursion() {
        return DNSHeader.builder()
                .id((short) (Math.abs( new Random().nextInt(1000) ) % Short.MAX_VALUE))
                .flags(new FlagOptions((short) (0)))
                .questionCount((short) 1)
                .additionalCount((short) 0)
                .authorityCount((short) 0)
                .build();
    }

    public String toHexByteString() {
        String idHex = paddedHex(Long.toHexString(Short.toUnsignedInt(id)), Short.class);
        String flagsHex = paddedHex(Long.toHexString(Short.toUnsignedInt(flags.getFlag())), Short.class);
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
        stringBuilder.append(ByteUtils.shortToByteString(flags.getFlag()));
        stringBuilder.append(ByteUtils.shortToByteString(questionCount));
        stringBuilder.append(ByteUtils.shortToByteString(answerCount));
        stringBuilder.append(ByteUtils.shortToByteString(authorityCount));
        stringBuilder.append(ByteUtils.shortToByteString(additionalCount));

        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

}
