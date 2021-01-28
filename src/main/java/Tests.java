import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

    public class Tests {
    @Test
    public void fourDigitsZipCodeFailed(){
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("1234");
        $(By.cssSelector("[value=Continue]")).click();
        String errorMessage = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits");
    }
}
