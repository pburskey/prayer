package com.burskey.prayer.participant;

import java.util.Comparator;
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


    public static List<Participant> sortByIdentifier(List<Participant> aList)
    {
        aList.sort(new SortByIdentifier());
        return aList;
    }


    public static class SortByIdentifier implements Comparator<Participant>
    {
        @Override
        public int compare(Participant source, Participant target) {
            String a = source.identifier();
            String b = target == null ? "" : target.identifier();
            return a.compareToIgnoreCase(b);
        }
    }



}
