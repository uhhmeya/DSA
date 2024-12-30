//Code authored by Ameya Mandhare. I did not have any partners.
import java.util.function.Function;

//The code below was developed by referencing the TA's and javabrahman for the findMax function syntax
public class findMax
{
    public static void main(String[] args)
    {
        Function<char[], Character> findMax = (char[] t) ->
        {
            char max = t[0];
            for(int i=1; i<t.length; i++)
            {
                if(t[i]>max) max=t[i];
            }
            return max;
        };

        char[] ICYMI = {'i','c','y','m','i'};
        System.out.println(findMax.apply(ICYMI));
    }
}
