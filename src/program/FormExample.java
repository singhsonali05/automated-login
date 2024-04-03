package program;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class FormExample extends JFrame implements Runnable{
    private static final long serialVersionUID = 1L;
    private String url;
    private String username;
    private String password;

    public FormExample(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

   
    private JTextField urlField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    @Override
    public void run() {
    }
    private FormExample() {
        setTitle("Login Form");
        setSize(300, 200);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel urlLabel = new JLabel("URL:");
        urlField = new JTextField();

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        submitButton = new JButton("Submit");
    
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            
          }
        	
    });
    
    
        add(urlLabel);
        add(urlField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); 
        add(submitButton);

        setVisible(true);
    
    }
  

    public static void main(String[] args) {
        new FormExample();
        new FormExample();
        new FormExample();
        Thread t1 = new Thread(new FormExample());
      Thread t2 = new Thread(new FormExample());
      Thread t3 = new Thread(new FormExample());

      t1.start();
      t2.start();
      t3.start();
        

    }

    private void onSubmit() {
        String url = urlField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        username = "singhsonali0903@gmail.com";
        password = "Rani4154@";

        System.out.println("URL: " + url + ", Username: " + username + ", Password: " + password);
        new FormSubmitter(url, username, password).run();


    }

    private static class FormSubmitter implements Runnable {
        private String url;
        private String username;
        private String password;

        public FormSubmitter(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        @Override
        public void run() {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Sonali\\Downloads\\geckodriver-v0.34.0-win64\\geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            driver.get("https://www." + url + ".com");

            ArrayList<WebElement> totalTextboxes = (ArrayList<WebElement>) driver.findElements(By.xpath("//input[@type='text']"));
            for (WebElement webElement : totalTextboxes) {
                String textElement = webElement.getAttribute("id");
                if (textElement.toLowerCase().contains("username")
                        || textElement.toLowerCase().contains("email")
                        || textElement.toLowerCase().contains("key")) {
                    webElement.sendKeys(username);
                }
            
            }
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            driver.quit();
            }
        }
  }
    
    


	
