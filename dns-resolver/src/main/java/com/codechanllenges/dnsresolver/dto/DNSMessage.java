package com.codechanllenges.dnsresolver.dto;

import com.codechanllenges.dnsresolver.ByteUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@AllArgsConstructor
@Data
public class DNSMessage implements ByteString {
    
    private DNSHeader dnsHeader;
    
    private  List<DNSQuestion> questions;

    private  List<DNSAddressRecord> answers;

    private  List<DNSAddressRecord> authorities;

    private  List<DNSAddressRecord> additionals;



    public static DNSMessage  buildDNSMessage(String domain) {
        return DNSMessage.builder()
                .dnsHeader(DNSHeader.buildHeader())
                .questions(Arrays.asList(DNSQuestion.buildQuestion(domain)))
                .build();
    }

    public static DNSMessage fromResponseBuffer(ByteBuffer buffer) throws IOException {
        DNSHeader header = new DNSHeader(buffer);
        List<DNSQuestion> dnsQuestions = new ArrayList<>();
        List<DNSAddressRecord> answers = new ArrayList<>();
        List<DNSAddressRecord> authorities = new ArrayList<>();
        List<DNSAddressRecord> additional = new ArrayList<>();

        // Build DNS Question section
        for (int qd = 0; qd< header.getQuestionCount(); qd++) {
            dnsQuestions.add(new DNSQuestion(buffer));
        }

        // Build DNS Answer section
        for (int an = 0; an< header.getAnswerCount(); an++) {
            answers.add(new DNSAddressRecord(buffer));
        }

        // Build DNS Answer section
        for (int an = 0; an< header.getAuthorityCount(); an++) {
            authorities.add(new DNSAddressRecord(buffer));
        }

        // Build DNS Authorities section
        for (int an = 0; an< header.getAdditionalCount(); an++) {
            additional.add(new DNSAddressRecord(buffer));
        }

        return DNSMessage
                .builder()
                    .dnsHeader(header)
                    .questions(dnsQuestions)
                    .answers(answers)
                    .authorities(authorities)
                    .additionals(additional)
                .build();
    }

    public String toString() {
        return "Question :: \n\t" + questions.get(0).getQuestionName() + "\n\n" +
                "Answer ::  \n\t" +  answers.stream().map(DNSAddressRecord::getRdData).reduce((in, fun) -> in + "\n\t" + fun ).orElse("") + "\n\n" +
                "Authorities :: \n\t" +  authorities.stream().map(DNSAddressRecord::getRdData).reduce((in, fun) -> in + "\n\t" + fun ).orElse("") + "\n\n" +
                "Additionals :: \n\t" +  additionals.stream().map(DNSAddressRecord::getRdData).reduce((in, fun) -> in + "\n\t" + fun ).orElse("") + "\n\n";
    }






    @Override
    public String toHexByteString() {
        return dnsHeader.toHexByteString() + questions.stream().map(DNSQuestion::toHexByteString).reduce("", (s, s2) -> s + s2);
    }

    @Override
    public byte[] getBytes() {
        String headerBytes = new String(dnsHeader.getBytes());
        String questionBytes = new String(questions.get(0).getBytes());

        int len = headerBytes.length() + questionBytes.length();


        return (ByteUtils.shortToByteString((short) len) + headerBytes + questionBytes).getBytes(StandardCharsets.UTF_8);
    }
}
