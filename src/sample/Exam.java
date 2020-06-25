package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;

class PGMImage {
    private int[][] pixelColor;

    public PGMImage(int width, int height) {
        this.pixelColor = new int[width][height];
    }

    public void setPixel(int x, int y, int color) {
        this.pixelColor[x][y] = color;
    }

    public int getWidth() {
        return this.pixelColor.length;
    }

    public int getHeight() {
        return this.pixelColor[0].length;
    }

    public void saveTo(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename, false);
        String text = "P2\n" + this.getWidth() + " " + this.getHeight() + "\n255\n";
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight() - 1; j++) {
                text += this.pixelColor[i][j] + " ";
            }
            text += this.pixelColor[i][this.getHeight() - 1] + "\n";
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
        for (int i = 0; i < randomImage.getWidth(); i++)
            for (int j = 0; j < randomImage.getHeight(); j++) {
                randomImage.setPixel(i, j, random.nextInt(diff + 1));
            }
        randomImage.saveTo("randomImage.pgm");

        PGMImage gradientImage = new PGMImage(80, 60);
        for (int i = 0; i < gradientImage.getWidth(); i++)
            for (int j = 0; j < gradientImage.getHeight(); j++) {
                int col = (i + j) % 256;
                gradientImage.setPixel(i, j, col);
            }
        gradientImage.saveTo("gradientImage.pgm");
    }

}
