import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ShareLaneTests {

    @Test
    public void fourDigitsZipCodeFailed() {
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("1234");
        $(By.cssSelector("[value=Continue]")).click();
        String errorMessage = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void wrongEmailTestFailed() {
        open("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        $(By.name("first_name")).sendKeys("Natasha");
        $(By.name("last_name")).sendKeys("Severin");
        $(By.name("email")).sendKeys("nata@123");
        $(By.name("password1")).sendKeys("123456");
        $(By.name("password2")).sendKeys("123456");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    @Test
    public void correctFieldsEmailCreated() {
        open("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        $(By.name("first_name")).sendKeys("Natasha");
        $(By.name("last_name")).sendKeys("Severin");
        $(By.name("email")).sendKeys("n.severin@gmail.com");
        $(By.name("password1")).sendKeys("123456");
        $(By.name("password2")).sendKeys("123456");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".confirmation_message")).getText();
        Assert.assertEquals(result, "Account is created!");
    }

    @Test
    public void incorrectPasswordsTestFailed() {
        open("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        $(By.name("first_name")).sendKeys("Natasha");
        $(By.name("last_name")).sendKeys("Severin");
        $(By.name("email")).sendKeys("n.severin@gmail.com");
        $(By.name("password1")).sendKeys("1234567");
        $(By.name("password2")).sendKeys("123456");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page");
    }
}
