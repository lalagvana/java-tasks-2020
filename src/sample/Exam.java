package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class PGMImage {
    private int[][] pixelColor;

    public PGMImage(int width, int height) {
        this.pixelColor = new int[height][width];
    }

    public void setPixel(int x, int y, int color) {
        this.pixelColor[x][y] = color;
    }

    public int getWidth() {
        return this.pixelColor[0].length;
    }

    public int getHeight() {
        return this.pixelColor.length;
    }

    public void saveTo(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename, false);
        String text = "P2\n" + this.getWidth() + " " + this.getHeight() + "\n255\n";
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (i == this.getHeight() - 1 && j == this.getWidth() - 1)
                    text += this.pixelColor[i][j];
                else
                    text += this.pixelColor[i][j] + " ";
            }
        }
        writer.write(text);
        writer.flush();
    }
}

public class Exam {
    public static void main(String[] args) throws IOException {
        int min = 0;
        int max = 255;
        int diff = max - min;
        Random random = new Random();

        PGMImage randomImage = new PGMImage(80, 60);
        for (int i = 0; i < randomImage.getHeight(); i++)
            for (int j = 0; j < randomImage.getWidth(); j++) {
                randomImage.setPixel(i, j, random.nextInt(diff + 1));
            }
        randomImage.saveTo("randomImage.pgm");

        PGMImage gradientImage = new PGMImage(80, 60);
        for (int x = 0; x < gradientImage.getHeight(); x++)
            for (int y = 0; y < gradientImage.getWidth(); y++) {
                gradientImage.setPixel(x, y, (x + y) % 256);
            }
        gradientImage.saveTo("gradientImage.pgm");
    }

}
