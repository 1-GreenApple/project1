package JSP_code;

public class Bbsjava {
	/*-> maria DB Ŀ�ǵ� â�� 
	 * create table bbs(
	bbsID int,
	bbsTitle varchar(50),
	userID varchar(20),
	bbsDate datetime,
	bbsContent varchar(2048),
	bbsAvailable int,
	primary key(bbsID)
);
	 * */
	
		private int bbsID;
		private String bbsTitle;
		private String userID;
		private String bbsData;
		private String bbsContent;
		private int bbsAvailable;
		public int getBbsID() {
			return bbsID;
		}
		public void setBbsID(int bbsID) {
			this.bbsID = bbsID;
		}
		public String getBbsTitle() {
			return bbsTitle;
		}
		public void setBbsTitle(String bbsTitle) {
			this.bbsTitle = bbsTitle;
		}
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public String getBbsData() {
			return bbsData;
		}
		public void setBbsData(String bbsData) {
			this.bbsData = bbsData;
		}
		public String getBbsContent() {
			return bbsContent;
		}
		public void setBbsContent(String bbsContent) {
			this.bbsContent = bbsContent;
		}
		public int getBbsAvailable() {
			return bbsAvailable;
		}
		public void setBbsAvailable(int bbsAvailable) {
			this.bbsAvailable = bbsAvailable;
		}
}
