public class TwoCityScheduling{

    /** 

    There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

    Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

    Example 1:

    Input: [[10,20],[30,200],[400,50],[30,20]]
    Output: 110
    Explanation: 
    The first person goes to city A for a cost of 10.
    The second person goes to city A for a cost of 30.
    The third person goes to city B for a cost of 50.
    The fourth person goes to city B for a cost of 20.

    The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.

    **/
    
    public int twoCitySchedCost(int[][] costs) {
        // Now we can think in greedy way that we can take the least
        // in each of the bucket then add it to our cost
        
        // But the issue is, the candidates needs to be divided equally 
        // amoung the cities 
        
        // Ex: if we dicide to take some candidates to city A, and if all the candidates are not 
        // divided equally,then we need to decide which can pick from A and swap them
        
        // So the approach needed here is still GREEDY but we need to pre process it
        // We need to get the effective/relative cost of one city lets say A for each candicate and then sort them
        
        // Ex: if a candidate has [400,50], their effective cost is 350, which if we choose city A we add 350 as cost relative to B
        // Ex: if a candidate has [30,200], their effective cost is -170, which if we choose city A we add -170 as cost relative to B
        
        // So we find the same and sort the candidated based on this reletive cost.
        
        Arrays.sort(costs, (int[] c1, int[] c2) -> (c1[0]-c1[1]) - (c2[0]-c2[1]));
        
        
        // one we are done sorting, we need to divide them. Like the first half will go to city
        // A and the other to city B.
        int cost = 0;
        
        for(int i = 0; i < costs.length; i++){
            if(i < costs.length/2){
                cost+= costs[i][0];
            }else{
                cost+= costs[i][1];
            }
        }
        
        
        return cost;
    }

}