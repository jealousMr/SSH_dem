import java.util.Scanner;

public class Te {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();

        String[]  str=a.split(":");

        String[] c = new String[2];
        for(int i=0;i<2; i++) {
            c[i] = str[i];
        }
        int d = Integer.parseInt(c[0]);

        if (d <=12) {
            System.out.println(d+ ":" + c[1] +"am");
        }
        else if (d > 12 && d !=24) {
            System.out.println((d-12) + ":" + c[1] +"pm");
        }
        else if (d == 24) {
            System.out.println((d-24) + ":" + c[1] +"am");
        }

    }
}
