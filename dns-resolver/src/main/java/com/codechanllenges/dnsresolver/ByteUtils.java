package com.codechanllenges.dnsresolver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteUtils {

    public static String shortToByteString(short s) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(s);
        return new String(buffer.array());
    }
    public static String intToByteString(byte s) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES);
        buffer.put(s);
        return new String(buffer.array());
    }

   public static String readString(ByteBuffer buff) throws IOException {
        int count  = buff.get();
        return  readString(buff, count);
   }

   public static String readString(ByteBuffer buff , int len) throws IOException {
       String str = "";
       for (int i=1;i<=len;i++) {
           str += (char) buff.get();
       }
       return str;
   }

   public static String readDomainName(ByteBuffer buffer) throws IOException {
        int value = Byte.toUnsignedInt(buffer.get());
        if (value == 0) {
            return "";
        }

        if (isCompressedByte(value)) {
            buffer.position(buffer.position()-1);
            int addr = getPointerIfCompressedByte(buffer);
            int paddr = buffer.position();
            buffer.position(addr);
            String str =  readDomainName(buffer);
            buffer.position(paddr);
            return str;
        } else {
            return readString(buffer, value) + "." + readDomainName(buffer);
        }

   }

   public static boolean isCompressedByte(int val) {
        return (val & (3<<6)) == (3<<6);
   }


   public static int getPointerIfCompressedByte(ByteBuffer buff) {
       int com1 = Byte.toUnsignedInt(buff.get());
       int com2 = Byte.toUnsignedInt(buff.get());

       int com = (com1 << 8) + com2;
       int pointer = com ^ (3<<14);
       return pointer;
   }


}
