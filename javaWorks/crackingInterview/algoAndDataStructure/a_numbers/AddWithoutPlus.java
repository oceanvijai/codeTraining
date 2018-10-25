public class AddWithoutPlus {
    /**
     * Write a function that adds two numbers. You should not use + or any
     * arithmetic operators.
     */

    /**
     * The approach for this is as follows, When we can do normal arithmetic, we can
     * do the following
     * 
     * lets say we want to add, 15 to 17
     * 
     * When we add, 5 to 7 first we take the carry and add it to the next digit 5 +
     * 7 = 12 so, we take 1 to the next position.
     * 
     * Instead, we add without the carry, so the value of 15 + 17 = 22 Now we so the
     * same, but only this time with carry, so 15 + 17 = 10
     * 
     * now we add 10 + 22 = 32 (actual 15 + 17)
     * 
     * 
     * We can do the same in terma of bit wise operation
     * 
     * First we add two number, lets take the same example
     * 
     * 15 -> 11111 and 17 -> 10001
     * 
     * Now if we want only the addition part without the carry, we can think like
     * this, In binary addition, we get 1 if denominator is 1 and numerator is 0 and
     * vice versa If both are one or zero, we get 0
     * 
     * So this is XOR
     * 
     * Then lets take the carry part. We get a carry only if both are 1 and we need
     * to give it to the next bit. i.e if 1 & 1 will only produce 1 and we just need
     * to shilft it one position
     * 
     * SO this is AND and then left shift one position
     * 
     * So this is what we need to do,
     * 
     * First we add without carry by doing XOR And then add with just the carry by
     * doing AND shift
     * 
     * then pass it recursively or iteratively, until we get a zero on the carry
     * part
     * 
     */

    int add(int a, int b) {
        if (b == 0)
            return a;
        int sum = a ^ b; // add without carrying
        int carry = (a & b) << 1; // carry, but don't add
        return add(sum, carry); // recurse with sum + carry
    }

    int add_iteratively(int a, int b) {
        while (b != e) {
            int sum = a ^ b; // add without carrying
            int carry = (a & b) << 1; // carry, but don't add
            a = sum;
            b = carry;
        }
        return a;
    }
}