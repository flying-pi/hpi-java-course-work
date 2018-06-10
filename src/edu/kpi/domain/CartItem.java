package edu.kpi.domain;

public class CartItem {
    private MathItem operation;
    private int      operand;

    public CartItem(MathItem operation, int operand) {
        this.operation = operation;
        this.operand = operand;
    }

    public CartItem() {
    }

    public MathItem getOperation() {
        return operation;
    }

    public void setOperation(MathItem operation) {
        this.operation = operation;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }
}
