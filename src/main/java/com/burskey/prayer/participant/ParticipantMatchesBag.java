package com.burskey.prayer.participant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParticipantMatchesBag
{

    private Set<Match> allCombinations = null;

    public final Set<Match> getAllCombinations()
    {
        if (this.allCombinations == null) {
            this.allCombinations = new HashSet<Match>();
        }
        return allCombinations;
    }


    public List<Match> combinationsAsList()
    {
        List<Match> list = new ArrayList<Match>();
        list.addAll(this.getAllCombinations());
        return list;
    }


    public void createCombinations(Participant[] participants, int matchSize)
    {

        this.createCombinations(participants, participants.length, matchSize);

    }


    void createCombinations(Participant[] participants, int n, int r)
    {
        // A temporary array to store all combination one by one
        Participant data[]=new Participant[r];

        // Print all combination using temprary array 'data[]'
        createCombinations(participants, data, 0, n-1, 0, r);
    }


    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    void createCombinations(Participant arr[], Participant data[], int start,
                                int end, int index, int r)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            Team team = new Team(data.length);
            for (int j=0; j<r; j++)
            {
                team.addParticipant(data[j]);
//                System.out.print(data[j]+" ");
            }
            this.getAllCombinations().add(team);
//            System.out.println("");
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            createCombinations(arr, data, i+1, end, index+1, r);
        }
    }







}
