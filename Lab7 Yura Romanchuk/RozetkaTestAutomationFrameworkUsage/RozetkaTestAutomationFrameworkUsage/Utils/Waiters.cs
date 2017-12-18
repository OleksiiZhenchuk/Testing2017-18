using OpenQA.Selenium.Support.UI;
using RozetkaTestAutomationFrameworkUsage.Pages;
using OpenQA.Selenium;
using System;

namespace RozetkaTestAutomationFrameworkUsage.Utils
{
    public class Waiters : BasePage
    {
        public Waiters(IWebDriver driver) : base(driver)
        { }

        public void waitForClickableElement(By bylocator)
        {
            WebDriverWait wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(5));
            wait.Until(ExpectedConditions.ElementToBeClickable(bylocator));
        }

        public void waitForVisibilityElement(By bylocator)
        {
            WebDriverWait wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(5));
            wait.Until(ExpectedConditions.VisibilityOfAllElementsLocatedBy(bylocator));
        }
    }
}
