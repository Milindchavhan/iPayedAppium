package iPayedAppiumFramework.helperClasses;

import java.io.File;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
public class ImageCracker {
	    public String getImgText(String filePath) {
	    	File imageFile = new File(filePath);
	      ITesseract instance = new Tesseract();
	      try 
	      {
	         String imgText = instance.doOCR(imageFile);
	         return imgText;
	      } 
	      catch (TesseractException e) 
	      {
	         e.getMessage();
	         return "Error while reading image";
	      }
	   }
}
