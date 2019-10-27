import java.io.*;
import java.util.*;

public class Hw1_3
{
	static int gap,match,mismatch;
	static List<String> list = new ArrayList<>();
	static HashMap<String,Integer> scoreMap = new HashMap<>();
	static HashMap<String,String> stringMap = new HashMap<>();
	static int max=Integer.MIN_VALUE;
	static String align,str1,str2;
	public static void main(String ar[])throws IOException
	 {
		Hw1_3 obj = new Hw1_3();
        String inputfile = ar[0];
		match = Integer.parseInt(ar[1]);
		mismatch = Integer.parseInt(ar[2]);
		gap = Integer.parseInt(ar[3]);
        String outputfile = ar[4];
		File file = new File(inputfile+".txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile+".txt"));
        bw.write(">seq0"+"\n");
		String st;
		st = br.readLine();
		while ((st = br.readLine()) != null)
		{
			list.add(st);
			st = br.readLine();
		}
		// Reading all the input sequences and calculating the scores for that sequences
		for(int i=0;i<list.size();i++)
		{
			obj.addString(list.get(i),i);
			obj.findMax();
		}
		// Removing the two sequences with best scores and adding the merged sequence
		list.remove(str1);
		list.remove(str2);
		list.add(0,align);
		// Removing the the two sequences with best scores from the map as well
		obj.clearMap(str1,str2);
		// Removing sequences till one merged sequence remains
		while(list.size() > 1)
		{
			max=Integer.MIN_VALUE;
			obj.addString(align,0);
			obj.findMax();
			list.remove(str1);
			list.remove(str2);
			list.add(0,align);
			obj.clearMap(str1,str2);
		}
        bw.write(list.get(0));
        bw.close();
        System.out.println("output in file:"+outputfile);
	}
	// Finding maximum score as well the sequences having best scores
	public void findMax()
	{
		for(Map.Entry<String,Integer> entry: scoreMap.entrySet())
		{
			if(entry.getValue() > max)
			{
					max = entry.getValue();
					String str = entry.getKey();
					align = stringMap.get(str);
					String split[] = str.split("_");
					str1 = split[0];
					str2 = split[1];
			}
		}
	}
	// adding String  as well as the scores in the hashmap
	public void addString(String temp, int i)
	{
		for(int j=i+1;j<list.size();j++)
		{
			int val = dovetailAlignment(temp,list.get(j));
			scoreMap.put(temp+"_"+list.get(j),val);
		}
	}
	// removing the two strings from the hashmap
	public void clearMap(String str1, String str2)
	{
		List<String> removeList = new ArrayList<>();
		for(Map.Entry<String,Integer> entry:scoreMap.entrySet())
		{
			String str = entry.getKey();
			String split[] = str.split("_");
			if(split[0].equals(str1) || split[1].equals(str1) || split[0].equals(str2) || split[1].equals(str2))
				removeList.add(split[0]+"_"+split[1]);
		}
		for(int i=0;i<removeList.size();i++)
		{
			scoreMap.remove(removeList.get(i));
			stringMap.remove(removeList.get(i));
		}
	}
	// Performing the dovetail Alignment
	public int dovetailAlignment(String str1, String str2)
	{
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];
		int rows = dp.length;
		int cols = dp[0].length;
		for (int j = 0; j < cols; j ++)
			dp[0][j] = 0;
		for (int i = 0; i < rows; i ++)
			dp[i][0] = 0;
		for (int i = 1; i < rows; i++)
			for (int j = 1; j < cols; j++)
				dp[i][j] = Math.max(dp[i-1][j] + gap,Math.max(dp[i][j-1] + gap, dp[i-1][j-1] + mismatchPenalty(str1.charAt(i-1), str2.charAt(j-1))));

		int maxCol = Integer.MIN_VALUE;
		int startCol = -1;
		int maxRow = Integer.MIN_VALUE;
		int startRow = -1;

		for (int i = 0; i < rows; i++)
			if (dp[i][cols - 1] > maxCol)
			{
				maxCol = dp[i][cols - 1];
				startCol = i;
			}

		for (int j = 0; j < cols; j++)
			if (dp[rows-1][j] > maxRow)
			{
				maxRow = dp[rows - 1][j];
				startRow = j;
			}

		if (maxRow > maxCol)
			mergedSequence(str1, str2, dp, rows - 1, startRow);
		else
			mergedSequence(str1, str2, dp, startCol, cols - 1);

		return maxRow > maxCol ? maxRow : maxCol;
	}

	int mismatchPenalty(char c1, char c2)
	{
		if(c1 == c2)
			return match;
		else
			return mismatch;
	}
	// Generating the merged String
	void mergedSequence(String seq1, String seq2, int[][] dpTable, int startRow, int startCol)
	 {
		String seq1Aligned = "";
		String seq2Aligned = "";
		int i = startRow;
		int j = startCol;
		while(i>0 && j>0 && dpTable[i][j] != 0)
		{
			if(dpTable[i][j] - mismatchPenalty(seq1.charAt(i-1), seq2.charAt(j-1)) == dpTable[i-1][j-1])
			{
				seq1Aligned = seq1.charAt(i-1) + seq1Aligned;
				seq2Aligned = seq2.charAt(j-1) + seq2Aligned;
				i=i-1;
				j=j-1;
			}
			else if(dpTable[i][j] - gap == dpTable[i-1][j])
			{
				seq1Aligned = seq1.charAt(i-1) + seq1Aligned;
				i=i-1;
			}
			else if(dpTable[i][j] - gap == dpTable[i][j-1])
			{
				seq2Aligned = seq2.charAt(j-1) + seq2Aligned;
				j=j-1;
			}
		}
		int index1 = seq1.indexOf(seq1Aligned);
		int index2 = seq2.indexOf(seq2Aligned);
		String s = seq1.substring(0,index1);
		s = s+seq2.substring(0,index2);
		if(seq1Aligned.length()>seq2Aligned.length())
			s= s+seq1Aligned;
		else
			s= s+seq2Aligned;
		s = s+ seq1.substring(index1+seq1Aligned.length());
		s = s+ seq2.substring(index2+seq2Aligned.length());
		stringMap.put(seq1+"_"+seq2,s);
	}
}
