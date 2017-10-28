import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

waiting {
    timeout = 10
}

environments {
    defaultEnvironment {
        driver = {
            ChromeOptions options = new ChromeOptions()
            options.addArguments('headless')
            new ChromeDriver(options)
        }
    }
}
