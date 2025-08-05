package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SnapshotUtil {
    public static void takeASnapshot(String name, WebDriver driver) {
    try {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      Files.createDirectories(Paths.get("target/screenshots"));
      String fileName = "target/screenshots/" + name + ".png";
      Files.copy(screenshot.toPath(), Paths.get(fileName), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

    } catch(IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Error al tomar snapshot: " + e.getMessage());
    }
  }
}
