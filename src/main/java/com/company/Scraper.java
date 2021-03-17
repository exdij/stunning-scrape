package com.company;

import com.twmacinta.util.MD5;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    public ArrayList<ArrayList<String>> scanAntolini(){
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "C:\\utils\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        //options.setExperimentalOption("useAutomationExtension", false);
        //options.addArguments("--proxy-server='direct://'");
        // options.addArguments("--proxy-bypass-list=*");
        options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        options.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver driver = new ChromeDriver(options);
        Actions actions = new Actions(driver);
        WebDriverWait myWaitVar = new WebDriverWait(driver, 10);

        int pages = 0;
        driver.get("https://www.antonioli.eu/en/PL/men/section/sale");
        try {
            myWaitVar.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/section/section/article")));
        } catch (Exception e) {
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                String date;
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                long time = System.currentTimeMillis();
                time += 2*60*60*1000;
                date = formatter.format(time);
                FileUtils.copyFile(file, new File("C:\\Headless\\headless_screenshot" + date + ".png"));
            } catch (IOException ex) {
            }
            //no articles
            driver.quit();
            return null;
        }

        List<WebElement> lista_linki;
        List<WebElement> lista_ceny;
        List<WebElement> lista_nazwa;
        List<WebElement> lista_kategoria;
        //Cookie policy
        try {
            myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/a")));
            driver.findElement(By.xpath("/html/body/div[2]/a")).click();
        } catch (Exception e) {
            //no cookie bar
        }
        // Newsletter
        try {
            myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/span/*")));
            driver.findElement(By.xpath("/html/body/div[2]/span/*")).click();
        } catch (Exception e) {
            //no newsletter box
        }

        try {
            WebElement footer = driver.findElement(By.xpath("/html/body/section/section/div/nav[2]"));
            pages = Integer.parseInt(driver.findElement(By.xpath("/html/body/section/section/div/nav[2]/a[@class=\"last\"]")).getAttribute("innerText"));

        } catch (Exception e) {
            pages = 1;
        }
        for (int j = 1; j <= pages;  j++) {
            myWaitVar.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/section/section/article")));
            lista_linki = driver.findElements(By.xpath("/html/body/section/section/article/a[@itemprop=\"url\"]\n"));
            lista_ceny = driver.findElements(By.xpath("/html/body/section/section/article//figure/figcaption/div[@class=\"description_left\"]/div[@class=\"price_product\"]/div[@class=\"price\"]/span[@itemprop=\"price\"]"));
            lista_nazwa = driver.findElements(By.xpath("/html/body/section/section/article//figure/figcaption//div[contains(@class,\"brand-name\")]"));
            lista_kategoria = driver.findElements(By.xpath("/html/body/section/section/article//figure/figcaption//div[contains (@class,\"category\")]"));
            int i = 0;
            while(true) {
                ArrayList<String> item = new ArrayList<>();
                item.add(lista_nazwa.get(i).getAttribute("innerText").replace("\n",""));
                item.add(lista_ceny.get(i).getAttribute("content").replace("\n",""));
                item.add(lista_kategoria.get(i).getAttribute("innerText").replace("\n",""));
                item.add(lista_linki.get(i).getAttribute("href").replace("\n",""));
                items.add(item);
                i++;
                if (i == lista_linki.size() || i == lista_ceny.size() || i == lista_nazwa.size() || i == lista_kategoria.size()) {
                    break;
                }
            }
            driver.get("https://www.antonioli.eu/en/PL/men/section/sale?page=" + (j+1));

        }

        return items;
    }


    public String listToMD5(ArrayList<ArrayList<String>> list){
        MD5 md5 = new MD5();
        ArrayList<String> temp_list = new ArrayList<>();
        for (ArrayList<String> child:list){
            temp_list.add(String.join("",child));
        }
        String s = String.join("\n", temp_list);
        md5.Update(s);
        return md5.asHex();
    }

}
