package Websites_Tests;





        import Excel_Lib.Xls_Reader;
        import com.assertthat.selenium_shutterbug.core.Capture;
        import com.assertthat.selenium_shutterbug.core.Shutterbug;
        import Excel_Lib.Xls_Reader;
        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.apache.commons.io.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import ru.yandex.qatools.ashot.AShot;
        import ru.yandex.qatools.ashot.Screenshot;

        import java.io.File;
        import java.io.IOException;
        import java.net.URI;
        import java.net.URISyntaxException;

public class WebPagesTest {
    static WebDriver driver;
    private static Object ScrollStrategy;

    public static void main(String[] args) throws IOException, URISyntaxException {

        Xls_Reader reader = new Xls_Reader("src/main/java/Excel_Lib/picapagesforscript.xlsx");
        String sheetname_ = "ProdURLS";


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        int rowCount = reader.getRowCount(sheetname_);
        System.out.println("total rows: " + rowCount);

        for (int rownum = 2; rownum <= rowCount; rownum++) {

            String urls = reader.getCellData(sheetname_, "URLs", rownum);
            System.out.println("url number" + rownum + urls);

            driver.manage().window().maximize();
            driver.get(urls);
            String s = reader.getCellData(sheetname_, "URLs", rownum);
            URI uri = new URI(s);
            String path = uri.getPath();
            String strNew = path.replace("http://", " ").replace("/", " ");
            System.out.println(strNew);
            Shutterbug.shootPage(driver, Capture.FULL, 500, true).withName(strNew).save("src/main/resources/Fullscreenshotofwebpage");

        }

        System.out.println(reader.getCellData(sheetname_, "URLs", rowCount));


    }
}