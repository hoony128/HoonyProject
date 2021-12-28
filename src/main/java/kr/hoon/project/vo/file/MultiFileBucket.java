package kr.hoon.project.vo.file;

import java.util.ArrayList;
import java.util.List;

public class MultiFileBucket {

	List<FileBucket> files = new ArrayList<FileBucket>();
	
	public List<FileBucket> getFiles() {
		return files;
	}

	public void setFiles(List<FileBucket> files) {
		this.files = files;
	}
}

