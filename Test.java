import java.util.ArrayList;

public class HelloWorld {

    public static void main(String[] args) {

        ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();

        ArrayList<String> a = new ArrayList<String>();
        a.add("1");

        ArrayList<String> b = new ArrayList<String>();
        b.add("2");
        System.out.println(a.toString() + b.toString());

        c.add(a);
        c.add(b);
        System.out.println(c);
    }

}

