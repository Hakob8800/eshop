public interface Commands {
    static void printCommands() {
        System.out.println("Please choose command");
        System.out.println("Press " + EXIT + " for exit");
        System.out.println("Press " + ADD_CATEGORY + " for add category");
        System.out.println("Press " + EDIT_CATEGORY_BY_ID + " for edit category by id");
        System.out.println("Press " + DELETE_CATEGORY_BY_ID + " for delete category by id");
        System.out.println("Press " + ADD_PRODUCT + " for add product");
        System.out.println("Press " + EDIT_PRODUCT_BY_ID + " for edit product by id");
        System.out.println("Press " + DELETE_PRODUCT_BY_ID + " for delete product by id");
        System.out.println("Press " + PRINT_SUM_OF_PRODUCTS + " for print sum of products");
        System.out.println("Press " + PRINT_MAX_OF_PRICE + " for print Max of price product");
        System.out.println("Press " + PRINT_MIN_OF_PRICE + " for Print Min of price product");
        System.out.println("Press " + PRINT_AVG_OF_PRICE + " for Print Avg of price product");
    }

    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String EDIT_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_ID = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_MAX_OF_PRICE = "8";
    String PRINT_MIN_OF_PRICE = "9";
    String PRINT_AVG_OF_PRICE = "10";
}
