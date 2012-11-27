
class TestPrinterFactory {

    static public void main(String[] args) {
    
        if (args.length != 1 ) {
            System.err.println("Please specify a printer type: Pdf, Home or Office.");
            System.exit(-1);
        }

        PrinterFactory factory = new PrinterFactory();

        Printer thePrinter = factory.createPrinter(args[0]);

        if (thePrinter != null ) {
	    thePrinter.print("MyFile.txt");
        } else {
            System.err.println("Unknown printer specifier.");
        }
    }
}
