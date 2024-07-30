import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение.");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String operation;
        String result;
        int number1;
        int number2;
        boolean isRoman;
        String[] strings = input.split("[+\\-*/]");
        if (strings.length != 2) throw new Exception("Должно быть два числа.");
        operation = detectOperation(input);
        if (operation == null) throw new Exception("Неправильный ввод данных.");

        if (Roman.isRoman(strings[0]) && Roman.isRoman(strings[1])) {
            number1 = Roman.convertToArabian(strings[0]);
            number2 = Roman.convertToArabian(strings[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(strings[0]) && !Roman.isRoman(strings[1])) {
            number1 = Integer.parseInt(strings[0]);
            number2 = Integer.parseInt(strings[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Неверный формат ввода (2 арабских, либо 2 римских числа).");
        }
        if (number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(number1, number2, operation);
        if (isRoman) {

            if (arabian <= 0) {
                throw new Exception("Значение должно быть больше нуля");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}
