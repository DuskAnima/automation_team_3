package cl.kibernumacademy.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Snapshots {
    public static void tomarSnapshot(WebDriver driver, String nombre) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("target/screenshots"));
            String fileName = "target/screenshots/" + nombre + ".png";
            Files.copy(screenshot.toPath(), Paths.get(fileName), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Snapshot guardado en: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al tomar snapshot: " + e.getMessage());
        }
    }
}