public class CarPooling{
	/**

	You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

	Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

	Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

	**/


	/**

	This is similar to type 2 or hotel booking problem


	**/


	public boolean carPooling(int[][] trips, int capacity) {
        int[][] tripEnds = trips.clone();
        Arrays.sort(trips, (a,b)-> a[1]-b[1]);
        Arrays.sort(tripEnds, (a,b)-> a[2]-b[2]);
        
        int poolStart = 0;
        int poolEnd = 0;
        while(poolStart < trips.length && poolEnd < trips.length){
            if(trips[poolStart][1] < tripEnds[poolEnd][2]){
                capacity = capacity - trips[poolStart][0];
                poolStart++;
            }else if(trips[poolStart][1] > tripEnds[poolEnd][2]){
                capacity = capacity + tripEnds[poolEnd][0];
                poolEnd++;
            }else{
                capacity += tripEnds[poolEnd][0] - trips[poolStart][0];
                poolEnd++;
                poolStart++;
            }
            
            if(capacity < 0){
                return false;
            }
        }
        
        return true;
    }


    // Approch 2

    // Just like type 3



    public boolean carPooling(int[][] trips, int capacity) {
        int[] timestamp = new int[1001];
        for (int[] trip : trips) {
            timestamp[trip[1]] += trip[0];
            timestamp[trip[2]] -= trip[0];
        }
        int ued_capacity = 0;
        for (int number : timestamp) {
            ued_capacity += number;
            if (ued_capacity > capacity) {
                return false;
            }
        }
        return true;
    }
}