package com.lawcloud.lawper.common.lucene.model;

public class Attachment {

	private Integer fileid;
	private String filename;
	private String filetype;
	private String filepath;
	private String filedate;
	private Float filescore;
	private String hitword;
	
	public Integer getFileid() {
		return fileid;
	}
	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFiledate() {
		return filedate;
	}
	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}
	public Float getFilescore() {
		return filescore;
	}
	public void setFilescore(Float filescore) {
		this.filescore = filescore;
	}
	public String getHitword() {
		return hitword;
	}
	public void setHitword(String hitword) {
		this.hitword = hitword;
	}
}
