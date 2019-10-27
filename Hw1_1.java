import java.io.*;
import java.util.*;
class Hw1_1
{
    public static void main(String ar[])throws IOException
    {
        int n = Integer.parseInt(ar[0]);
        int a = Integer.parseInt(ar[1]);
        int c = Integer.parseInt(ar[2]);
        int g = Integer.parseInt(ar[3]);
        int t = Integer.parseInt(ar[4]);
        int k = Integer.parseInt(ar[5]);
        double prob = Double.parseDouble(ar[6]);
        String outputfile = ar[7];
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile+".txt"));
        // Total Sum of the input values
        int s = a+c+g+t;
        Random r = new Random();
        StringBuilder seq = new StringBuilder();
        // First Sequence of size n
        for(int i=1;i<=n;i++)
        {
            int val = r.nextInt(s)+1;
            // Dividing probability according to length -> probability of A* sum = value of a
            if(val <= a)
                seq.append("A");
            else if (val <= a+c)
                seq.append("C");
            else if (val <= a+c+g)
                seq.append("G");
            else
                seq.append("T");
        }
        bw.write(">seq0"+"\n");
        bw.write(seq+"\n");
        for(int i=1;i<k;i++)
        {
            // Writing sequence in FASTA format
            bw.write(">seq"+i+"\n");
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<seq.length();j++)
            {
                char ch = seq.charAt(j);
                if(Math.random() <= prob)
                {
                    if(r.nextInt(200)+1 <= 100)
                        {
                            boolean flag = true;
                            while(flag)
                            {
                                int val = r.nextInt(s)+1;
                                if(val <= a)
                                {
                                    if(ch != 'A')
                                    {
                                        sb.append("A");
                                        flag = false;
                                    }
                                }
                                else if(val <= a+c)
                                {
                                    if(ch != 'C')
                                    {
                                        sb.append("C");
                                        flag = false;
                                    }
                                }
                                else if(val <= a+c+g)
                                {
                                    if(ch != 'G')
                                    {
                                        sb.append("G");
                                        flag = false;
                                    }
                                }
                                else
                                {
                                    if(ch != 'T')
                                    {
                                        sb.append("T");
                                        flag = false;
                                    }
                                }
                            }
                        }
                }
                else
                    sb.append(ch+"");
            }
            bw.write(sb+"\n");
        }
        bw.close();
        System.out.println("output in file:"+outputfile);
    }

}
