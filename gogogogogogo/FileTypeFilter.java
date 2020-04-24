package gogogogogogo;

import java.io.File;

public class FileTypeFilter {

	private final String extension;
	private final String description;
	
	
	public FileTypeFilter(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}
	public boolean accept(File file) {
		if(file.isDirectory()) {
			return true;
		}
		return file.getName().endsWith(extension);
	}
	
	public String getDescription() {
		//
		return description+String.format(" (*%s)", extension);
	}
	
}
