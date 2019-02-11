import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {
    private ItemController itemController = new ItemController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(itemController.findById(Long.parseLong(req.getParameter("id"))));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = new Item();
        item.setName(req.getParameter("name"));
        item.setDateCreated(new Date());
        item.setLastUpdatedDate( new Date());
        item.setDescription(req.getParameter("description"));
        resp.getWriter().println(itemController.save(item));
         req.getReader().readLine();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = itemController.findById(Long.parseLong(req.getParameter("id"))).get(0);
        item.setName(req.getParameter("name"));
        item.setLastUpdatedDate( new Date());
        item.setDescription(req.getParameter("description"));
        resp.getWriter().println(itemController.update(item));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = itemController.findById(Long.parseLong(req.getParameter("id"))).get(0);
        itemController.delete(item);
        resp.getWriter().println("Item with id = " + req.getParameter("id") + " was deleted");
    }
}
