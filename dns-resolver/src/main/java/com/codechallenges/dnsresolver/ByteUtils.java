package com.codechallenges.dnsresolver;

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
