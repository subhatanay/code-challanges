package com.codechallenges.dnsresolver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlagOptions {
    /*
                                    1  1  1  1  1  1
      0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |QR|   Opcode  |AA|TC|RD|RA|   Z    |   RCODE   |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
     0  0  0  0  0  0  0  0
     */
    private short flag ;


    public byte getQR() {
        return (byte)((flag & 0x8000) >> 15);
    }

    public byte getOpcode() {
        return (byte)((flag & 0x7800) >> 10);
    }

    public byte getAA() {
        return (byte)((flag & 0x0400) >> 10);
    }

    public byte getTC() {
        return (byte)((flag & 0x0200) >> 9);
    }

    public byte getRD() {
        return (byte)((flag & 0x0100) >> 8);
    }

    public byte getRA() {
        return (byte)((flag & 0x0080) >> 7);
    }

    public byte getZ() {
        return (byte)((flag & 0x0070) >> 4);
    }

    public byte getRCODE() {
        return (byte)((flag & 0x000F));
    }

    public String toString() {
        return "QR :: (0 - Request), (1 - Response) : " + getQR() + "\n" +
               "Opcode :: " + getOpcode() + "\n" +
                "Authoritative Answer :: " + getAA() + "\n" +
                "Truncated :: " + getTC() + "\n" +
                "Recursion Desired :: " + getRD() + "\n" +
                "Recursion Available :: " + getRA() + "\n" +
                "Response Code :: " + getRCODE() ;

    }




}
