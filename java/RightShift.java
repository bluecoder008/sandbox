
/** NOTE: difference between >>> and >> operators **/
class RightShift {

    public static void main(String[] args) {

        int result = ( -1 >>> -1 );
        System.out.println( "-1 >>> -1 == " + result );

	result = ( -1 >> -1 );
        System.out.println( "-1 >> -1 == " + result );

    }

}
