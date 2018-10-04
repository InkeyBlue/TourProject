package query.review;

import model.CommonConstants;
import model.vo.ReviewVO;

public interface ReviewStringQuery {

	String GETCITIES = "SELECT city FROM location WHERE location=?";
	String SEARCH_REVIEW_LIKE = "select likes from review where review_num=?";
	String LIKE_ADD = "update review set likes=likes+1 where review_num=?";
	String BEST_REVIEW_LOCATION_TAG = "select review_num, title, likes from (select * from review order by likes desc) where rownum<4"
			+ " AND review_num IN ((SELECT review_num FROM tag WHERE word=?)) AND location=?";
	String GET_REVIEW_TAGS = "select word from tag where review_num=?";
	String SCRAP = "insert into scrap values(?,?)";
	String GET_ATTRACTION = "select spot_name,address,location,city,info,img from tourspot where city=?";
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency from festival where location=?";
	String GET_IMAGE_LIST = "select img from tourspot where city=?";
	String CHECK_REVIEW = "select * from review where review_num = ?";
	String SEARCH_BY_TAG = "SELECT review_num,location,city,title,content,date_writing,likes,id "
			+ "FROM review WHERE review_num = all(select review_num from tag where word=?)";
	//String GET_SCRAP_LIST = "select * from review where review_num in (select review_num from scrap where id=?)";
	//String GET_MY_REVIEW = "select * from review where id=?";
	String GET_BEST_REVIEWS = "SELECT location,city,title,review_num,likes FROM (SELECT location,title.review_num,likes,city ORDER BY likes desc) "
			+ "WHERE rownum<10 review_num IN (SELECT review_num FROM tag WHERE tag=?)";

	String DELETE_REVIEW = "delete from review where review_num=?";
	String DELETE_SCRAP = "delete from scrap where review_num=?";
	String UPDATE_REVIEW = "update review set location=?, city=?, title=?, content=? where review_num=?";
	String TOTAL_SCRAP_COUNT = "select count(-1) from scrap where id=?";
	String TOTAL_MY_REVIEW_COUNT = "select count(-1) from review where id=?";
	
	String GET_REVIEW_IMAGES = "SELECT review_image FROM review_image WHERE review_num = ?";
	String GET_REVIEW_COMMENTS = "SELECT id,comment FROM comment WHERE review_num = ?";
	String GET_SCRAP_LIST="select * from review where review_num in"
			+ " (select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from scrap where id=? order by review_num desc)) where page=?)";
	String GET_MY_REVIEW = "select review_num, title, date_writing, id from"
			+ " (select review_num, title, date_writing, id, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num, title, date_writing, id from review where id=? order by review_num desc)) where page=?";
	String GET_RECENT_REVIEWS_BY_TAG = "SELECT review_num, title, location, city,id FROM (SELECT * FROM review ORDER BY review_num desc)" + 
			" WHERE review_num IN((SELECT review_num FROM tag WHERE word = ?)) AND rownum<";
	
	String RELATED_REVIEWS = "select * from review where review_num in"
			+ " (select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from tag where word=? order by review_num desc)) where page=?)";

	String GET_DATA = "select * from tourspot where spot_name ="
			+ "(select distinct word from tag where word=?)";
	
	String CHECK_SPOT = "select * from tourspot where spot_name=?;";
	
	String GET_REVIEW_BY_SEARCH = "select * from review where review_num in"
			+ " (select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from tag where word="
			+ "(select city from tourspot where spot_name=?) order by review_num desc)) where page=?)";

	String REVIEW_IMG = "SELECT review_image FROM review_image WHERE review_num=?";
}

/*
 * 1-1) .. GET_DATA (�ش� �±װ� tourspot���� �ƴ��� �˻��� ����..)
 * select * from tourspot where spot_name =
 * (select distinct word from tag where word='�ι��Ӹ�');
 */

/*
 * GET_DATA���� ���� ���� null�� �ƴ� ��
 * 
 * ===========================================================
 * 
 * 1-2), 3-1) .. RELATED_REVIEWS
 * select * from review where review_num in
 *  (select review_num from
 *  (select review_num, ceil(rownum/6) page from
 *  (select review_num from tag where word='�ι��Ӹ�' order by review_num desc)) where page=1);
 *  
 *  
 * 4-1) ..
 * select * from tourspot where spot_name='���ýĹ���';
 * 
 * 4-2)
 * select * from review where review_num in
 *  (select review_num from
 *  (select review_num, ceil(rownum/2) page from
 *  (select review_num from tag where word=
 * (select city from tourspot where spot_name='���ýĹ���') order by review_num desc)) where page=1);
 */

/*
 * 1. �˻������ tag�� �ִ� ���(tag ������ tourspot �� ��)
 *    1) tag�� ���� ���� ��ܿ� ǥ��
 *    2) tag�� ���õ� ���� ǥ��
 *    
 * 2. �˻������ tag�� �ִ� ���(tag ������ tourspot�� �ƴ� ��-location, city)
 *    1) v1, v2 ���������� ��ũ �ɾ��ֱ�
 *    
 * 3. �˻������ tag�� �ִ� ���(tag ������ tourspot�� �ƴ� ��-�ٸ� �±�)
 *    1) tag�� ���õ� ���� ǥ��
 *    
 * 4. �˻������ tag�� ���� ���(tag ������ tourspot �� ��)
 *    1) �˻������ ���� ���� ��ܿ� ǥ��
 *    2) �˻��� ������ tourspot�� ���� �ű⿡ �ִ� city �����ϰ�
 *       city�� ���õ� ���� ǥ��
 *    
 * 5. �˻������ tag�� ���� ���(tag ������ city,location �� ��)
 *    1) v1, v2 ���������� ��ũ �ɾ��ֱ�
 *    
 * 6. �˻������ tag�� ���� ���(tag ������ tourspot,city,location�� �ƴ� ��)
 *    1) �˻�..x.....
 */




