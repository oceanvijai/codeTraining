import java.util.ArrayList;

public class HotelBooking {

    /**
     * A hotel manager has to process N advance bookings of rooms for the next
     * season. His hotel has K rooms. Bookings contain an arrival date and a
     * departure date. He wants to find out whether there are enough rooms in the
     * hotel to satisfy the demand.
     * 
     * 
     * Write a program that solves this problem in time O(N log N) .
     */

    /**
     * Input : Arrivals : [1 3 5] Departures : [2 6 8] K : 1
     * 
     * Return : False / 0
     * 
     * At day = 5, there are 2 guests in the hotel. But I have only one room.
     */

    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        Collections.sort(arrive);
        Collections.sort(depart);

        int maxGuests = 0;

        int arrivalIndex = 0;
        int departIndex = 0;
        int guests = 0;

        while (arrivalIndex < arrive.size() && departIndex < depart.size()) {
            if (arrive.get(arrivalIndex) < depart.get(departIndex)) {
                guests++;
                arrivalIndex++;
            } else if (arrive.get(arrivalIndex) == depart.get(departIndex)) {
                arrivalIndex++;
                departIndex++;
            } else {
                guests--;
                departIndex++;
            }
        }

        if (guests > maxGuests) {
            maxGuests = guests;
        }

        if (maxGuests > K) {
            return false;
        } else {
            return true;
        }
    }
}