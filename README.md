# Attacks on AES-ECB encryption


ECB encrypts identical plaintext blocks into identical ciphertext blocks, it does not hide data patterns well. 
In some senses, it doesn't provide serious message confidentiality, and it is not recommended for use in cryptographic protocols at all.

A striking example of the degree to which ECB can leave plaintext data patterns in the ciphertext can be seen when ECB mode is used 
to encrypt a bitmap image which uses large areas of uniform color. While the color of each individual pixel is encrypted, 
the overall image may still be discerned, as the pattern of identically colored pixels in the original remains in the encrypted version.

ECB mode can also make protocols without integrity protection even more susceptible to replay attacks, since each block gets decrypted in exactly the same way.

Code example
===============
Full example may be found here: https://github.com/rusakovichma/ecb-encryption-research/blob/master/src/test/java/com/github/rusakovichma/encryption/ecb/research/service/TripleDesEncryptionServiceTest.java

```JAVA
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
```

