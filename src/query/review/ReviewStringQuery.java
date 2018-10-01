package query.review;

public interface ReviewStringQuery {

	
	String SEARCH_BY_TAG="SELECT * FROM review WHERE review_num in (SELECT review_num FROM tag WHERE word =?)";
	//�±װ˻��ؼ� ���� ��������
	String GET_WORD="SELECT * FROM TAG WHERE review_num  in (SELECT review_num FROM tag WHERE word =?)";
	//�±׿��� word ��������
	String GET_COMMENTS="SELECT * FROM comments WHERE review_num in (SELECT review_num FROM tag WHERE word =?)";
	//review_num�� �´� comments���̺� ��������
	String GET_REVIEW_IMAGE="SELECT * FROM review_image WHERE review_num in (SELECT review_num FROM tag WHERE word = ?)";
	//review_num�� �´� review_image���̺� ��������
	String SCRAP="insert into scrap values(?,?)";
	String GET_ATTRACTION = "select tourspot,address,location,city,info,img from tourspot where city=?";
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency from festival where city=?";
	
}
