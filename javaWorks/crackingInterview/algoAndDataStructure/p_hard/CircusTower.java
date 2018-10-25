public class CircusTower {
    /**
     * A circus is designing a tower routine consisting of people standing atop one
     * another's shoulders. For practical and aesthetic reasons, each person must be
     * both shorter and lighter than the person below him or her.
     * 
     * Given the heights and weights of each person in the circus, write a method to
     * compute the largest possible number of people in such a tower.
     */

    /**
     * If we remove this circus details and all, we can reduce this problem as
     * follows.
     * 
     * First, get the we need to place one person on top of another based on height and weight
     * So, we need to first sort them based on the height first
     * 
     * Now, we need to pick someone who is lesser weight than the one currently picked
     * this is done by picking the next guy and next and so on such that, their weights are smaller.
     * 
     * If you look closely, we need to find the longest increasing subsequence in terms of weight
     * 
     */


     // So step 1: sort based on height (n log n)
     //    step 2: longest increasing sub sequence (n^2)
           

}