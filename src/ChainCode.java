import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChainCode {

	public static void main(String[] args) throws Exception {
		
		int op;
		
		System.out.println("Digite uma String: ");
		String srt = EntradaTeclado.leString();
		
		File inFile = new File("src/" + srt);
		try {
			BufferedImage image = ImageIO.read(inFile);
			ImageProccessment.getInitialPoint(image);
			
			do {
				op = EntradaTeclado.leInt();
				System.out.println("Escolha um opção:\n");
				System.out.println("1) Localizar ponto inicial.");
				System.out.println("2) Achar a largura.");
				System.out.println("3) Achar a altura.");
				System.out.println("4) Calcular o número de pontos da borda.");
				System.out.println("5) Calcular tamanho da borda.");
				System.out.println("6) Sair.");
				
				switch (op) {
				case 1:
					int[] point = ImageProccessment.getInitialPoint(image);
					System.out.println("(" + point[0]+", " + point[1]+")" );
					break;
					
				case 2:
					System.out.println(ImageProccessment.getObjectWidth(image));
					break;
					
				case 3:
					System.out.println(ImageProccessment.getObjectHeight(image));
					break;
					
				case 4:
					System.out.println(ImageProccessment.chainCodes(image, 0));
					break;

				case 5:
					System.out.println(ImageProccessment.chainCodes(image, 1));
				default:
					System.out.println("Operador inválido!");
					break;
				}
				
			} while (op != 6);

			
		} catch (IOException e) {
			String workingDir = System.getProperty("user.dir");
		    System.out.println("Current working directory : " + workingDir);
		    e.printStackTrace();
		}
		
	}
		
}
