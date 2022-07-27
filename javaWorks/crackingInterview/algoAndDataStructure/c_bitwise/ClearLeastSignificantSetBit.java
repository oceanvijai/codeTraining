Intution

First create a mask by doing (a-1)

Lets say a = 10010001000 
Then a-1=10010000111 (mask)

then & the mask with the orginal value

ans = a & mask

so, ans becomes 10010000000

------------------------------------------------------------------------------------------------
Lets say we want to want to clear the most significant bit ?

Lets say a=000010010001000

1. First we find the most significant bit location
2. create a mask like mask = ((1<<msb) - 1)
3. and with mask a & mask


private static int msb(int num){
        int pos = 0;
        System.out.println(Integer.toBinaryString(num));
        while(num != 0){
            num = num << 1; // 00011100100
            System.out.println(Integer.toBinaryString(num));
            pos++;
        }
        
        return pos;
    }
