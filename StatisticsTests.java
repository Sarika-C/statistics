import java.util.Arrays;

public class StatisticsTests {
	public static void testData(double[] a) {
		System.out.println("The mean of " + Arrays.toString(a) + " is " + Statistics.mean(a));
		System.out.println("The median of " + Arrays.toString(a) + " is " + Statistics.median(a));
		System.out.println("The modes of " + Arrays.toString(a) + " are " + Statistics.mode(a));
		System.out.println("The range of " + Arrays.toString(a) + " is " + Statistics.range(a));
		System.out.println("The upper quartile of " + Arrays.toString(a) + " is " + Statistics.upperQuartile(a));
		System.out.println("The lower quartile of " + Arrays.toString(a) + " is " + Statistics.lowerQuartile(a));
		System.out.println("The interquartile range of " + Arrays.toString(a) + " is " + Statistics.interquartileRange(a));
		System.out.println("The outliers of " + Arrays.toString(a) + " are " + Statistics.outliers(a));
		System.out.println("The standard deviation of " + Arrays.toString(a) + " is " + Statistics.standardDeviation(a));
	}
	
	public static void tests() {
		double[][] tests = {{}, 
				{9.434456756}, 
				{0, 1, 2, 3}, 
				{5, 6, 7}, 
				{-3, -13, -20, -2, -9, 0, 0, 4}, 
				{45.6, 32.4, 90.8, 100.5, 22.1, 0}, 
				{-1111111111, -33333, 99999}, 
				{4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 1, 0}};
	
		for(double[] a: tests) {
			testData(a);
			for(double n: a) {
				System.out.println("The z-score of " + n + " in " + Arrays.toString(a) + " is " + 
						Statistics.zScore(a, n));
			}
			System.out.println(Arrays.toString(a));
		}
	}

	public static void main(String[] args) {
		tests();
	}

}
