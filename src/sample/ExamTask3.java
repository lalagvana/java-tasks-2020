package sample;

import javafx.application.Application;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ExamTask3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private ImageView showPNGImage;

    public void start(Stage stage) throws IOException {

        Button chooseImage = new Button("Select PGM image");
        final FileChooser fileChooser = new FileChooser();
        Label imgPath = new Label();

        VBox root = new VBox(imgPath, chooseImage);
        root.setPadding(new Insets(10));
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("Save PGM image as PNG");
        stage.setResizable(false);
        stage.show();

        chooseImage.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                imgPath.setText(file.getAbsolutePath());
                try {
                    convertPGMtoPNG(file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image pic = new Image("file:" + file.getAbsolutePath() + ".png");
                showPNGImage = new ImageView(pic);
                root.getChildren().addAll(showPNGImage);
            }
        });

    }

    private void convertPGMtoPNG(String filename) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream(filename));
        System.out.println(scanner.nextLine());
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        System.out.println(scanner.nextInt());
   /*     int brightness;
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw = img.getPixelWriter();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                brightness = scanner.nextInt() / 255;
                pw.setColor(x, y, Color.gray(brightness));
            }
        assert false;
        ImageIO.write((RenderedImage) img, "PNG", new File(filename + ".png"));*/
    }
}
