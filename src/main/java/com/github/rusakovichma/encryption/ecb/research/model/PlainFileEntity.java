package com.github.rusakovichma.encryption.ecb.research.model;

import java.io.Serializable;

public class PlainFileEntity implements Serializable {

    private byte[] header;
    private byte[] infoHeader;
    private byte[] content;

    public PlainFileEntity(byte[] header, byte[] infoHeader, byte[] content) {
        this.header = header;
        this.infoHeader = infoHeader;
        this.content = content;
    }

    public PlainFileEntity() {
    }

    public byte[] getHeader() {
        return header;
    }

    public void setHeader(byte[] header) {
        this.header = header;
    }

    public byte[] getInfoHeader() {
        return infoHeader;
    }

    public void setInfoHeader(byte[] infoHeader) {
        this.infoHeader = infoHeader;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
