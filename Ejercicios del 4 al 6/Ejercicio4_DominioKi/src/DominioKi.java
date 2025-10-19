import java.util.Scanner;

public class DominioKi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la expresión del Ki:");
        String expresion = sc.nextLine();

        if (ValidadorExpresion.esBalanceada(expresion)) {
            System.out.println("Balanceada");
        } else {
            System.out.println("No balanceada");
        }
    }
}
