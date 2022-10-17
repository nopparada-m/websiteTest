import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class sendEmail
{
    public static void main (String[] args)
    {
        //properties
        String senderEmail = "email@proton.me";
        String recipientEmail = "email2@proton.me";
        String emailContent = "This is a sentence, and I have no idea what else to write.";

        //System.out.print("test"); //I do this just to see if it is my code that is messed up
        //Firefox driver setup
        //WARNING: add the appropriate web driver or the program will NOT work
        System.setProperty("webdriver.gecko.driver", "/home/nopparada-m/webdrivers/geckodriver-v0.32.0-linux64/geckodriver"); //change to your directory
        WebDriver driver = new FirefoxDriver(); //Or change to your browser of choice
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));


        ///////////////////SENDER////////////////////
        //login to proton page
        driver.get("https://account.proton.me/login");
        WebElement usernameInput = driver.findElement(By.name("Email or username"));
        //or WebElement usernameInput = driver.findElement(By.cssSelector("uname"));
        WebElement password = driver.findElement(By.name("Password"));
        //or WebElement password = driver.findElement(By.cssSelector("psw"));
        WebElement loginButton = driver.findElement(By.cssSelector("button"));

        usernameInput.sendKeys(senderEmail); //change to your email
        password.sendKeys("!!!abc123"); //change password according to the email
        password.submit();
        loginButton.click(); //clicks sign in
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

        //write a new message
        WebElement newMessage = driver.findElement(By.name("New message")); //click: new message
        WebElement from = driver.findElement(By.name("From")); //sender's address box
        WebElement to = driver.findElement(By.name("To")); //recipient's address box
        WebElement subject = driver.findElement(By.name("Subject")); //subject box
        WebElement emailText = driver.findElement(By.cssSelector("proton-editor-container")); //box for typing message
        WebElement sendButton = driver.findElement(By.name("Send")); //send button

        newMessage.click(); //opens "new message" box
        from.sendKeys(senderEmail); //sender address
        to.sendKeys(recipientEmail); //recipient address
        subject.sendKeys("Email Subject"); //sets email's subject
        emailText.sendKeys(emailContent); //inserts email content
        sendButton.click(); //send email
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

        //log out
        WebElement logOut = driver.findElement(By.id("dropdown-39")); //clicks the drop-down menu on the top right
        WebElement logoutButton = driver.findElement(By.name("Sign out")); //sign out button

        logOut.submit(); //press the drop-down menu
        logoutButton.click(); //click sign out


        ////////////////////RECIPIENT/////////////////////
        //recipient logs in
        usernameInput.sendKeys(recipientEmail); //recipient's email
        password.sendKeys("!!!abc123"); //change password according to the email
        password.submit();
        loginButton.click(); //clicks sign in
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

        //recipient checks email
        WebElement newEmail = driver.findElement(By.name("Inbox")); //find "Inbox"
        newEmail.submit(); //selects email in the inbox


        //end
        driver.quit();
    }
}
