package cn.hoj.travel.web.servlet;

import cn.hoj.travel.domain.PageBean;
import cn.hoj.travel.domain.Route;
import cn.hoj.travel.service.RouteService;
import cn.hoj.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    /**
     * 分页查询
     * @param request
     * @param response
     */
    public void pageQuery(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //获取参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //处理参数
        int cid=0;
        if(cidStr!=null&&cidStr.length()>0){
            cid=Integer.parseInt(cidStr);
        }
        int currentPage=1; //当前页默认为 1
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }
        int pageSize=5;     //每页显示条数，默认每页显示5条
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }
        //查询
        //调用service
        RouteService service=new RouteServiceImpl();
        PageBean<Route> pageBean = service.pageQuery(cid, pageSize, currentPage);

        //json化写回前端
        ObjectMapper mapper=new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(pageBean);
            response.setContentType("application/json;character:utf-8");    //response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void test(HttpServletRequest request,HttpServletResponse response){
        System.out.println("ttt");
    }
}
