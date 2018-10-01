package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleInfo;
import model.vo.AttractionVO;
import model.vo.FestivalVO;
import model.vo.ReviewVO;
import query.review.ReviewStringQuery;

public class TourDao {
	private static TourDao reviewDao = new TourDao();
	private TourDao() {}
	public static TourDao getInstance() {
		return reviewDao;
	}
	
	
	
	
	
	
	static {
		try {
			Class.forName(OracleInfo.DRIVER_NAME);
			System.out.println("����̹� �ε� ����");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getCities(String location) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> cities = new ArrayList<String>();
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GETCITIES);
			ps.setString(1, location);
			rs = ps.executeQuery();
			while(rs.next()) {
				cities.add(rs.getString("city"));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return cities;
	}
	
	void addLike(int reviewNum) {
		/*
		 * �� �޼��尡 ȣ��Ǹ� �۹�ȣ post_num�� ������ like ���� 1 �����Ѵ�.
		 */
		// post_num �� �� ã��
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// + review_num
		try {
			conn = getConnect();
			String updateQuery = "update review set like=? where review_num=?";
			ps = conn.prepareStatement(updateQuery);
			ps.setInt(1, reviewNum);
			ps.setInt(2, reviewNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("reviewNum�� like�� 1 ����! :: " + rs.getInt("like"));
			}
		} catch (Exception e) {
		}
	}// addLike ������

	/*public ArrayList<ReviewVO> getBestReview() throws SQLException {
		
		 * �� �޼��尡 ȣ��Ǹ�.. DB�� ����� ��� Review�� ���ƿ� ���� ���ؼ� �ϴ��� ���� 3�������� ����Ʈ�� �����ϱ�! �׸��� ��
		 * ����Ʈ�� review_num�� tag ���̺� �ִ� review_num�̶� ���� �� Ȯ���ؼ�
		 
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		ReviewVO vo = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.BEST_REVIEW);				*****************************��ġ��********************************
			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new ReviewVO();
				vo.setReviewNum(rs.getInt("review_num"));
				vo.setContent(rs.getString("content"));
				vo.setLike(rs.getInt("likes"));
				list.add(vo);
			}
			ps = conn.prepareStatement(ReviewStringQuery.GET_TAG_LIST);

			for (int i = 0; i < list.size(); i++) {
				ps.setInt(1, list.get(i).getReviewNum());
				rs = ps.executeQuery();
				ArrayList<String> tags = new ArrayList<String>();
				if (rs.next()) {
					tags.add(rs.getString(1));
					list.get(i).setTags(tags);
				} // if
			} // for

			ps = conn.prepareStatement(ReviewStringQuery.GET_IMAGE_LIST);
			for (int i = 0; i < list.size(); i++) {
				ps.setInt(1, list.get(i).getReviewNum());
				rs = ps.executeQuery();
				ArrayList<String> img = new ArrayList<String>();
				if (rs.next()) {
					img.add(rs.getString(1));
					list.get(i).setImages(img);
				} // if
			} // for
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getBestReview ������
*/
	public ArrayList<FestivalVO> getFestivalInfo(String location) throws SQLException {
		ArrayList<FestivalVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_FESTIVAL_INFO);
			ps.setString(1, location);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new FestivalVO(rs.getString("festival_Name"), rs.getString("festival_Location"),
						rs.getString("location"), rs.getString("city"), rs.getString("start_Date"),
						rs.getString("END_DATE"), rs.getString("agency")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getFestivalInfo ö����

	public ArrayList<AttractionVO> getAttraction(String city) throws SQLException {
		ArrayList<AttractionVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_ATTRACTION);
			ps.setString(1, city);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new AttractionVO(rs.getString("spot_name"), rs.getString("address"), rs.getString("location"),
						rs.getString("city"), rs.getString("info")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}// getAttraction ö����

	public void scrap(String id, int review_num) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.SCRAP);
			ps.setString(1, id);
			ps.setInt(2, review_num);
			ps.executeUpdate();

		} finally {
			closeAll(ps, conn);
		}
	} // scrap ö����

	public ReviewVO checkReview(int reviewNum) throws SQLException { // �� ��ȸ�ϱ�
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReviewVO rvo = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.CHECK_REVIEW);
			ps.setInt(1, reviewNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				rvo = new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"),
						rs.getString("location"), rs.getString("city"), rs.getString("content"),
						rs.getString("date_writing"), rs.getInt("likes"));
			}
			//�ڸ�Ʈ. �̹���. ��� �����;���..
		} finally {
			closeAll(ps, conn);
		}
		return rvo;
	} // checkReview ���־�
	
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASS);
		System.out.println("��� ���� ����!");
		return conn;
	}// getConnect
	
	private void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}// closeAll

	private void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(ps, conn);
	}// closeAll
	
	public static void main(String[] args) {		//�����׽�Ʈ
		
	}
}
