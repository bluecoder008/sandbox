
interface Printer {

    public void print(String file);
}

class PDFPrinter implements Printer {
    public void print(String file) {
        System.out.println("Output " + file + " to PDF file");
    }
}

class HomePrinter implements Printer {
    public void print(String file) {
        System.out.println("Output " + file + " to home ink jet printer");
    }
}

class OfficePrinter implements Printer {
    public void print(String file) {
        System.out.println("Output " + file + " to office laser printer");
    }
}

