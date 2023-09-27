package com.codechallenges.dnsresolver.dto;

import com.codechallenges.dnsresolver.ByteUtils;
import com.codechallenges.dnsresolver.DNSUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DNSQuestion implements ByteString {

    private String questionName;

    private short questionType;

    private short questionClass;

    public DNSQuestion(ByteBuffer buffer) throws IOException {
        questionName = DNSUtils.readDomainName(buffer);
        questionType = buffer.getShort();
        questionClass = buffer.getShort();
    }

    public static DNSQuestion buildQuestion(String domainName) {
        return DNSQuestion.builder().questionName((domainName))
                .questionType((short) 1)
                .questionClass((short) 1)
                .build();
    }

    private String encodeDomainToHexString(String domain) {
        String[] subDomainList = domain.split("\\.");
        StringBuilder domainBuilder = new StringBuilder("");
        for(String dom : subDomainList) {
            domainBuilder.append(paddedHex(Integer.toHexString(dom.length()), Byte.class)).append(stringToHex(dom));
        }
        domainBuilder.append("00");
        return domainBuilder.toString();
    }
    private String encodeDomainToByteString(String domain) {
        String[] subDomainList = domain.split("\\.");
        StringBuilder domainBuilder = new StringBuilder("");
        for(String dom : subDomainList) {
            domainBuilder.append(ByteUtils.intToByteString((byte) dom.length())).append((dom));
        }
        domainBuilder.append(ByteUtils.intToByteString((byte) 0));
        return domainBuilder.toString();
    }

    private String stringToHex(String str) {
        String s = "";
        for (Byte byt : str.getBytes(StandardCharsets.UTF_8))  {
            s = s + (paddedHex(Integer.toHexString(Byte.toUnsignedInt(byt)), Byte.class));
        }
        return s;
    }

    @Override
    public String toHexByteString() {
       String encodedQuestion = encodeDomainToHexString(questionName);

       StringBuilder questionHexString = new StringBuilder(encodedQuestion);

       String questionTypeHex = paddedHex(Integer.toHexString(Short.toUnsignedInt(questionType)), Short.class);
       String questionClassHex = paddedHex(Integer.toHexString(Short.toUnsignedInt(questionClass)), Short.class);

       return questionHexString.append(questionTypeHex).append(questionClassHex).toString();
    }

    @Override
    public byte[] getBytes() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(encodeDomainToByteString(questionName)).append(ByteUtils.shortToByteString(questionType)).append(ByteUtils.shortToByteString(questionClass));
        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
