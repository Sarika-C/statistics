import java.util.ArrayList;

public class Statistics {
	
	/*
	 * Sorts the passed array
	 */
	public static void sort(double[] data) {
		for(int i = 0; i < data.length; i++) {
			for(int j = i + 1; j < data.length; j++) {
				if(data[i] > data[j]) {
					double temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
	}
	
	public static double mean(double[] data) {
		if(data.length == 0) {
			return Double.NaN;
		}
		
		double sum = 0;
		
		for(double d: data) {
			sum += d;
		}
		
		return (sum / data.length);
	}
	
	public static double medianPresorted(double[] data) {
		if(data.length == 0) {
			return Double.NaN;
		}
		
		if(data.length % 2 == 0) {
			double median = (data[data.length / 2] + data[(data.length / 2) - 1]) / 2;
			return median;
		}
		else {
			double median = data[data.length / 2];
			return median;
		}
	}
	
	public static double median(double[] data) {
		double[] sortedData = data.clone();
		sort(sortedData);
		
		return medianPresorted(sortedData);
	}

	public static ArrayList<Double> mode(double[] data) {
		double[] sortedData = data.clone();
		sort(sortedData);
		
		ArrayList<Double> modes = new ArrayList<Double>();
		int numOccurrences = 0;
		
		for(int i = 0; i < data.length; i++) {
			int tally = 0;
			
			for(int j = 0; j < data.length; j++) {
				if(sortedData[i] == sortedData[j]) {
					tally++;
				}
			}
			
			if(tally > 1) {
				if(tally == numOccurrences) {
					if(sortedData[i] != modes.get(modes.size() - 1)) {
						modes.add(sortedData[i]);
					}
				}
				else if(tally > numOccurrences) {
					numOccurrences = tally;
					modes.clear();
					modes.add(sortedData[i]);
				}
			}
		}
		
		return modes;
		
	}
	
	public static double range(double[] data) {
		if(data.length == 0) {
			return Double.NaN;
		}
		
//		double[] sortedData = data.clone();
//		sort(sortedData);
//		
//		double range = sortedData[data.length - 1] - sortedData[0];
		
		double min;
		double max;
		min = max = data[0];
		
		for(double d: data) {
			if(d > max) {
				max = d;
			}
			else if(d < min) {
				min = d;
			}
		}
		
		return max - min;
	}
	
	public static double upperQuartile(double[] data) {
		if(data.length == 0) {
			return Double.NaN;
		}
		
		double[] sortedData = data.clone();
		sort(sortedData);
		
		int n = data.length / 2;
		double[] upperData = new double[n];
		
		int index = 0;
		
		if(data.length % 2 == 0) {
			for(int i = n; i < data.length; i++) {
				upperData[index] = sortedData[i];
				index++;
			}
		}
		else {
			for(int i = n + 1; i < data.length; i++) {
				upperData[index] = sortedData[i];
				index++;
			}
		}
		
		return median(upperData);
	}
	
	public static double lowerQuartile(double[] data) {
		if(data.length == 0) {
			return Double.NaN;
		}
		
		double[] sortedData = data.clone();
		sort(sortedData);
		
		int n = data.length / 2;
		double[] lowerData = new double[n];
		
		for(int i = 0; i < n; i++) {
			lowerData[i] = sortedData[i];
		}
		
		return medianPresorted(lowerData);	
	}
	
	public static double interquartileRange(double[] data) {
		return (upperQuartile(data) - lowerQuartile(data));
	}
	
	public static ArrayList<Double> outliers(double[] data) {
		double upperQ = upperQuartile(data);
		double lowerQ = lowerQuartile(data);
		double IQR = interquartileRange(data);
		
		ArrayList<Double> outliers = new ArrayList<Double>();
		
		for(double d: data) {
			if(d == lowerQ - (1.5 * IQR) || d == upperQ + (1.5 * IQR)) {
				outliers.add(d);
			}
		}
		
		return outliers;
	}
	
	public static double standardDeviation(double[] data) {
		double stDev = 0;
		double mean = mean(data);
		
		double[] squares = new double[data.length];
		int i = 0;
		
		for(double d: data) {
			squares[i] = Math.pow(d - mean, 2);
			i++;
		}
		
		stDev = Math.sqrt(mean(squares));
		return stDev;	
	}
	
	public static double zScore(double[] data, double value) {
		if(data.length == 0) {
			return Double.NaN;
		}
		
		double zScore = (value - mean(data)) / standardDeviation(data);
		return zScore;
	}
}
