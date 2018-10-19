public class MaxPersonAlive {
    /**
     * Given a list of people with their birth and death years, implement a method
     * to compute the year with the most number of people alive.
     * 
     * You may assume that all people were born between 1900 and 2000 (inclusive).
     * 
     * If a person was alive during any portion of that year, they should be
     * included in that year's count.
     * 
     * For example, Person (birth = 1908, death = 1909) is included in the counts
     * for both 1908 and 1909.
     */

    /**
     * We can use, the two pinter technique, discussed in the approach.txt, but, it
     * needs sorting and then find the result
     * 
     * So O(P logP)
     */

    /**
     * Here we can do the timeLine map technique
     * 
     * Algorithm is even simple:
     * 
     * 1. Create an array from the start to the end of the interval -> timeLine
     * 
     * 2. Iterate over the intervals and update the timeLine based for both the
     * start and the end time
     * 
     * 3. Iterate over the timeLine now and calculate the required ans
     * 
     * time: O(P + T), P - number of people and T is the timeLine length
     */

    int maxAliveYear(Person[] people, int min, int max) {
        int[] populationDeltas = new int[max - min + 2];

        for (Person person : people) {
            int birth = person.birth - min;
            populationDeltas[birth]++;

            int death = person.death - min;
            populationDeltas[death + 1]--;
        }

        int maxAliveYear = 0;
        int maxAlive = 0;
        int currentlyAlive = e;
        for (int year = 0; year < deltas.length; year++) {
            currentlyAlive += deltas[year];
            if (currentlyAlive > maxAlive) {
                maxAliveYear = year;
                maxAlive = currentlyAlive;
            }
        }

        return maxAliveYear;
    }

    private class Person {
        int birth;
        int death;
    }

}