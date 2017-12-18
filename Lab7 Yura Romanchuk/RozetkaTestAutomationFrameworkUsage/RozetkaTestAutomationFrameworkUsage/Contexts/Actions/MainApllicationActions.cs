using RozetkaTestAutomationFrameworkUsage.Pages;
using System.Text.RegularExpressions;
using RozetkaTestAutomationFrameworkUsage.Utils;
using OpenQA.Selenium;

namespace RozetkaTestAutomationFrameworkUsage.Contexts.Actions
{
    public static class MainApllicationActions
    {
        public static WineListPage ClickOnMore(this WineListPage page, Waiters wait)
        {
            wait.waitForClickableElement(By.Name(page.locLinkMore));
            page.LinkMore.Click();
            return page;
        }

        public static WineListPage ChooseCountry(this WineListPage page, string country, Waiters wait)
        {
            foreach (var element in page.listOfCountry)
            {
                if (Regex.IsMatch(element.getText(), country))
                {
                    element.Click();
                    break;
                }
            }
            return page;
        }

        public static GoodsItemPage ClickOnButtonBuy(GoodsItemPage page, Waiters wait)
        {
            wait.waitForVisibilityElement(By.ClassName(page.locBuy));
            page.Buy.Click();
            return page;
        }

        public static GoodsItemPage SubmitOfferButton(this GoodsItemPage page, Waiters wait)
        {
            wait.waitForVisibilityElement(By.Id(page.locSubOffer));
            page.SubmitOffer.Click();
            return page;
        }

        public static CheckOutPage ClickOnButtonContinue(CheckOutPage page)
        {
            while(page.Continue.Displayed && page.Continue.Enabled)
            {
                page.Continue.Click();
            }
            return page;
        }

        public static CheckOutPage ClickOnNotCall(this CheckOutPage page, Waiters wait)
        {
            page.NotCall.Click();     
            return page;
        }
    }
}
