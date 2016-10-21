package com.lombardrisk.colline.supportutil.data;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;

import java.io.File;

/**
 * Created by Bond Chen on 2/22/2016.
 * Get the ${COLLINE_HOME} except *.jars and other files.
 */
public class CollineHome {
	private String collineHomeDir = null;

	/**
	 *
	 * @param collineHomeDir the file system path of the home directory
	 */
	public CollineHome(String collineHomeDir){
		this.collineHomeDir = collineHomeDir;
	}

	/**
	 * Zip the home directory includes and excludes patterns
	 * @param destFile The target zip file name
	 * @param includes Includes patterns
	 * @param excludes Excludes patterns
	 */
	@Deprecated
	public void zip(String destFile, String includes, String excludes){
		final class Ziper extends Zip{
			public Ziper(){
				project = new Project();
				project.init();
				taskType = "zip";
				taskName = "zip";
				target = new Target();
			}
		}
		Ziper ziper = new Ziper();
		ziper.setBasedir(new File(this.collineHomeDir));
		ziper.setDestFile(new File(destFile));
		if(excludes != null && !excludes.isEmpty()) {
			ziper.setExcludes(excludes);
		}
		if(includes != null && !includes.isEmpty()) {
			ziper.setIncludes(includes);
		}
		ziper.execute();
	}
}
