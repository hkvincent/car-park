package vincent.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * this is filter class for JFileChooser to filter file not matching extension of JFileChooser
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class DatFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		String name = f.getName();
		return f.isDirectory()||name.toLowerCase().endsWith(".dat");
	}

	@Override
	public String getDescription() {
		
		return "*.dat";
	}

}
