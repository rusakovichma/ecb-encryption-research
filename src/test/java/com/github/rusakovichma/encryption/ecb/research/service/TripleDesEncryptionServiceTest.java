package com.github.rusakovichma.encryption.ecb.research.service;

import com.github.rusakovichma.encryption.ecb.research.model.PlainFileEntity;
import com.github.rusakovichma.encryption.ecb.research.provider.EncryptionKeyProvider;
import com.github.rusakovichma.encryption.ecb.research.provider.TripleDesEncryptionKeyProvider;
import com.github.rusakovichma.encryption.ecb.util.FileUtil;
import org.junit.jupiter.api.Test;

import static com.github.rusakovichma.encryption.ecb.asserts.ImageAssertion.assertImageExists;

class TripleDesEncryptionServiceTest {

    private static final String RESOURCES_FOLDER = "src\\test\\resources\\";

    @Test
    void ecbEncryptionTest() {
        //Encryption provider initialize
        EncryptionKeyProvider keyProvider = new TripleDesEncryptionKeyProvider();
        EncryptionService encryptionService = new TripleDesEncryptionService(keyProvider);

        //read the image
        PlainFileEntity secretImage = FileUtil.read(RESOURCES_FOLDER + "secret1.bmp");
        //encrypt the image
        byte[] encryptedImage = encryptionService.encrypt(secretImage.getContent());
        //set image content
        secretImage.setContent(encryptedImage);

        //Encrypted Image for Analysis
        final String encryptedSecretPath = RESOURCES_FOLDER + "secret1_encrypted.bmp";
        //save encrypted image
        FileUtil.save(secretImage, encryptedSecretPath);
        assertImageExists(true, encryptedSecretPath);
    }
}