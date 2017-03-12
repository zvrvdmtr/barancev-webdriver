package ru.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import java.util.NoSuchElementException;

/**
 * Created by Zhanna on 11.03.2017.
 * Тест для задания №10
 */
public class GoodsPage extends TestBase{
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public String colorRGB(String color){
        String text_color = null;
        String numbers[] = color.replace("rgba(", ""). replace(")", "").split(", ");
        int[] col = new int[3];
        int r = Integer.parseInt(numbers[0]);
        int g = Integer.parseInt(numbers[1]);
        int b = Integer.parseInt(numbers[2]);
        if (r == g && g == b){
            text_color = "GREY";
        }
        else if (g == b && r != g && r != b){
            text_color = "RED";
        }
        return text_color;
    }

    public Boolean compareDimensions(Dimension regular, Dimension campaign){
        if(regular.getHeight() < campaign.getHeight() && regular.getWidth() < campaign.getWidth()){
            return true;
        }
        return false;
    }

    @Test
    public void mainPage(){
        driver.get("http://localhost/litecart/en/");
        WebElement good = driver.findElement(By.xpath("//*[@id='box-campaigns']//li"));
        String name = good.findElement(By.cssSelector("div.name")).getText();
        String regular_price = good.findElement(By.cssSelector("s.regular-price")).getText();
        String color_regular = good.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        Assert.assertEquals("GREY", colorRGB(color_regular));
        String line_regular = good.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        Assert.assertEquals("line-through", line_regular);
        Dimension size_regular = good.findElement(By.cssSelector("s.regular-price")).getSize();
        String campaign_price = good.findElement(By.cssSelector("strong.campaign-price")).getText();
        String color_campaign = good.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        Assert.assertEquals("RED", colorRGB(color_campaign));
        String bold_campaign = good.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        Assert.assertEquals("bold", bold_campaign);
        Dimension size_campaign = good.findElement(By.cssSelector("strong.campaign-price")).getSize();
        Assert.assertTrue(compareDimensions(size_regular, size_campaign));
        good.click();
        isElementPresent(By.cssSelector("h1"));
        Assert.assertEquals(name, driver.findElement(By.cssSelector("h1")).getText());
        Assert.assertEquals(regular_price, driver.findElement(By.cssSelector("s.regular-price")).getText());
        Assert.assertEquals(campaign_price, driver.findElement(By.cssSelector("strong.campaign-price")).getText());
        Assert.assertEquals("GREY", colorRGB(driver.findElement(By.cssSelector("s.regular-price")).getCssValue("color")));
        Assert.assertEquals("RED", colorRGB(driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color")));
        Assert.assertEquals("line-through", driver.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration"));
        Assert.assertEquals("bold", driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight"));
        Assert.assertTrue(compareDimensions(driver.findElement(By.cssSelector("s.regular-price")).getSize(), driver.findElement(By.cssSelector("strong.campaign-price")).getSize()));
    }
}
