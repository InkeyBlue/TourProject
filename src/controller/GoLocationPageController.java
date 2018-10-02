package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.FestivalVO;

public class GoLocationPageController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pathConstant = request.getParameter("location").substring(request.getParameter("location").indexOf("-")+1);
		System.out.println(pathConstant);
		String location = getLocation(pathConstant);
		ArrayList<FestivalVO> flist = TourDao.getInstance().getFestivalInfo(location);
		ArrayList<String> clist = TourDao.getInstance().getCities(location);
		request.setAttribute("clist", clist);
		request.setAttribute("flist", flist);
		return new ModelAndView("v1.jsp?location="+location);
	}
	
	public String getLocation(String pathConstant) {
		switch(pathConstant) {
		case "0": return "���ֵ�";
		case "1": return "��󳲵�";
		case "2": return "���ϵ�";
		case "3": return "���󳲵�";
		case "4": return "����ϵ�";
		case "5": return "��û����";
		case "6": return "��û�ϵ�";
		case "7": return "������";
		case "8": return "��⵵";
		case "9": return "���";
		case "10": return "����";
		case "11": return "����";
		case "12": return "��õ";
		case "13": return "�뱸";
		case "14": return "�λ�";
		case "15": return "����";
		}
		return null;
	}
}
