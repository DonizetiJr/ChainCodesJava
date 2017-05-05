import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChainCode {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Digite uma String: ");
		String srt = EntradaTeclado.leString();
		
		File inFile = new File("src/" + srt);
		try {
			BufferedImage image = ImageIO.read(inFile);
			ImageProccessment.getInitialPoint(image);
		} catch (IOException e) {
			String workingDir = System.getProperty("user.dir");
		    System.out.println("Current working directory : " + workingDir);
		    e.printStackTrace();
		}
		
	}
}
