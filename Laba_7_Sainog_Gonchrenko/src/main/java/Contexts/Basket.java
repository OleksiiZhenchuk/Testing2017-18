package Contexts;

import PageObjects.BasketPage;
import PageObjects.ContactsPage;

public class Basket {
    public static ContactsPage confirmOrder(BasketPage page){
        page.confirm_order();
        return new ContactsPage(page.driver);
    }
}
