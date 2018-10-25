package CrackCode;

public class XMLEncoding {


    /**
     * Since XML is very verbose, you are given a way of encoding it where each tag gets
     * mapped to a pre-defined integer value. The language/grammar is as follows:
     * Element --> Tag Attributes END Children END
     * Attribute --> Tag Value
     * END --> 0
     * Tag --> some predefined mapping to int
     * Value --> string value
     *
     * For example, the following XML might be converted into the compressed string below (assuming a
     * mapping of family - > 1, person - >2, firstName - > 3, lastName - > 4, state
     * -> 5).
     * <family lastName="McDowell" state="CA")
     * <person firstName="Gayle")Some Message</person)
     * </family)
     * Becomes:
     * 1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0
     * Write code to print the encoded version of an XML element (passed in Element and Attribute
     * objects).
     */


    // The approach to recursively first the element name its attributes and its children

    void encode(Element root, StringBuilder sb) {
        encode(root.getNameCode(), sb);
        for (Attribute a : root.attributes) {
            encode(a, sb);
        }
        encode("e", sb);
        if (root.value != null && root.value != "") {
            encode(root.value, sb);
        } else {
            for (Element e : root.children) {
                encode(e, sb);
            }
        }
        encode("e", sb);
    }

    void encode(String v, StringBuilder sb) {
        sb.append(v);
        sb.append(" ");
    }

    void encode(Attribute attr, StringBuilder sb) {
        encode(attr.getTagCode(), sb);
        encode(attr.value, sb);
    }

    String encodeToString(Element root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }


    private abstract class Element {
        Attribute[] attributes;
        String value;

        abstract String getNameCode();

        Element[] children;
    }

    private class Attribute {
        String value;

        String getTagCode() {
            return "" ;
        }
    }
}
