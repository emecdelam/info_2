package oop;

/**
 * This class can be used to build simple arithmetic expression
 * with binary operator +,-,* and involving one variable 'x'.
 *
 * The expression can be
 * 1) evaluated by replacing the variable x with a specific value
 * 2) derivated to obtain a new expression
 *
 * You must modify this class to make it work
 * You can/should extend this class with inner classes the way you want.
 * You can also modify it but you are not allowed to modify the signature
 * of existing methods
 *
 * As a reminder, the formulas for the derivations as are followed
 *  - (f + g)' = f' + g'
 *  - (f*g)' = f'g + fg'
 *  - (x)' = 1
 *  - (C)' = 0 with C a constant
 */
public abstract class Expression {

    /**
     * Creates the basic variable expression 'x'
     * @return the expression 'x'
     */
    public static Expression x() {return new Variable();}

    /**
     * Creates the basic constant expression 'v'
     * @return the expression 'v'
     */
    public static Expression value(double v) {return new Constant(v);}

    /**
     * Creates the binary expression 'this + r'
     * @param r the right operator
     * @return the binary expression 'this + r'
     */
    public Expression plus(Expression r) {return new Addition(this,r);}

    /**
     * Creates the binary expression 'this - r'
     * @param r the right operator
     * @return the binary expression 'this - r'
     */
    public Expression minus(Expression r) {return new Substraction(this,r);}

    /**
     * Creates the binary expression 'this * r'
     * @param r the right operator
     * @return the binary expression 'this * r'
     */
    public Expression mul(Expression r) {return new Multiplication(this,r);}

    /**
     * Evaluate the expression with fixed value for x
     * @param xValue the value taken by x for the evaluation
     * @return the evaluation of the expression considering x=xValue
     */
    public abstract double evaluate(double xValue);

    /**
     * Derivate the expression wrt to 'x'
     * @return the derivative of the expression with respect to 'x'
     */
    public abstract Expression derivate();

}
class Variable extends Expression {

    @Override
    public double evaluate(double val) {
        return val;
    }

    @Override
    public Expression derivate() {
        return value(1);
    }
}
class Constant extends Expression {
    private double value;
    public Constant(double value) {
        this.value = value;
    }
    @Override
    public double evaluate(double val){
        return value;
    }
    @Override
    public Expression derivate(){
        return value(0);
    }
}
class Addition extends Expression{
    private Expression a;
    private Expression b;

    public Addition(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public double evaluate(double val) {
        return a.evaluate(val) + b.evaluate(val);
    }

    @Override
    public Expression derivate() {
        return a.derivate().plus(b.derivate());
    }
}
class Substraction extends Expression{
    private Expression a;
    private Expression b;
    public Substraction(Expression a, Expression b){
        this.a = a;
        this.b = b;
    }
    @Override
    public double evaluate(double val) {
        return a.evaluate(val) - b.evaluate(val);
    }

    @Override
    public Expression derivate() {
        return a.derivate().minus(b.derivate());
    }
}
class Multiplication extends Expression{
    private Expression a;
    private Expression b;
    public Multiplication(Expression a,Expression b){
        this.a = a;
        this.b = b;
    }
    @Override
    public double evaluate(double val) {
        return a.evaluate(val) * b.evaluate(val);
    }

        @Override
        public Expression derivate() {
            return a.mul(b.derivate()).plus(a.derivate().mul(b));
        }
    }
