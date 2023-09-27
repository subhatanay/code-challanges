package com.codechallenges.dnsresolver.dto;

public enum RecordTypeEnum {
    A((short) 1),
    NS((short) 2),
    CNAME((short) 5),
    AAAA((short) 28);

    private short type;
    RecordTypeEnum(short type) {
        this.type = type;
    }

    public short getTypeValue() {
        return type;
    }

    public static RecordTypeEnum getTypeByValue(short val) {
        for (RecordTypeEnum record : RecordTypeEnum.values()) {
            if (record.getTypeValue() == val)
                return record;
        }
        return null;
    }
}
