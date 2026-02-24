
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();

        String croatia[] = {"č","ć", "dž", "đ", "lj", "nj", "š", "ž"};
        String words[] = {"c=","c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        for(int i=0; i<words.length; i++){
            text = text.replace(words[i], "*");
        }


        System.out.print(text.length());
    }
}
