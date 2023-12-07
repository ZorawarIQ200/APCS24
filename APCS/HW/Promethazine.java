import java.util.*; import java.math.*;

public class Promethazine {
    public static void main(String[] args) {
        ArrayList<Polynomial> polynomials = new ArrayList<>();
        
        // Add your polynomials here -- you can add more if u cringe
        polynomials.add(new Polynomial(new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5)}));
        polynomials.add(new Polynomial(new BigDecimal[]{BigDecimal.valueOf(9), BigDecimal.valueOf(8), BigDecimal.valueOf(7)}));
        

        
        // Print each polynomial
        for (int i = 0; i < polynomials.size(); i++) {
            Polynomial polynomial = polynomials.get(i);
            System.out.println("Polynomial " + (i + 1) + ": " + polynomial);
            System.out.println("Polynomial " + (i + 1) + ": evaluated at x = 1: " + polynomial.evaluate(BigDecimal.valueOf(1))+ "\n");

        }
      
      
        
          
        // Get high on Purple Drank
        Polynomial sum = PolyMath.add(polynomials);
        System.out.println("Sum of all Polynomials: " + sum);
        System.out.println("Sum evaluated at x = 1: " + sum.evaluate(BigDecimal.valueOf(1)) + "\n");

        Polynomial product = PolyMath.multiplyPolynomials(polynomials);
        System.out.println("Product of all Polynomials: " + product);
        System.out.println("Product evaluated at x = 1: " + product.evaluate(BigDecimal.valueOf(1)) + "\n");

        // Initialize a polynomial with degree 4+
        polynomials.add(new Polynomial(new BigDecimal[]{BigDecimal.valueOf(9), BigDecimal.valueOf(8), BigDecimal.valueOf(7), BigDecimal.valueOf(7), BigDecimal.valueOf(7)}));

        System.out.println("Original Polynomial with Degree n >= 4: " + polynomials.get(2));
        Derivative derivative = new Derivative();
        Polynomial dFinal = derivative.findDerivative(polynomials.get(2));

        System.out.println("Derivative of the Polynomial: " + dFinal);

        // Comparing two polynomials for equality 
        System.out.print(polynomials.size());

      	for (int i = 0; i < polynomials.size()-1; i++) {
            if (polynomials.get(i).equals(polynomials.get(i+1))) {
                System.out.printf("Polynomial %d is equal to Polynomial %d %n", i, i+1);
            } else {
                System.out.printf("Polynomial %d is not equal to Polynomial %d %n", i, i+1);
            }
            }  
    }
}

class Polynomial {
    private BigDecimal[] coefficients;

    public Polynomial(BigDecimal[] coefficients) {
        this.coefficients = coefficients;
    }

    public BigDecimal[] getCoefficients() {
        return coefficients;
    }

    public BigDecimal evaluate(BigDecimal x) {
        BigDecimal result = BigDecimal.valueOf(0);
        BigDecimal xPower = BigDecimal.valueOf(1);

        for (int i = 0; i < coefficients.length; i++) {
            result = result.add(coefficients[i].multiply(xPower));
            xPower = xPower.multiply(x);
        }

        return result;
    }

    public Polynomial add(Polynomial other) {
        // find the bigga one
        int maxLength = Math.max(coefficients.length, other.coefficients.length);
        BigDecimal[] sumCoefficients = new BigDecimal[maxLength];

        for (int i = 0; i < coefficients.length; i++) {
            sumCoefficients[i] = coefficients[i];
        }

        for (int i = 0; i < other.coefficients.length; i++) {
            if (sumCoefficients[i] == null) {
                sumCoefficients[i] = other.coefficients[i];
            } else {
                sumCoefficients[i] = sumCoefficients[i].add(other.coefficients[i]);
            }
        }

        return new Polynomial(sumCoefficients);
    }

    public Polynomial multiply(Polynomial other) {
        int resultLength = coefficients.length + other.coefficients.length - 1;
        BigDecimal[] productCoefficients = new BigDecimal[resultLength];


        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                if (productCoefficients[i + j] == null) {
                    // If the array is undefined at this index, add the product directly
                    productCoefficients[i + j] = coefficients[i].multiply(other.coefficients[j]);
                } else {
                    // If the array is defined at this index, add the calculated product to the existing value
                    productCoefficients[i + j] = productCoefficients[i + j].add(coefficients[i].multiply(other.coefficients[j]));
                }
            }
        }
        return new Polynomial(productCoefficients);
    }

    //this took wayyyyyyyy to long.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 0; i--) { 
            if (coefficients[i].compareTo(BigDecimal.valueOf(0)) != 0) {
                if (coefficients[i].compareTo(BigDecimal.valueOf(0)) > 0 && i < coefficients.length - 1) {
                    //kewl append
                    sb.append(" + ");
                } else if (coefficients[i].compareTo(BigDecimal.valueOf(0)) < 0) {
                    sb.append(" - ");
                }

                if (coefficients[i].abs().compareTo(BigDecimal.valueOf(1)) != 0) {
                    sb.append(coefficients[i].abs());
                }

                if (i > 0) {
                    sb.append("x");
                    if (i > 1) {
                        sb.append("^").append(i);
                    }
                }
            }
        }
        return sb.toString();
    }
}

class PolyMath {
    public static Polynomial add(List<Polynomial> polynomials) {
        Polynomial sum = polynomials.get(0);
        for (int i = 1; i < polynomials.size(); i++) {
            sum = sum.add(polynomials.get(i));
        }
        return sum;
    }

    public static Polynomial multiplyPolynomials(List<Polynomial> polynomials) {
        Polynomial product = polynomials.get(0);
        for (int i = 1; i < polynomials.size(); i++) {
            product = product.multiply(polynomials.get(i));
        }
        return product;
    }
}

class Derivative { // how curvy is your mom?
    public Polynomial findDerivative(Polynomial p) {
        BigDecimal[] temp = new BigDecimal[p.getCoefficients().length - 1];
        for (int i = 1; i < p.getCoefficients().length; i++) {
            temp[i - 1] = p.getCoefficients()[i].multiply(BigDecimal.valueOf(i));
        }
        return new Polynomial(temp);
    }
}

