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
		boolean flag = false;
		String path = "relatedreview.do";
		if(!alist.isEmpty()) 
			emptyFlag = true;
		
		else {
			/*boolean tagExist = TourDao.getInstance().tagExist(tag);
			if(tagExist) {
				String check = TourDao.getInstance().checkTag(tag);
				if(check.equals("location"))
					path = "index.jsp"; // ���߿� location(v1) �������� �̵�
					
				else if(check.equals("city")) 
					path = "index.jsp"; // ���߿� city(v2) �������� �̵�
			}
			else {
				alist = TourDao.getInstance().checkSpot(tag);
				flag = true;
			}*/
			
			String check = TourDao.getInstance().checkTag(tag);
			if(check.equals("location"))
				path = "locationpage.do?location=path-"+tag; // ���߿� location(v1) �������� �̵�
				
			else if(check.equals("city")) 
				path = "getAttraction.do?city="+tag; // ���߿� city(v2) �������� �̵�
			
			else {
				boolean tagExist = TourDao.getInstance().tagExist(tag);
				if(!tagExist) {
					alist = TourDao.getInstance().checkSpot(tag);
					flag = true;
					if(alist.isEmpty()) {
						path = "noResult.jsp";
					}
				}
			}
		}
		
		request.setAttribute("alist", alist);
		request.setAttribute("emptyFlag", emptyFlag);
		
		return new ModelAndView(path + "?pageNo="+pageNo+"&&tag="+tag+"&&flag="+flag);
	}
}
