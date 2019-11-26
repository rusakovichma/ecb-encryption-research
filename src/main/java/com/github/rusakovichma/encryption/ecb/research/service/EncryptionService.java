package com.github.rusakovichma.encryption.ecb.research.service;

public interface EncryptionService {

    public byte[] encrypt(byte[] value);

    public byte[] decrypt(byte[] value);

}
