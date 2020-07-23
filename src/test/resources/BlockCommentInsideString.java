/*****
 * This is a test program with 5 lines of code
 *  \/* no nesting allowed!
 //*****/ /***/ // Slightly pathological comment ending...
 /*****/ /***/ // comment
public class Hello {

    @cool
    private String test = "some /* commented */ 'and' \" asd other\" " +
            "/*string" +
            "invisible line" +
            "df*/";

    public static final void main(String [] args) { // gotta love Java
        // Say hello
    }
}