package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.AttractionVO;

public class GetDataController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");	
		if(pageNo == null) pageNo="1";
		String tag = request.getParameter("search");
		
		ArrayList<AttractionVO> alist = TourDao.getInstance().getData(tag);
		boolean emptyFlag = false;
		String path = "relatedreview.do";
		
		if(!alist.isEmpty()) {
			emptyFlag = true;
			request.setAttribute("alist", alist);
		}
		
		else {
			String check = TourDao.getInstance().checkTag(tag);
			if(check.equals("location")) 
				path = "index.jsp"; // ���߿� location(v1) �������� �̵�
				
			else if(check.equals("city")) 
				path = "index.jsp"; // ���߿� city(v2) �������� �̵�
		}
		request.setAttribute("emptyFlag", emptyFlag);

		return new ModelAndView(path + "?pageNo="+pageNo+"&&tag="+tag);
	}
}
