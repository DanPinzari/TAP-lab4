// Interfața pentru operațiile specifice fracțiilor
interface FractionOperations {
    Fraction add(Fraction other);
    void display();
}

// Interfața pentru operațiile specifice polinoamelor de fracții
interface FractionPolynomOperations extends FractionOperations {
    void displayPolynom();
}

// Clasa pentru fracții
class Fraction implements FractionOperations {
    protected int numerator;
    protected int denominator;

    // Constructor
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Adunarea a două fracții
    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Metoda pentru afișarea fracției
    public void display() {
        System.out.println(numerator + "/" + denominator);
    }
}

// Clasa pentru polinoame de fracții
class FractionPolynom extends Fraction implements FractionPolynomOperations {
    private int[] numerators;
    private int[] denominators;

    public FractionPolynom(int[] numerators, int[] denominators) {
        super(0, 1); // Inițializăm cu 0/1 pentru a aduna fracții (element neutru pentru adunarea polinom)
        this.numerators = numerators;
        this.denominators = denominators;
    }

    @Override
    public Fraction add(Fraction other) { //metoda add, care primește ca argument un obiect de tip Fraction
        if (other instanceof FractionPolynom) {
            FractionPolynom otherPolynom = (FractionPolynom) other;
            // Verificăm dacă ambele obiecte sunt polinoame de fracții
            if (this.numerators.length != otherPolynom.numerators.length || this.denominators.length != otherPolynom.denominators.length) {
                System.out.println("Nu se poate aduna. Polinoamele nu au aceeași lungime.");
                return null;
            }
            int[] resultNumerators = new int[this.numerators.length];
            int[] resultDenominators = new int[this.denominators.length];
            for (int i = 0; i < this.numerators.length; i++) {
                resultNumerators[i] = this.numerators[i] * otherPolynom.denominators[i] + otherPolynom.numerators[i] * this.denominators[i];
                resultDenominators[i] = this.denominators[i] * otherPolynom.denominators[i];
            }
            return new FractionPolynom(resultNumerators, resultDenominators);
        } else {
            System.out.println("Nu se poate aduna. Al doilea operand nu este un polinom de fracții.");
            return null;
        }
    }

    public void displayPolynom() { //Metoda Afisarea Polinomului de fractii
        // Implementare pentru afișarea polinomului de fracții
        System.out.print("Polinomul de fracții: ");
        for (int i = 0; i < numerators.length; i++) {
            System.out.print(numerators[i] + "/" + denominators[i] + "x^" + i + " + ");
        }
        System.out.println("0");
    }
}

public class Main {
    public static void main(String[] args) {
        // Exemplu de utilizare a claselor
        Fraction fractie1 = new Fraction(1, 2);
        Fraction fractie2 = new Fraction(3, 4);

        Fraction sumaFracții = fractie1.add(fractie2);
        System.out.print("Suma fracțiilor: ");
        sumaFracții.display();

        int[] numeratori = {1, 0, -2, 3}; // exemplu de numaratori pentru un polinom de fracții
        int[] denominatori = {1, 1, 1, 1}; // exemplu de denominatori pentru un polinom de fracții
        FractionPolynom polinomFracții = new FractionPolynom(numeratori, denominatori);

        polinomFracții.add(polinomFracții); // exemplu de utilizare a metodei suprascrise din clasa derivată
        polinomFracții.displayPolynom(); // exemplu de utilizare a metodei specifică clasei FractionPolynom
    }
}
