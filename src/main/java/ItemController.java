import java.util.List;

public class ItemController {
    private ItemService itemService = new ItemService();

    public Item save(Item item) {
        return itemService.save(item);
    }

    public Item update(Item item) {
        return itemService.update(item);
    }

    public void delete(Item item) {
        itemService.delete(item);
    }

    public List<Item> findById(long id) {
        return itemService.findById(id);
    }
}
