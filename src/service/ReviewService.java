package service;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PagingBean;
import model.dao.TourDao;
import model.vo.ReviewVO;
/*
 * �ַ� SELECT �� ����
 */
public class ReviewService {
	
	private TourDao dao;
	private static ReviewService service = new ReviewService();
	private ReviewService() {
		dao = TourDao.getInstance();
	}
	public static ReviewService getInstance() {
		return service;
	}
	
/*	public ListVO getPostingList(String pageNo) throws SQLException{
		int pn = 0;
		if(pageNo==null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		int totalContents = dao.totalReviewNumber();
		ArrayList<ReviewVO> list = dao.getReviewList(pn);
		PagingBean pb = new PagingBean(totalContents, pn);
		
		return new ListVO(list, pb);
	}*/
	
}
