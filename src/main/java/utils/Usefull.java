package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by winch on 15.04.16.
 */
public class Usefull {

    public static String pseudoCssGetter(WebDriver driver, String cssSelector, String attribute) throws InterruptedException {

//        String script = "return window.getComputedStyle(document.querySelector('.wl-navigation .bt-menu'),':before').getPropertyValue('background-color')";
        String script = "return window.getComputedStyle(document.querySelector('" + cssSelector + "').getPropertyValue('" + attribute + "')";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String content = (String) js.executeScript(script);
//        System.out.println(content);
        String pseudoElementColor = rgbToHexJS(content);

        return pseudoElementColor;


    }

    public static String rgbToHex(String rgb) {

        String[] numbers = rgb.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());

        String hex = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
        return hex;
    }

    public static String rgbToHexJS(String rgb) {

        String[] numbers = rgb.replace("rgb(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());

        String hex = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
        return hex;
    }

    /**
     * page text validation
     */
    public void labelValidation(String fileLocation, int debug) throws InterruptedException, IOException, URISyntaxException {

        String text = null; //= driver.findElement(By.xpath("//html")).getText();

        if (debug == 1) {
            System.out.println(text);
        }

        String fileLine = null; //file lines

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(classloader.getResource(fileLocation).getFile());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        String[] line = text.split("\n"); //array of page parced strings
        int j = line.length; //length of the array

        int fileLinesCount = 0; //counter of file lines
        int fileCounter = 0; //counter of matches

        while ((fileLine = br.readLine()) != null) {
            fileLinesCount++;

            int elementMatches = 0;

            for (int i = 0; i < j; i++) {
                if (line[i].equals(fileLine)) {

                    if (debug == 1) {
                        System.out.println("file text: " + fileLine + "  site text: " + line[i]);
                    }

                    elementMatches++;
                }
            }

            if (elementMatches == 0) {
                System.out.println("alarm " + fileLine);
            }
            if (elementMatches >= 1) {
                fileCounter++;
            }
        }

        if (debug == 1) {
            System.out.println(fileLinesCount);
            System.out.println(fileCounter);
        }
//        assertTrue (fileCounter!=fileLinesCount);
        assert Integer.valueOf(fileCounter).equals(Integer.valueOf(fileLinesCount));
    }

    public static String email;

    public void emailNameGenerator(String emailPart1, String emailPart2) {

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("HHmmssddMMYYYY");
        String date = dateFormat.format(currentDate);

        email = emailPart1 + date + emailPart2;
        return;

        //System.out.println(email);
    }

    public int lineCount = 0;
    public int arrLine = 0;
    public String line = null;
    public String[][] fieldInputData;

    public void registrationTestData(int customArrayLines, int customArrayRows, String testDataLocation) throws IOException {

        fieldInputData = new String[customArrayLines][customArrayRows];
        String[] words;

        //read from file test data
        File file = new File(testDataLocation);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        while ((line = br.readLine()) != null) {
            //lineCount ++;
            words = line.split(";"); // use ; as separator

            fieldInputData[lineCount][0] = words[0];
            fieldInputData[lineCount][1] = words[1];
            fieldInputData[lineCount][2] = words[2];
            lineCount = lineCount + 1;
        }
    }
}
