import java.awt.image.BufferedImage;

public class ImageProccessment {
    

    /*
     * Localizar ponto "inicial" do objeto
     */
    
    public static int[] getInitialPoint(BufferedImage image) {
    	boolean aux = false;
    	int[] init = new int[2];
    	
        for (int x = 0; x < image.getHeight(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
            	
                if (image.getRGB(y,x) != -1 && !(aux)) {
                    
                	init[0] = x;
                    init[1] = y;
                    aux = true;
                    
                    return init;
                }
            }
            System.out.println();
        }
		return init;
    }
    
    /*
     * Achar largura do objeto
     */
    
    public static int getObjectWidth(BufferedImage image) {
    	int count = 0;
    	int objectWidth = 0;
    	
        for (int x = 0; x < image.getHeight(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
            	
            	if (image.getRGB(y,x) != -1) {
            		count++;
            	} else {
            		if (count > objectWidth) {
						objectWidth = count;
					}
            		count = 0;
            	}	
            }
        }
		return objectWidth;
    }
    
    /*
     * Achar altura do objeto
     */
    
    public static int getObjectHeight(BufferedImage image) {
    	int count = 0;
    	int objectHeight = 0;
    	
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
            	
            	if (image.getRGB(y,x) != -1) {
            		count++;
            	} else {
            		if (count > objectHeight) {
						objectHeight = count;
					}
            		count = 0;
            	}	
            }
        }
		return objectHeight;
    }
        
    public static int chainCodes(BufferedImage image, int op) {
    	int[][] visited = new int[image.getWidth()][image.getHeight()];
    	int perimeter = 0;
    	int pointsNumber = 0;
    	
    	chainCodesAux(image, visited, perimeter, pointsNumber, 0, 0);
    	
    	if (op == 0) // Calcula o número de pontos da borda
    		return pointsNumber;
    	else if (op == 1) // Tamanho da borda
    		return perimeter;
    	else
    		return -1;
    }
    
    /*
     * Calcula o número de pontos da borda do objeto
     */
    private static void chainCodesAux(BufferedImage image, int[][] visited, int perimeter, int pointsNumber, int x, int y) {
    	int[] point = new int[2];
    	
    	point = nextPixels(image, visited, perimeter, pointsNumber, x, y);
    	
    	visited[x][y] = 1;
    	
    	if(visited[point[0]][point[1]] == 0) {
    		chainCodesAux(image, visited, perimeter, pointsNumber, point[0], point[1]);
    	} else {
    		System.out.println();
    	}
    }


	private static int[] nextPixels(BufferedImage image, int[][] visited, int perimeter, int pointsNumber, int x, int y) {
		int[] point = new int[2];
		boolean aux = false;
		

		int[][] sides = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}};
		int i = 0;
		
		for (int[] p : sides) {
			int newX = x+p[0];
			int newY = y+p[1];
			
			if (border(image, newX, newY) && !aux && visited[newX][newY] == 0) {
				System.out.println(i+" ");
				
				aux = true;
				point[0] = newX;
				point[1] = newY;
				perimeter += (p[0]*p[1] == 0)
						? 1 // if not diagonal
						: Math.sqrt(2); // else 
				
				return point;
			}
			
			i++;
		}
		
		point[0] = x;
		point[1] = y;
		return point;
	}


	private static boolean border(BufferedImage image, int x, int y) {
		if (image.getRGB(x, y) == 0) return false;
		
		// Verifica esquerda
		if (y == 0) return true;
		if (y > 0) {
			if(image.getRGB(y-1, x) == -1) return true;
		}
		
		// Verifica topo
		if (x == 0) return true;
		if (x > 0) {
			if (image.getRGB(y, x-1) == -1) return true;
		}
		
		// Verifica direita
		if (y == image.getWidth()) return true;
		if (y < image.getWidth()) {
			if (image.getRGB(y+1, x) == -1) return true;
		}
		
		// Verifica embaixo
		if (x == image.getHeight()) return true;
		if (x < image.getHeight()) {
			if (image.getRGB(y, x+1) == -1) return true;
		}
	return false;
	}
}
