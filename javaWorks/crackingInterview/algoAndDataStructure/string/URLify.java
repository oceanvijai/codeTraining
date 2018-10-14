public class URLify {

    // 1: in the first pass count the number of spaces
    // Then start filling from the last with two pointer
    // one for the index where the char has to be places
    // another reading the characters in the string

    void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = e, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spacecount++;
            }
        }

        index = trueLength + spaceCount * 2;
        for (i = trueLength; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }

    }

}