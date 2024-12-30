//Code authored by Ameya Mandhare. I did not have any partners.

public class GenericsLab
{
    public static void main(String[] args)
    {
        Integer [] intArry = {1, 2, 3, 4, 5 };
        Double [] doubArry = {1.1, 2.2, 3.3, 4.4};
        Character [] charArray = {'H','E','L', 'L', 'O' };
        String [] strArray = {"once", "upon", "a", "time" };
        printArray(intArry);
        printArray(doubArry);
        printArray(charArray);
        printArray(strArray);
        System.out.println("max Integer is: " + getMax(intArry));
        System.out.println("max Double is: " + getMax(doubArry));
        System.out.println("max Character is: " + getMax(charArray));
        System.out.println("max String is: " + getMax(strArray));



    }

    public static <T extends Comparable<T>> T getMax(T[] t)
    {
        T max = t[0];
        for(int i=0; i<t.length; i++)
        {
            if(t[i].compareTo(max)==1) max=t[i];
        }
        return max;

    }


//    public static Comparable getMax(Comparable[] t)
//    {
//        Comparable max = t[0];
//        for(int i=0; i<t.length; i++) //ChatGPT informed me what compareTo returned
//        {
//            if(t[i].compareTo(max)==1) max=t[i];
//        }
//        return max;
//
//    }
    public static <T>void printArray(T[] t)
    //ChatGPT informed me that <T> goes before return type.
    {
        System.out.print("{");
        int counter = 0;
        for(T x: t)
        {
            System.out.print(x);
            if(counter<(t.length-1)) System.out.print(", ");
            counter++;
        }
        System.out.println("}");
    }



//    public static void printArray(Integer[] t)
//    {
//        System.out.print("{");
//        int counter = 0;
//        for(Integer x: t)
//        {
//            System.out.print(x);
//            if(counter<(t.length-1)) System.out.print(", ");
//            counter++;
//        }
//        System.out.println("}");
//    }
//
//    public static void printArray(Double[] t)
//    {
//        System.out.print("{");
//        int counter = 0;
//        for(Double x: t)
//        {
//            System.out.print(x);
//            if(counter<(t.length-1)) System.out.print(", ");
//            counter++;
//        }
//        System.out.println("}");
//    }
//
//    public static void printArray(Character[] t)
//    {
//        System.out.print("{");
//        int counter = 0;
//        for(char x: t)
//        {
//            System.out.print(x);
//            if(counter<(t.length-1)) System.out.print(", ");
//            counter++;
//        }
//        System.out.println("}");
//    }
//
//    public static void printArray(String[] t)
//    {
//        System.out.print("{");
//        int counter = 0;
//        for(String x: t)
//        {
//            System.out.print(x);
//            if(counter<(t.length-1)) System.out.print(", ");
//            counter++;
//        }
//        System.out.println("}");
//    }

//    public static void printArray(Object[] t)
//    {
//        System.out.print("{");
//        int counter = 0;
//        for(Object x: t)
//        {
//            System.out.print(x);
//            if(counter<(t.length-1)) System.out.print(", ");
//            counter++;
//        }
//        System.out.println("}");
//    }
}