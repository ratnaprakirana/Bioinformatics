import java.io.*;
import java.util.*;
class Hw1_2
{
    public static void main(String ar[])throws IOException
    {
        String inputfile = ar[0];
        int x = Integer.parseInt(ar[1]);
        int y = Integer.parseInt(ar[2]);
        String outputfile = ar[3];
        File file = new File(inputfile+".txt");
        Random r = new Random();
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile+".txt"));
          String st;
          st = br.readLine();
          int i=0;
          while ((st = br.readLine()) != null)
            {
                int l = st.length();
                while(l>=x)
                {
                    int val = r.nextInt(y-x+1)+x;
                    if(val < l)
                    {
                        bw.write(">seq"+(i++)+"\n");
                        bw.write(st.substring(0,val)+"\n");
                        st = st.substring(val);
                        l = st.length();
                    }
                    else
                    {
                        bw.write(">seq"+(i++)+"\n");
                        bw.write(st+"\n");
                        l=0;
                    }
                }
                st = br.readLine();
            }
        bw.close();
        System.out.println("output in file:"+outputfile);
    }
}
