package query.review;

import model.CommonConstants;

public interface ReviewStringQuery {

	String GETCITIES = "SELECT city FROM location WHERE location=?";				// ��⵵ -> ����,�����
	String SEARCH_REVIEW_LIKE = "select likes from review where review_num=?";		// ���ƿ�� ����?
	String LIKE_ADD = "update review set likes=likes+1 where review_num=?";			// ���ƿ�+1
	String INSERT_REVIEW = "INSERT INTO review(review_num, location, city, title, content, date_writing, id)"
			+ "VALUES(review_seq.nextVal, ?, ?, ?, ?, sysdate, ?)";
	String CURRENT_NO = "SELECT review_seq.currVal FROM dual";
	String BEST_REVIEW_LOCATION_TAG = "select review_num, title, likes from (select * from review order by likes desc) where rownum<4"
			+ " AND review_num IN ((SELECT review_num FROM tag WHERE word=?)) AND location=?"; // v1���� ���� ���� ����Ʈ
	String SCRAP = "insert into scrap values(?,?)";									// ��ũ��
	String GET_ATTRACTION = "select spot_name,address,location,city,info,img from tourspot where city=?"; // city�� ������ ���� return
	String GET_ATTRACTION_IMG= "select spot_image from spot_image where spot_name=?";					  // ������ �̹��� ����
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency from festival where location=?" + 
			" AND (start_Date <=(SELECT SYSDATE + 7 FROM DUAL) OR SYSDATE BETWEEN start_Date AND end_Date)";// location�� �������� return �ȵǸ� start,end Date�� ''�߰�
	String CHECK_REVIEW = "select * from review where review_num = ?";				// �� ���� return
//	String SEARCH_BY_TAG = "SELECT review_num,location,city,title,content,date_writing,likes,id "
//			+ "FROM review WHERE review_num = all(select review_num from tag where word=?)";	// �ٽ�
	//String GET_SCRAP_LIST = "select * from review where review_num in (select review_num from scrap where id=?)";
	//String GET_MY_REVIEW = "select * from review where id=?";
//	String GET_BEST_REVIEWS_BY_TAG = "SELECT location,city,title,review_num,likes FROM (SELECT location,title.review_num,likes,city ORDER BY likes desc) "
//			+ "WHERE rownum<10 review_num IN (SELECT review_num FROM tag WHERE word=?)";			// index review list

	String DELETE_REVIEW = "delete from review where review_num=?";			//���� �� ���� ����
	String DELETE_SCRAP = "delete from scrap where review_num=?";			//��ũ�� ����
	String UPDATE_REVIEW = "update review set location=?, city=?, title=?, content=? where review_num=?";	//���� ������Ʈ
	String TOTAL_SCRAP_COUNT = "select count(-1) from scrap where id=?";	//��ũ�� ����
	String TOTAL_MY_REVIEW_COUNT = "select count(-1) from review where id=?";// ���� �� ���� ����
	
	String GET_REVIEW_TAGS = "select word from tag where review_num=?";							//���� tag�� return
	String GET_REVIEW_IMAGES = "SELECT review_image FROM review_image WHERE review_num = ?";	//���� img�� return
	String GET_REVIEW_COMMENTS = "SELECT id,comment FROM comment WHERE review_num = ?";			//���� comment�� return
	String GET_SCRAP_LIST="select * from review where review_num in"
			+ " (select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from scrap where id=? order by review_num desc)) where page=?)";		//��ũ�� ����Ʈ ����
	String GET_MY_REVIEW = "select review_num, title, date_writing, id from"
			+ " (select review_num, title, date_writing, id, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num, title, date_writing, id from review where id=? order by review_num desc)) where page=?";	//���� ����Ʈ ����
	String GET_RECENT_REVIEWS_BY_TAG = "SELECT review_num, title, location, city,id FROM (SELECT * FROM review ORDER BY review_num desc)" + 
			" WHERE review_num IN((SELECT review_num FROM tag WHERE word = ?)) AND rownum<";		//index review list
}










