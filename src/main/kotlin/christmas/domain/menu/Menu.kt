package christmas.domain.menu

enum class Menu(private val menuName: String, private val price: Int, private val type: MenuType) {

    BUTTON_MUSHROOM_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55000, MenuType.MAIN),
    BARBEQUE_LIBS("바비큐립", 54000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),

    ZERO_COLA("제로콜라", 3000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuType.BEVERAGE);

    fun getName() = this.menuName
    fun getPrice() = this.price
    fun getMenuType() = this.type

    companion object {
        fun String.stringToMenu() = Menu.entries.find { this == it.menuName }
    }
}