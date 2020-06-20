package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Colors extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        //1 image
        WritableImage greenImage = new WritableImage(120, 100);
        PixelWriter pw1 = greenImage.getPixelWriter();
        for (int x = 0; x < 120; x++)
            for (int y = 0; y < 100; y++)
                pw1.setColor(x, y, Color.GREEN);
        ImageView first = new ImageView(greenImage);
        first.setFitHeight(100);
        first.setFitWidth(120);

        //2 image
        WritableImage rgbZeroXY = new WritableImage(120, 100);
        PixelWriter pw2 = rgbZeroXY.getPixelWriter();
        for (int x = 0; x < 120; x++)
            for (int y = 0; y < 100; y++)
                pw2.setColor(x, y, Color.rgb(0, x, y));
        ImageView second = new ImageView(rgbZeroXY);
        second.setFitHeight(100);
        second.setFitWidth(120);

        //3 image
        WritableImage rgbXXY = new WritableImage(120, 100);
        PixelWriter pw3 = rgbXXY.getPixelWriter();
        for (int x = 0; x < 120; x++)
            for (int y = 0; y < 100; y++)
                pw3.setColor(x, y, Color.rgb(x, x, y));
        ImageView third = new ImageView(rgbXXY);
        third.setFitHeight(100);
        third.setFitWidth(120);

        //4 image
        WritableImage hsbXY1 = new WritableImage(360, 100);
        PixelWriter pw4 = hsbXY1.getPixelWriter();
        for (int x = 0; x < 360; x++)
            for (int y = 0; y < 100; y++) {
                double y1 = y;
                pw4.setColor(x, y, Color.hsb(x, y1 / 99, 1));
            }
        ImageView fourth = new ImageView(hsbXY1);
        fourth.setFitHeight(100);
        fourth.setFitWidth(360);

        //5 image
        WritableImage lch80YX = new WritableImage(360, 140);
        PixelWriter pw5 = lch80YX.getPixelWriter();
        for (int x = 0; x < 360; x++)
            for (int y = 0; y < 140; y++)
                pw5.setColor(x, y, colorFromLCH(80, y, x));
        ImageView fifth = new ImageView(lch80YX);
        fifth.setFitHeight(140);
        fifth.setFitWidth(360);

        //6 image
        Image pic = new Image("https://sun9-48.userapi.com/c854124/v854124719/2041f5/zL47BK7k1PM.jpg?ava=1");
        ImageView sixth = new ImageView(pic);
        sixth.setFitHeight(200);
        sixth.setFitWidth(200);

        //7 image
        PixelReader pr = pic.getPixelReader();
        WritableImage picWB = new WritableImage(200, 200);
        PixelWriter pw7 = picWB.getPixelWriter();
        for (int x = 0; x < 200; x++)
            for (int y = 0; y < 200; y++) {
                Color color = pr.getColor(x, y);
                double brightness = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
                pw7.setColor(x, y, Color.hsb(0, 0, brightness));
            }
        ImageView seventh = new ImageView(picWB);
        seventh.setFitHeight(200);
        seventh.setFitWidth(200);

        //1 block - 1,2,3 images
        HBox firstToThird = new HBox(first, second, third);
        firstToThird.setMargin(first, new Insets(6));
        firstToThird.setMargin(second, new Insets(6));
        firstToThird.setMargin(third, new Insets(6));

        //3 block - 6,7 images
        HBox sixthSeventh = new HBox(sixth, seventh);
        firstToThird.setMargin(sixth, new Insets(0));
        firstToThird.setMargin(seventh, new Insets(0));

        //whole window
        VBox root = new VBox(8, firstToThird, fourth, fifth, sixthSeventh);
        root.setMargin(fourth, new Insets(20));
        root.setMargin(fifth, new Insets(20));
        stage.setScene(new Scene(root, 400, 650));
        stage.setTitle("Colors!");
        stage.setResizable(false);
        stage.show();
    }

    public static Color colorFromLCH(double cieL, double cieC, double cieH) {
        // CIE-LCH -> CIE-Lab
        double cieHradians = cieH * Math.PI / 180;
        double ciea = Math.cos(cieHradians) * cieC;
        double cieb = Math.sin(cieHradians) * cieC;

        // CIE-Lab -> XYZ
        double varY = (cieL + 16) / 116;
        double varX = ciea / 500 + varY;
        double varZ = varY - cieb / 200;

        if (varY * varY * varY > 0.008856)
            varY = varY * varY * varY;
        else
            varY = (varY - 16. / 116) / 7.787;
        if (varX * varX * varX > 0.008856)
            varX = varX * varX * varX;
        else
            varX = (varX - 16. / 116) / 7.787;
        if (varZ * varZ * varZ > 0.008856)
            varZ = varZ * varZ * varZ;
        else
            varZ = (varZ - 16. / 116) / 7.787;

        double X = varX * 95.047;
        double Y = varY * 100;
        double Z = varZ * 108.883;

        //XYZ -> sRGB
        varX = X / 100;
        varY = Y / 100;
        varZ = Z / 100;

        double varR = varX * 3.2406 + varY * -1.5372 + varZ * -0.4986;
        double varG = varX * -0.9689 + varY * 1.8758 + varZ * 0.0415;
        double varB = varX * 0.0557 + varY * -0.2040 + varZ * 1.0570;

        if (varR > 0.0031308)
            varR = 1.055 * Math.pow(varR, 1 / 2.4) - 0.055;
        else
            varR = 12.92 * varR;
        if (varG > 0.0031308)
            varG = 1.055 * Math.pow(varG, 1 / 2.4) - 0.055;
        else
            varG = 12.92 * varG;
        if (varB > 0.0031308)
            varB = 1.055 * Math.pow(varB, 1 / 2.4) - 0.055;
        else
            varB = 12.92 * varB;

        if (varR < 0)
            varR = 0;
        if (varR > 1)
            varR = 1;
        if (varG < 0)
            varG = 0;
        if (varG > 1)
            varG = 1;
        if (varB < 0)
            varB = 0;
        if (varB > 1)
            varB = 1;

        return Color.color(varR, varG, varB);
    }
}
