package kr.hb.board.domain;

public class Board {

	private int bno;
	private String title;
	private String content;
	private int readcnt;
	//원래 자료형은 Date 지만 사용을 편리하게 하기 위해 String으로 변환
	private String regdate;
	private String updatedate;
	//출력을 위한 변수
	private String email;
	private String ip;
	private String dispdate;
	private String nickname;
	private String image;
	private int replycnt;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDispdate() {
		return dispdate;
	}
	public void setDispdate(String dispdate) {
		this.dispdate = dispdate;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", content=" + content + ", readcnt=" + readcnt + ", regdate="
				+ regdate + ", updatedate=" + updatedate + ", email=" + email + ", ip=" + ip + ", dispdate=" + dispdate
				+ ", nickname=" + nickname + ", image=" + image + ", replycnt=" + replycnt + "]";
	}
	
	

	
	
	
}
