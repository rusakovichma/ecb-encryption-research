package com.github.rusakovichma.encryption.ecb.research.service;

import com.github.rusakovichma.encryption.ecb.research.provider.EncryptionKeyProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class TripleDesEncryptionService implements EncryptionService {

    private final EncryptionKeyProvider encryptionKeyProvider;

    private final Cipher encipher; // Triple DES chiper
    private final Cipher decipher; // Triple DES chiper

    private final IvParameterSpec iv = new IvParameterSpec(new SecureRandom().generateSeed(8));

    public TripleDesEncryptionService(EncryptionKeyProvider encryptionKeyProvider) {
        this.encryptionKeyProvider = encryptionKeyProvider;

        byte[] keyBytes = this.encryptionKeyProvider.getEncryptionKey();
        SecretKeySpec key = new SecretKeySpec(keyBytes, "DESede");

        try {
            //DESede/ECB/PKCS5Padding is used be default
            encipher = Cipher.getInstance("DESede");
            encipher.init(Cipher.ENCRYPT_MODE, key);

            //DESede/ECB/PKCS5Padding is used be default
            decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public byte[] encrypt(byte[] value) {
        if (value == null) {
            return null;
        }

        try {
            return encipher.doFinal(value);
        } catch (Exception ex) {
            throw new SecurityException("Could not encrypt", ex);
        }
    }

    @Override
    public byte[] decrypt(byte[] value) {
        if (value == null) {
            return null;
        }

        try {
            return decipher.doFinal(value);
        } catch (Exception ex) {
            throw new SecurityException("Could not decrypt", ex);
        }
    }
}
