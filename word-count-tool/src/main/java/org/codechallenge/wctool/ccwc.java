package org.codechallenge.wctool;


import org.codechallenge.wctool.readers.FileReader;
import org.codechallenge.wctool.readers.IReader;
import org.codechallenge.wctool.readers.InputLineReader;

public class ccwc {

    public static void main(String[] args) throws Exception {
        String[] arguments = parseArgument(args);
        IReader reader;

        if (arguments[1]!=null) {
            reader =  new FileReader(arguments[1]);
        } else {
            reader =  new InputLineReader();
        }
        String dataString = reader.read();
        CountFactory.getCountFactory(arguments[0]).forEach(iCount -> {
            System.out.print("    " + iCount.count(dataString));
        });
        System.out.println("  " + (arguments[1] !=null ? arguments[1] : ""));
    }

    private static String[] parseArgument(String[] args) {
        if (args.length > 2) throw new IllegalArgumentException("Maximum two argument needs to provided");

        String[] argsv = new String[2];

        if (args.length == 0) {
            argsv[0] = "";
            argsv[1] = null;
        }
        if (args.length == 1) {
            if (CountOptionEnum.getOptions().contains(args[0])) {
                argsv[0] = args[0];
                argsv[1] = null;
            } else {
                argsv[0] = "";
                argsv[1] = args[0];
            }
        } else if (args.length == 2) {
            if (CountOptionEnum.getOptions().contains(args[0])) {
                argsv[0] = args[0];
            } else {
                argsv[0] = null;
            }
            argsv[1] = args[1];
        }

        return argsv;
    }


}
