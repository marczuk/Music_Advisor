package advisor.command;

import advisor.object.CategoriesItem;
import advisor.object.CategoriesWrapper;
import advisor.request.CategoriesRequest;
import java.util.stream.Collectors;

public class CategoriesCommand extends Command{

//    static {
//        name = "/help";
//    }
//    protected static String name = "/help";

    private static CategoriesWrapper lastCategoriesWrapper;

    private String title = "---CATEGORIES---";

    @Override
    public void handle() {

        //System.out.println(title);
        //System.out.println(message);

        CategoriesRequest request = new CategoriesRequest();

        lastCategoriesWrapper = request.execute();

        String result = lastCategoriesWrapper.getCategories().getItems()
                .stream()
                .map(CategoriesItem::toString)
                .collect(Collectors.joining(""));

        System.out.println(result);
    }

    public static String getCategoryIdFromLastCategoryWrapper(String name) {
        return lastCategoriesWrapper.getCategories().getItems()
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findAny().map(CategoriesItem::getId)
                .orElse(null);
    }
}
