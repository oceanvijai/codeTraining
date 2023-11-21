public class HashCheckBoard{

  /**
      Design a hash function for chess game states. 
      Your function should take a state and the hash code for that state, and a move, and efficiently compute the hash code for the updated state
  **/


  /**
      Approach 1: We have 4 types of pieces + 2 colours + 1 empty. so totally 13 values are possible. So we need 4 bits to represent 13 pieces.
      So, 64 char lenght hashcode can be created with each char with a digit which is base 13. 
      like 0=empty, 1=blackKing, 2=blackQueen... 9=somePiece, A=somePiece,B&C. 

      So we form a 64 digit alphanumerical numnber like HEX.

      When a move is made we try and assign the respective values to the respective digit locations.

      O(1) soluation

      Approach 2: An aswesom solution with XOR, check the book page 233.
  **/


  
}
