package scratch;

import java.util.List;

public interface Participant
{
    public String identifier();


    public static boolean doesListContain(List<Participant> list, Participant aToFind)
    {
        boolean itDoes = false;

        if (list != null)
        {
            for(Participant aPotential: list)
            {
                if (aPotential.equals(aToFind))
                {
                    itDoes = true;
                    break;
                }
            }
        }

        return itDoes;
    }

}
