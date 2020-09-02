import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class PhotoManager {
	private List<Photo> photoList = null;

	public PhotoManager() {
		photoList = new ArrayList<Photo>();
	}

	public PhotoManager(List<Photo> photoList) {
		this.photoList = photoList;
	}

	// add photoList in this.photoList
	public void addAll(List<Photo> photoList) {
		this.photoList.addAll(photoList);
	}
	
	// add a photo in this.photoList
	public void add(Photo photo) {
		this.photoList.add(photo);
	}
	
	// remove all in this.photoList
	public void removeAll() {
			photoList.clear();
	}
	
	// remove a photo in this.photoList (by Photo)
	public void remove(Photo other) {
		photoList.remove(other);
	}
	
	// remove a photo in this.photoList (by fullPath)
	public void remove(String fullPath) {
		Iterator<Photo> it = photoList.iterator();
		while (it.hasNext()) {
			Photo p = it.next();
			if (p.getFullPath().equals(fullPath)) {
				it.remove();
				break;		}
		}	
	}
	
	// remove a photo in this.photoList (by index)
	public void remove(int index) {
		photoList.remove(index);
	}
	
	// find a photo in this.photoList (by Photo)
	public Photo find(Photo other) {
		Iterator<Photo> it = photoList.iterator();
		
		while (it.hasNext()) {
			Photo p = it.next();
			if (p.equals(other)) {
				return p;
			}
		}
		return null;
	}

	// find a photo in this.photoList (by fullPath)
	public Photo find(String fullPath) {
		Iterator<Photo> iter = photoList.iterator();
		
		while (iter.hasNext()) {
			Photo p = iter.next();
			if (p.getFullPath().equals(fullPath)) {
				return p;
			}
		}
		
		return null;
	}
	
	// find a photo in this.photoList (by index)
	public Photo find(int index) {
		return null;
	}
	
	// sort this.photoList (by columnIndex)
	
	public void sort(int columnIndex) {

	}

	// sort this.photoList (by columnName)
	public void sort(String columnName) {
		
		switch(columnName) {
		case "Format" : 
			Collections.sort(photoList,Photo.FormatComparator);
			break;
		case "Width" :
			Collections.sort(photoList, Photo.WidthComparator);
			break;
		case "Height" :
			Collections.sort(photoList, Photo.HeightComparator);
			break;
		default : 
			Collections.sort(photoList);
			break; // fullPath
		}
		
	}

	// print all photo information
	public void print() {
		photoList.toArray();
	}

	// get the photo list
	public List<Photo> getList() {
		photoList.getClass();
		return null;
	}
	
	// get the size of photo list
	public int size() {
		photoList.size();
		return 0;
	}

	// if the photo list is empty or not
	public boolean isEmpty() {
		photoList.isEmpty();
		return false;
	}

	// get the photoList's fullPaths as String[]
	public String[] getPhotoFullPaths() {
		if (this.photoList.size() == 0) return null;
		
		String[] names = new String[this.photoList.size()];
		for (int i = 0; i < this.photoList.size(); i++) 
			names[i] = this.photoList.get(i).getFullPath();
		for (String name : names) System.out.println(name);
		return names;
	}
}
