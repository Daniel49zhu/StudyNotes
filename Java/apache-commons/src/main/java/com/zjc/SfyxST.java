
package com.zjc;

public enum SfyxST {
    INVALID(0, "INVALID"), VALID(1, "VALID"), TEMP(2, "TEMP");

    private int id;
    private String desc;

    SfyxST(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }


    public static SfyxST get(String name) {
        for (SfyxST sfyxST : SfyxST.values()) {
            if (sfyxST.desc.equals(name)) {
                return sfyxST;
            }
        }
        return null;
    }

    public static SfyxST get(int id) {
        for (SfyxST sfyxST : SfyxST.values()) {
            if (sfyxST.id == id) {
                return sfyxST;
            }
        }
        return null;
    }
}
