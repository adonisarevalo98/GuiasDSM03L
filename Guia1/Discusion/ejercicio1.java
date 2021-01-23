import java.util.Scanner;
public class Validandomodulo {
public static void main(String[] ar) {
Scanner teclado = new Scanner(System.in);
int num1, num2, modulo;
System.out.print("Ingrese el primer numero:");
num1 = teclado.nextInt();
System.out.print("Ingrese el segundo numero:");
num2 = teclado.nextInt();
if (num1 % num2 == 0) {
    System.out.println("El primer numero SI es divicible entre el segundo");
} else {
    System.out.println("El primer numero NO es divicible entre el segundo");
}

}
}