import cn.hoj.travel.dao.CategoryDao;
import cn.hoj.travel.dao.impl.CategoryImpl;
import cn.hoj.travel.domain.Category;
import org.junit.Test;

import java.util.List;

public class Testt {
    @Test
    public void test1(){
        CategoryDao dao=new CategoryImpl();
        List<Category> all = dao.findAll();
        System.out.println(all);
    }
}
