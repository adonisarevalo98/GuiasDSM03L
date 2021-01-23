import java.util.Scanner;
public class ejercicio {

public static void main(String[] ar) {
Scanner teclado = new Scanner(System.in);
int num1, num2, suma, resta, producto, divicion;
System.out.print("Ingrese el primer numero:");
num1 = teclado.nextInt();
System.out.print("Ingrese el segundo numero:");
num2 = teclado.nextInt();

if (num1 > num2) {
suma = num1+num2;
resta = num1-num2;
    System.out.println("Resultado de suma:" + suma);
    System.out.println("Resultado de resta:" + resta);
} else if (num1 <= num2) {

producto = num1*num2;
 divicion = num1/num2;
     System.out.println("Resultado de producto:" + producto);
    System.out.println("Resultado de divicion:" + divicion);
}
    


}
}
