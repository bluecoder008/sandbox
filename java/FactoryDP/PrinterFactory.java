
class PrinterFactory
{
    Printer createPrinter(String requirement)  {

        if ( requirement.equalsIgnoreCase("PDF") ) {
            return new PDFPrinter();
        }

        if ( requirement.equalsIgnoreCase("Home") ) {
            return new HomePrinter();
        }

        if ( requirement.equalsIgnoreCase("Office") ) {
            return new OfficePrinter();
        }
	return null;
    }
}

