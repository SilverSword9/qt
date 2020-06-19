package cn.hoj.travel.dao.impl;

import cn.hoj.travel.dao.RouteDao;
import cn.hoj.travel.domain.Route;
import cn.hoj.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询当前数据类型的条数
     * @param cid
     * @return
     */
    @Override
    public int findTotalCount(int cid) {
        String sql="select count(*) from tab_route where cid=?";
        return template.queryForObject(sql,Integer.class,cid);
    }

    @Override
    public List<Route> findByPage(int cid, int star, int pageSize) {
        String sql="select * from tab_route where cid=? limit ?,? ";
        List<Route> list;
        try {
            list = template.query(sql,new BeanPropertyRowMapper<Route>(Route.class), cid, star, pageSize);
        }catch (Exception e){
            System.out.println(e);
            list=null;
        }

        return list;
    }
}
