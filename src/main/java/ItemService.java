import java.util.List;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public Item save(Item item) {
        return itemDAO.save(item);
    }

    public Item update(Item item) {
        return itemDAO.update(item);
    }

    public void delete(Item item) {
        itemDAO.delete(item);
    }

    public List<Item> findById(long id) {
        return itemDAO.findById(id);
    }

}
