package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ExamTask3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private ImageView showPNGImage;

    public void start(Stage stage) {

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
        scanner.nextLine();
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextInt();
        double brightness;
        WritableImage wim = new WritableImage(width, height);
        PixelWriter pw = wim.getPixelWriter();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                brightness = (double) scanner.nextInt()/255;
                pw.setColor(y, x, Color.gray(brightness));
            }
        File fileA = new File(filename+".png");
        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
    }
}
