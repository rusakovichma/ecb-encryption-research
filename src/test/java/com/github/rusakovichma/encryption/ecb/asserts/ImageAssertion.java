package com.github.rusakovichma.encryption.ecb.asserts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;

import javax.imageio.ImageIO;

public class ImageAssertion extends Assertions
{

    // Assert existence and dimension of an image
    public static void assertImageSize(int expectedWidth, int expectedHeight, String imagePath)
    {
        BufferedImage img = getBufferedImage(imagePath);
        if (expectedWidth!=img.getWidth() || expectedHeight!=img.getHeight())
        {
            fail("Expected: <"+expectedWidth+", "+expectedHeight+"> but was <"+img.getWidth()+", "+img.getHeight()+"> (" + imagePath + ")");
        }
    }

    // Assert existence and height of an image
    public static void assertImageHeight(int height, String imagePath)
    {
        BufferedImage img = getBufferedImage(imagePath);
        if (height!=img.getHeight())
        {
            fail("Expected: <" + height + "> but was: <" + img.getHeight() + "> (" + imagePath + ")");
        }
    }


    // Assert existence and width of an image
    public static void assertImageWidth(int width, String imagePath)
    {
        BufferedImage img = getBufferedImage(imagePath);
        if (width!=img.getWidth())
        {
            fail("Expected: <" + width + "> but was: <" + img.getWidth() + "> (" + imagePath + ")");
        }
    }

    // Assert existence of an image
    public static void assertImageExists(boolean exists, String imagePath)
    {
        boolean result = getFile(imagePath).exists();
        assertEquals(exists, result);
    }

    public static String getFullPath(String imagePath)
    {
        return imagePath;
    }

    private static String getFilePath(String imagePath)
    {
        return imagePath;
    }

    private static File getFile(String imagePath)
    {
        String fullPath = getFullPath(imagePath);
        return new File(getFilePath(fullPath));

    }

    private static BufferedImage getBufferedImage(String imagePath)
    {
        File file = getFile(imagePath);
        if (!file.exists())
        {
            fail("File " + imagePath + " does not exist");
            return null;
        }
        try
        {
            BufferedImage img = ImageIO.read(file);
            return img;
        }
        catch (IOException e)
        {
            fail("Exception Occured. " + e.getMessage());
            return null;
        }

    }
}
