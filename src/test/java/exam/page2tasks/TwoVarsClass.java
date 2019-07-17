package exam.page2tasks;

//Создать класс с двумя переменными. Добавить функцию вывода на экран и функцию изменения этих переменных.
//Добавить функцию, которая находит сумму значений этих переменных, и функцию которая находит наибольшее значение из этих двух переменных.
public class TwoVarsClass {
    private int var1;
    private int var2;

    public TwoVarsClass(int var1, int var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public static void main(String[] args) {
        TwoVarsClass twoVarsClass = new TwoVarsClass(5, 74);
        twoVarsClass.printVars();
    }

    public void printVars() {
        System.out.printf("var1 = %d; var2 = %d", var1, var2);
    }

    public void setVars(int var1, int var2) {
        setVar1(var1);
        setVar2(var2);
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public void setVar2(int var2) {
        this.var2 = var2;
    }

    public long getSum() {
        return var1 + var2;
    }

    public int getMax() {
        if (var1 > var2)
            return var1;
        else
            return var2;
    }
}