import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.Scanner;

public class Main implements Commands {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CategoryManager CATEGORY_MANAGER = new CategoryManager();
    private static final ProductManager PRODUCT_MANAGER = new ProductManager();


    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = SCANNER.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryById();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProduct();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductById();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    printSumOfProducts();
                    break;
                case PRINT_MAX_OF_PRICE:
                    printMaxOfPrice();
                    break;
                case PRINT_MIN_OF_PRICE:
                    printMinOfPrice();
                    break;
                case PRINT_AVG_OF_PRICE:
                    printAvgOfPrice();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void printAvgOfPrice() {
        System.out.println("The average price products is " + PRODUCT_MANAGER.avgPrice());
    }

    private static void printMinOfPrice() {
        System.out.println("The min price products is " + PRODUCT_MANAGER.minPrice());
    }

    private static void printMaxOfPrice() {
        System.out.println("The max price products is " + PRODUCT_MANAGER.maxPrice());
    }

    private static void printSumOfProducts() {
        System.out.println("The sum of products are " + PRODUCT_MANAGER.sum());
    }

    private static void deleteProductById() {
        for (Object obj : PRODUCT_MANAGER.getAll()) {
            System.out.println(obj);
        }
        System.out.println("Please choose product id");
        int id = Integer.parseInt(SCANNER.nextLine());
        if (PRODUCT_MANAGER.getById(id) != null) {
            PRODUCT_MANAGER.deleteById(id);
            System.out.println("Product deleted!");
        } else System.out.println("Product does not exists");
    }

    private static void editProduct() {
        for (Object obj : PRODUCT_MANAGER.getAll()) {
            System.out.println(obj);
        }
        System.out.println("Please choose product id");
        int id = Integer.parseInt(SCANNER.nextLine());
        if (PRODUCT_MANAGER.getById(id) != null) {
            System.out.println("Please input new name,description,price,quantity,category_id");
            String[] productNewData = SCANNER.nextLine().split(",");
            PRODUCT_MANAGER.upDate(id, productNewData);
            System.out.println("Product was edited");
        } else System.out.println("Product does not exist");

    }

    private static void addProduct() {
        for (Object obj : CATEGORY_MANAGER.getAll()) {
            System.out.println(obj);
        }
        System.out.println("choose category id");
        int category_id = Integer.parseInt(SCANNER.nextLine());
        if (CATEGORY_MANAGER.getById(category_id) != null) {
            System.out.println("Please input name,description,price,quantity");
            Product product = new Product();
            String[] productData = SCANNER.nextLine().split(",");
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Integer.parseInt(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            product.setCategory(CATEGORY_MANAGER.getById(category_id));
            PRODUCT_MANAGER.add(product);
            System.out.println("Product was added");
        } else System.out.println("category does not exist");
    }

    private static void deleteCategoryById() {
        if (CATEGORY_MANAGER.getAll().isEmpty()) {
            System.out.println("There is not any categories");
            return;
        }
        for (Object obj : CATEGORY_MANAGER.getAll()) {
            System.out.println(obj);
        }
        System.out.println("Please choose Id");
        int id = Integer.parseInt(SCANNER.nextLine());
        if (CATEGORY_MANAGER.getById(id) == null) {
            System.out.println("Company does not exists");
            return;
        }
        CATEGORY_MANAGER.deleteById(id);
        System.out.println("Category was deleted.");
    }

    private static void editCategoryById() {
        for (Object obj : CATEGORY_MANAGER.getAll()) {
            System.out.println(obj);
        }
        System.out.println("Please choose Id");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Please input new name");
        String newName = SCANNER.nextLine();
        CATEGORY_MANAGER.upDate(id, newName);
        System.out.println("Category was updated.");
    }

    private static void addCategory() {
        System.out.println("Please input category name");
        String name = SCANNER.nextLine();
        Category category = new Category();
        category.setName(name);
        CATEGORY_MANAGER.add(category);
        System.out.println("Category was added.");
    }
}
