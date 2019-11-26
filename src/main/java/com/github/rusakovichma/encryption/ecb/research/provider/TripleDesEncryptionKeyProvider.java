package com.github.rusakovichma.encryption.ecb.research.provider;

import javax.crypto.KeyGenerator;
import java.security.Key;

public class TripleDesEncryptionKeyProvider implements EncryptionKeyProvider {

    private static final String ALG = "DESede";

    @Override
    public byte[] getEncryptionKey() {
        try {
            Key key = KeyGenerator.getInstance(ALG).generateKey();
            return key.getEncoded();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
