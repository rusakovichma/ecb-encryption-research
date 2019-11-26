package com.github.rusakovichma.encryption.ecb.util;

import com.github.rusakovichma.encryption.ecb.research.model.PlainFileEntity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {

    public static PlainFileEntity read(String path) {
        try {
            PlainFileEntity entity = new PlainFileEntity();

            FileInputStream fs = new FileInputStream(path);

            int HEADER_LENGTH = 14;  // 14 byte bmp header
            byte header[] = new byte[HEADER_LENGTH];
            fs.read(header, 0, HEADER_LENGTH);
            entity.setHeader(header);

            int INFO_HEADER_LENGTH = 40; // 40-byte bmp info header
            byte infoheader[] = new byte[INFO_HEADER_LENGTH];
            fs.read(infoheader, 0, INFO_HEADER_LENGTH);
            entity.setInfoHeader(infoheader);

            byte[] content = new byte[fs.available()];
            fs.read(content);
            entity.setContent(content);

            return entity;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static void save(PlainFileEntity entity, String fileToWrite) {
        try {
            FileOutputStream fos = new FileOutputStream(fileToWrite);
            fos.write(entity.getHeader());
            fos.write(entity.getInfoHeader());
            fos.write(entity.getContent());
            fos.flush();
            fos.close();
        } catch (
                Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
