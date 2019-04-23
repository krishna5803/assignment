
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestPrg {

	public static void main(String[] args) {

		String folder = "C:\\nextstepforservicenow\\";
		String testData = folder + "TestData.txt";
		String slimeTorpedo = folder + "SlimeTorpedo.txt";
		String starShip = folder + "Starship.txt";

		System.out.println("----Test Data----");
		int mainData[][] = readData(testData, 100, 100);
		// printArray(mainData);

		System.out.println("----SlimeTorpedo data----");
		int subData1[][] = readData(slimeTorpedo, 11, 11);
		// printArray(subData1);

		System.out.println("----StarShip data----");
		int subData2[][] = readData(starShip, 14, 14);
		// printArray(subData2);

		System.out.println("Occurences of slimeTopedo");
		int count1 = findOccurences(mainData, subData1);
		System.out.println("Counter1:" + count1);

		System.out.println("Occurences of starShip");
		int count2 = findOccurences(mainData, subData2);
		System.out.println("Counter2:" + count2);

		int[][] sixbysix = new int[][] { { 1, 0, 0, 1, 0, 1 }, { 1, 0, 0, 1, 0, 1 }, { 0, 0, 0, 0, 1, 1 },
				{ 1, 1, 1, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1 }, { 1, 1, 1, 0, 0, 1 } };

		int[][] twobytwo = new int[][] { { 1, 1 }, { 1, 0 } };

		System.out.println("Occurences of testdata");
		int count3 = findOccurences(sixbysix, twobytwo);
		System.out.println("Counter3:" + count3);

	}

	private static int findOccurences(int[][] mainArray, int[][] subArray) {
		int count = 0;
		for (int row = 0; row < mainArray.length - subArray.length + 1; row++)
			loopMainArrayCol: for (int col = 0; col < mainArray[row].length - subArray[0].length + 1; col++) {
				for (int r = 0; r < subArray.length; r++)
					for (int c = 0; c < subArray[0].length; c++) {
						if (mainArray[row + r][col + c] != subArray[r][c]) {
							continue loopMainArrayCol;
						}
					}

				System.out.println("Found at row:" + row + ", col:" + col);
				count++;
			}

		return count;
	}

	private static void printArray(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j] + ",");
			}
			System.out.println();
		}
	}

	private static int[][] readData(String path, int rows, int cols) {
		int[][] data = new int[rows][cols];
		int row = 0;
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
			// read line by line
			String line;
			while ((line = br.readLine()) != null) {
				// check chars in line
				// if space fill with ZERO
				// if + fill with ONE
				char[] chars = line.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					int val = 0;
					if (chars[i] == ' ') {
						val = 0;
					} else if (chars[i] == '+') {
						val = 1;
					}
					data[row][i] = val;
				}
				row++;
			}

		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

		return data;
	}

}
