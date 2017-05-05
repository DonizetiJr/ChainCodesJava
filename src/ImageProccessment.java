import java.awt.image.BufferedImage;

public class ImageProccessment {
	private int borderHeight;
	private int borderWidth;
	
    private int visited[][];
    private int perimeter;
    

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
    	boolean aux = false;
    	int count = 0;
    	int objectWidth = 0;
    	int[] init = new int[2];
    	
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
    	boolean aux = false;
    	int count = 0;
    	int objectHeight = 0;
    	int[] init = new int[2];
    	
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
        
    public static void chainCodes(BufferedImage image) {
    	int[][] visited = new int[image.getWidth()][image.getHeight()];
    	int perimeter = 0;
    	
    	chainCodesAux(image, visited, perimeter, 0, 0);
    }
    
    /*
     * Calcula o número de pontos da borda do objeto
     */
    private static void chainCodesAux(BufferedImage image, int[][] visited, int perimeter, int x, int y) {
    	int[] point = new int[2];
    	
    	point = nextPixels(visited, perimeter, x, y);
    	
    	visited[x][y] = 1;
    	
    	if(visited[point[0]][point[1]] == 0) {
    		chainCodesAux(image, visited, point[0], point[1]);
    	} else {
    		System.out.println();
    	}
    }


	private static int[] nextPixels(int[][] visited, int perimeter, int x, int y) {
		int[] point = new int[2];
		boolean aux = false;
		
		if (border(x, y+1) && !aux && visited[x][y+1] == 0) {
			y++;
			System.out.println("0 ");
			perimeter += 1;
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		if (border(x+1, y+1) && !aux && visited[x+1][y+1] == 0) {
			y++;
			x++;
			System.out.println("1 ");
			perimeter += Math.sqrt(2);
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		if (border(x+1, y-1) && !aux && visited[x+1][y] == 0) {
			x++;
			System.out.println("2 ");
			perimeter += 1;
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		if (border(x+1, y-1) && !aux && visited[x+1][y-1] == 0) {
			y--;
			x++;
			System.out.println("3 ");
			perimeter += Math.sqrt(2);
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		if (border(x, y-1) && !aux && visited[x][y-1] == 0) {
			y--;
			System.out.println("4 ");
			perimeter += 1;
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		if (border(x-1, y-1) && !aux && visited[x-1][y-1] == 0) {
			x--;
			y--;
			System.out.println("5 ");
			perimeter += Math.sqrt(2);
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		if (border(x-1, y) && !aux && visited[x-1][y] == 0) {
			x--;
			System.out.println("6 ");
			perimeter += 1;
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		if (border(x-1, y+1) && !aux && visited[x-1][y+1] == 0) {
			x--;
			y--;
			System.out.println("7 ");
			perimeter += Math.sqrt(2);
			aux = true;
			point[0] = x;
			point[1] = y;
			return point;
		}
		
		point[0] = x;
		point[1] = y;
		return point;
	}


	private static boolean border(int x, int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
