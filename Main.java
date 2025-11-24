import functions.*;
import functions.basic.*;
import functions.meta.*;
import java.io.*;

public class Main {

    public static void eq(boolean result) {
        if (result) {
            System.out.println("Функции равны" + '\n');
        } else {
            System.out.println("Функции не равны" + '\n');
        }
    }

    public static void main(String[] s) throws InappropriateFunctionPointException {

        // Создаем линейную функцию y = 4x
        TabulatedFunction fun1 = new ArrayTabulatedFunction(1, 100, 10);
        for (int i = 0; i < fun1.getPointsCount(); i++) {
            fun1.setPointY(i, 4 * fun1.getPointX(i));
        }
        System.out.println("fun1: y = 4x");
        System.out.println(fun1.toString() + '\n');

        // Создаем функцию с произвольными значениями
        double[] val = {2, 4, 6, 8, 10, 12, 14};
        TabulatedFunction fun2 = new LinkedListTabulatedFunction(1, 7, val);
        System.out.println("fun2: Функция с значениями из массива");
        System.out.println(fun2.toString() + '\n');

        // Создаем функцию через композицию
        TabulatedFunction fun3 = TabulatedFunctions.tabulate(
                Functions.composition(new Log(Math.E), new Exp()), 1, 11, 11);
        System.out.println("fun3: log(exp)");
        System.out.println(fun3.toString() + '\n');

        // Создаем сумму функций
        TabulatedFunction fun4 = TabulatedFunctions.tabulate(Functions.sum(fun1, fun3), 1, 11, 11);
        System.out.println("fun4: fun1 + fun3");
        System.out.println(fun4.toString() + '\n');

        // Создаем функцию с линейными значениями
        double[] val1 = {4, 48, 92, 136, 180, 224, 268, 312, 356, 400};
        TabulatedFunction fun5 = new LinkedListTabulatedFunction(1, 100, val1);
        System.out.println("fun5: Функция с линейными значениями");
        System.out.println(fun5.toString() + '\n');

        // Клонируем fun4
        TabulatedFunction fun6 = (TabulatedFunction) fun4.clone();
        System.out.println("fun6: Копия fun4");
        System.out.println(fun6.toString() + '\n');

        // Тестируем equals()
        System.out.println("Равенство функций fun1 и fun2");
        eq(fun1.equals(fun2));

        System.out.println("Равенство функций fun1 и fun3 ");
        eq(fun1.equals(fun3));

        System.out.println("Равенство функций fun1 и fun5 ");
        eq(fun1.equals(fun5));

        System.out.println("Равенство функций fun4 и fun6");
        eq(fun4.equals(fun6));

        // Тестируем hashCode()
        System.out.println("Хэш-код для fun1");
        System.out.println(fun1.hashCode());
        System.out.println();

        System.out.println("Хэш-код для fun2");
        System.out.println(fun2.hashCode());
        System.out.println();

        System.out.println("Хэш-код для fun3");
        System.out.println(fun3.hashCode());
        System.out.println();

        System.out.println("Хэш-код для fun4");
        System.out.println(fun4.hashCode());
        System.out.println();

        System.out.println("Хэш-код для fun5");
        System.out.println(fun5.hashCode());
        System.out.println();

        System.out.println("Хэш-код для fun6");
        System.out.println(fun6.hashCode());
        System.out.println();

        // Тестируем изменение хэш-кода
        System.out.println("Изменили точку с индексом 2 на 0.005");
        fun4.setPointY(2, fun4.getPointY(2) + 0.005);
        System.out.println(fun4.toString() + '\n');
        System.out.println("Измененный хэш-код для fun4");
        System.out.println(fun4.hashCode());
        System.out.println();

        // Проверяем глубокое клонирование
        System.out.println("Изменили исходную fun4, при этом копия fun6 не изменилась");
        System.out.println(fun6.toString() + '\n');

        // Тестируем клонирование LinkedList
        TabulatedFunction fun7 = (TabulatedFunction) fun2.clone();
        System.out.println("fun7: Копия fun2");
        System.out.println(fun7.toString() + '\n');

        fun2.addPoint(new FunctionPoint(18, 20));
        System.out.println("Изменили исходную fun2");
        System.out.println(fun2.toString() + '\n');

        System.out.println("Копия fun7 не изменилась");
        System.out.println(fun7.toString() + '\n');

    }
}