package pageobject;

public enum UserMenuItems {

    CART("Корзина", "/cart"),
    FAVOR("Избранное", "/my/favorites"),
    ORDERS("Заказы", "/my/orderlist");

    public final String nameUserMenuItem;
    public String urlUserMenuItem;

    UserMenuItems(String nameUserMenuItem, String urlUserMenuItem){
        this.nameUserMenuItem = nameUserMenuItem;
        this.urlUserMenuItem = urlUserMenuItem;
    }
}
