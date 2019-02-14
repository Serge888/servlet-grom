import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
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
        BufferedReader bufferedReader = req.getReader();
        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        Item item = new Item();
        try {
            JSONObject jsonObject = new JSONObject(sb.toString());
            item.setName(jsonObject.getString("name"));
            item.setDescription(jsonObject.getString("description"));
            item.setDateCreated(new Date());
            item.setLastUpdatedDate( new Date());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.getWriter().println(itemController.save(item));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = new Item();
        BufferedReader bufferedReader = req.getReader();
        String line = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        try {
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            item = itemController.findById(Long.parseLong(jsonObject.getString("id"))).get(0);
            item.setName(jsonObject.getString("name"));
            item.setDescription(jsonObject.getString("description"));
            item.setLastUpdatedDate( new Date());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.getWriter().println(itemController.update(item));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = itemController.findById(Long.parseLong(req.getParameter("id"))).get(0);
        itemController.delete(item);
        resp.getWriter().println("Item with id = " + req.getParameter("id") + " was deleted");
    }
}
